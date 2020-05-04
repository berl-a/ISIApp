using ISI_TaxiCorpDriverApp.Model.HttpRequest;
using ISI_TaxiCorpDriverApp.Model.HttpResponseBody;
using ISI_TaxiCorpDriverApp.Utils;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model
{
    class LoginController
    {
        public async Task<string> GetToken(string username, string password) {
            string token = "";

            HttpResponseMessage loginResponse = await GetLoginResponse(username, password);

            switch (loginResponse.StatusCode) {
                case HttpStatusCode.OK:
                    string loginResponseBody = await loginResponse.Content.ReadAsStringAsync();

                    UserToken userToken = JsonConvert.DeserializeObject<UserToken>(loginResponseBody);
                    token = userToken.Token;

                    Logger.AddLine(string.Format("token - {0}", token));
                    break;

                default:
                    //TODO: throw an exception, call this function in a try-catch block
                    break;
            }

            return token;
        }

        private async Task<HttpResponseMessage> GetLoginResponse(string username, string password) {
            HttpRequestAuth authRequest = new HttpRequestAuth(username, password);

            Logger.AddLine(authRequest.GetRequestUri());

            HttpResponseMessage authResponse = await HttpClientManager.PostAsync(authRequest);

            HttpClientManager.LogRequestResponseAsync(authResponse);

            return authResponse;
        }
    }
}
