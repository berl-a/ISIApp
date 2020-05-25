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
    class HttpRequestRides : IHttpRequest
    {
        private const string RidesEndpoint = "/rides";

        public UserCredentials UserCredentials { get; set; }

        public HttpRequestRides() { }


        public HttpContent GetRequestContent() {
            return new StringContent("", Encoding.UTF8, "application/json");
        }

        public string GetRequestUri()
        {
            return string.Concat(Properties.Settings.Default.ISIApiServerUrl, RidesEndpoint);
        }

        public AuthenticationHeaderValue GetAuthenticationHeaderValue() {
            return null;
        }
    }
}
