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

        private const string DistanceKey = "distance";

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

        public string GetRequestUri() {
            Dictionary<string, string> requestParameters = new Dictionary<string, string> {
                { DistanceKey, RideDistance.ToString() }
            };

            FormUrlEncodedContent encodedParameters = new FormUrlEncodedContent(requestParameters);

            string parameters = encodedParameters.ReadAsStringAsync().Result;

            return string.Format("{0}{1}?{2}", Properties.Settings.Default.ISIApiServerUrl, GetCostEndpoint, parameters);
        }

        public HttpContent GetRequestContent() {
            return null;
        }

        public AuthenticationHeaderValue GetAuthenticationHeaderValue() {
            return new AuthenticationHeaderValue(Properties.Settings.Default.BearerTokenKey, Token);
        }
    }
}
