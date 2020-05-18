using ISI_TaxiCorpDriverApp.Model.HttpRequest;
using ISI_TaxiCorpDriverApp.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model
{
    static class HttpClientManager
    {
        private static readonly HttpClient client = new HttpClient();
        private static readonly AuthenticationHeaderValue defaultAuthentication = client.DefaultRequestHeaders.Authorization;

        public static async Task<HttpResponseMessage> PostAsync(IHttpRequest request) {
            AuthenticationHeaderValue requestAuthentication = request.GetAuthenticationHeaderValue();

            client.DefaultRequestHeaders.Authorization = requestAuthentication ?? defaultAuthentication;

            return await client.PostAsync(request.GetRequestUri(), request.GetRequestContent());
        }

        public static async Task<HttpResponseMessage> GetAsync(IHttpRequest request) {
            AuthenticationHeaderValue requestAuthentication = request.GetAuthenticationHeaderValue();

            client.DefaultRequestHeaders.Authorization = requestAuthentication ?? defaultAuthentication;

            return await client.GetAsync(request.GetRequestUri());
        }

        public static async void LogRequestResponseAsync(HttpResponseMessage response) {
            Logger.AddLine(string.Format("{0} - {1}", response.StatusCode.Description(), await response.Content.ReadAsStringAsync()));
        }
    }
}
