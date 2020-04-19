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

        public static async Task<HttpResponseMessage> PostAsync(string requestUri, FormUrlEncodedContent content) {
            return await client.PostAsync(requestUri, content);
        }

        public static async Task<string> BuiilRequestUri(string requestUri, FormUrlEncodedContent content) {
            string parameters = await content.ReadAsStringAsync();

            return string.Format("{0}?{1}", requestUri, parameters);
        }
    }
}
