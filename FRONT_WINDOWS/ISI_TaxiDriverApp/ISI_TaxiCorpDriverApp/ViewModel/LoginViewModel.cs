using ISI_TaxiCorpDriverApp.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Security.Cryptography;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Threading;

namespace ISI_TaxiCorpDriverApp.ViewModel
{
    class LoginViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        private const string AuthorizationEndpoint = "https://accounts.google.com/o/oauth2/v2/auth";
        private const string TokenEndpoint = "https://www.googleapis.com/oauth2/v4/token";
        private const string UserInfoEndpoint = "https://www.googleapis.com/oauth2/v3/userinfo";

        private const string CodeChallengeMethod = "S256";

        private string authorizationRequestUri;
        public string AuthorizationRequestUri { 
            get { return authorizationRequestUri; }
            set {
                authorizationRequestUri = value;
                OnPropertyChanged(nameof(AuthorizationRequestUri));
            }
        }

        public void Init() {
            Login();
        }

        public async void Login() {
            string codeVerifier = RandomDataBase64Url(32);
            string codeChallenge = Base64UrlEncodeNoPadding(Sha256(codeVerifier));

            string redirectUri = string.Format("http://{0}:{1}/", IPAddress.Loopback, GetRandomUnusedPort());
            string state = RandomDataBase64Url(32);
            string requestUri = string.Format("{0}?response_type=code&scope=openid%20profile&redirect_uri={1}&client_id={2}&state={3}&code_challenge={4}&code_challenge_method={5}",
                AuthorizationEndpoint,
                Uri.EscapeDataString(redirectUri),
                Properties.Settings.Default.GoogleOAuthClientId,
                state,
                codeChallenge,
                CodeChallengeMethod);

            HttpListener listener = new HttpListener();
            listener.Prefixes.Add(redirectUri);
            listener.Start();

            AuthorizationRequestUri = requestUri;

            Process.Start(requestUri);

            HttpListenerContext context = await listener.GetContextAsync();

            string html = string.Format("<html><head><meta http-equiv='refresh' content='10;url=https://google.com'></head><body>Please return to the app.</body></html>");
            byte[] buffer = Encoding.UTF8.GetBytes(html);
            context.Response.ContentLength64 = buffer.Length;
            Stream stream = context.Response.OutputStream;
            Task responseTask = stream.WriteAsync(buffer, 0, buffer.Length).ContinueWith((task) =>
            {
                stream.Close();
                listener.Stop();
            });

            if (context.Request.QueryString.Get("error") != null) {
                Logger.AddLine(string.Format("OAuth authorization error: {0}.", context.Request.QueryString.Get("error")));
                return;
            }

            if (context.Request.QueryString.Get("code") == null || context.Request.QueryString.Get("state") == null) {
                Logger.AddLine("Malformed authorization response. " + context.Request.QueryString);
                return;
            }

            var incoming_state = context.Request.QueryString.Get("state");

            if (incoming_state != state) {
                Logger.AddLine(string.Format("Received request with invalid state ({0})", incoming_state));
                return;
            }

            Logger.AddLine("Success!");
        }

        public static int GetRandomUnusedPort() {
            TcpListener listener = new TcpListener(IPAddress.Loopback, 0);
            
            listener.Start();
            int port = ((IPEndPoint)listener.LocalEndpoint).Port;
            listener.Stop();

            return port;
        }

        private static string RandomDataBase64Url(int length) {
            byte[] bytes = new byte[length];

            using (var rng = new RNGCryptoServiceProvider()) {
                rng.GetBytes(bytes);         
            }

            return Base64UrlEncodeNoPadding(bytes);
        }

        private static string Base64UrlEncodeNoPadding(byte[] buffer) {
            string b64 = Convert.ToBase64String(buffer);

            b64 = b64.Replace('+', '-');
            b64 = b64.Replace('/', '_');

            b64 = b64.Replace("=", "");
            return b64;
        }

        private static byte[] Sha256(string text) {
            using (SHA256Managed sha256 = new SHA256Managed()) {
                return sha256.ComputeHash(Encoding.ASCII.GetBytes(text));
            }
        }

        protected void OnPropertyChanged(string propertyName) {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
