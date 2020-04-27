using ISI_TaxiCorpDriverApp.Model.HttpRequestBody;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpRequests
{
    class HttpRequestAuth : IHttpRequest
    {
        private const string LoginEndpoint = "/auth/login";

        public UserCredentials UserCredentials { get; set; }

        public HttpRequestAuth() { }

        public HttpRequestAuth(UserCredentials userCredentials) {
            UserCredentials = userCredentials;
        }

        public HttpRequestAuth(string username, string password) {
            UserCredentials = new UserCredentials {
                Username = username,
                Password = password
            };
        }

        public HttpContent GetRequestContent() {
            string json = JsonConvert.SerializeObject(UserCredentials);

            return new StringContent(json, Encoding.UTF8, "application/json");
        }

        public string GetRequestUri() {
            return string.Concat(Properties.Settings.Default.ISIApiServerUrl, LoginEndpoint);
        }
    }
}
