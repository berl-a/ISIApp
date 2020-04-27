using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpRequests
{
    class HttpRequestDistance : IHttpRequest
    {
        private const string OriginsKey = "origins";
        private const string DestinationsKey = "destinations";
        private const string Key = "key";

        public WorldPosition Origin { get; set; }
        public WorldPosition Destination { get; set; }

        public HttpRequestDistance() { 
        }

        public HttpRequestDistance(WorldPosition origin, WorldPosition destination) {
            Origin = origin;
            Destination = destination;
        }

        public string GetRequestUri() {
            string parameters = GetRequestContent().ReadAsStringAsync().Result;

            return string.Format("{0}?{1}", Properties.Settings.Default.GoogleApiUrlXml, parameters);
        }

        public HttpContent GetRequestContent() {
            Dictionary<string, string> requestParameters = new Dictionary<string, string> {
                { OriginsKey, Origin.ToString() },
                { DestinationsKey, Destination.ToString() },
                { Key, Properties.Settings.Default.GoogleApiKey }
            };

            return new FormUrlEncodedContent(requestParameters);
        }
    }
}
