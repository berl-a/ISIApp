using ISI_TaxiCorpDriverApp.Model.HttpRequests;
using ISI_TaxiCorpDriverApp.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model
{
    static class HttpClientManager
    {
        private static readonly HttpClient client = new HttpClient();

        public static async Task<HttpResponseMessage> PostAsync(IHttpRequest request) {
            return await client.PostAsync(request.GetRequestUri(), request.GetRequestContent());
        }

        public static async void LogRequestResponseAsync(HttpResponseMessage response) {
            Logger.AddLine(string.Format("{0} - {1}", response.StatusCode.Description(), await response.Content.ReadAsStringAsync()));
        }
    }
}
