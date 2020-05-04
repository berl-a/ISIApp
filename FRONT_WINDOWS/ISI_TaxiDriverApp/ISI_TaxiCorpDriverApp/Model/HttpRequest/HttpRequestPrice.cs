using ISI_TaxiCorpDriverApp.Model.HttpRequestBody;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpRequest
{
    class HttpRequestPrice : IHttpRequest
    {
        private const string GetCostEndpoint = "/ride/getCost";

        public RideDistance RideDistance { get; set; }
        public string Token { get; set; }

        public HttpRequestPrice() { }

        public HttpRequestPrice(RideDistance rideDistance, string token) {
            RideDistance = rideDistance;
            Token = token;
        }

        public HttpRequestPrice(double distance, string token) {
            RideDistance = new RideDistance {
                Distance = distance,
            };
            Token = token;
        }

        public HttpContent GetRequestContent() {
            string json = JsonConvert.SerializeObject(RideDistance);

            return new StringContent(json, Encoding.UTF8, "application/json");
        }

        public string GetRequestUri() {
            return string.Concat(Properties.Settings.Default.ISIApiServerUrl, GetCostEndpoint);
        }

        public AuthenticationHeaderValue GetAuthenticationHeaderValue() {
            return new AuthenticationHeaderValue(Properties.Settings.Default.BearerTokenKey, Token);
        }
    }
}
