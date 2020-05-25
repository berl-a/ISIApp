using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using System.Net;
using ISI_TaxiCorpDriverApp.Utils;
using System.IO;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Schema;
using System.Xml.Xsl;
using System.Xml.XPath;
using ISI_TaxiCorpDriverApp.Model.HttpRequest;
using System.Text.RegularExpressions;
using ISI_TaxiCorpDriverApp.Model.HttpResponseBody;
using Newtonsoft.Json;
using ISI_TaxiCorpDriverApp.Model.HttpRequestBody;

namespace ISI_TaxiCorpDriverApp.Model
{
    class RidesController
    {
        public async Task<List<Ride>> GetRides(double distance, string token) {
            List<Ride> ridesList = new List<Ride>();

            HttpResponseMessage rideResponse = await GetRidesResponse();

            switch (rideResponse.StatusCode) {
                case HttpStatusCode.OK:
                    string ridesResponse = await rideResponse.Content.ReadAsStringAsync();

                    Rides rides = JsonConvert.DeserializeObject<Rides>(ridesResponse);
                    ridesList = rides.RidesList;
                    break;

                default:
                    //TODO: throw an exception, call this function in a try-catch block
                    break;
            }

            return ridesList;
        }

        private async Task<HttpResponseMessage> GetRidesResponse() {
            HttpRequestRides ridesRequest = new HttpRequestRides();

            Logger.AddLine(ridesRequest.GetRequestUri());

            HttpResponseMessage ridesResponse = await HttpClientManager.GetAsync(ridesRequest);

            HttpClientManager.LogRequestResponseAsync(ridesResponse);

            return ridesResponse;
        }
    }
}
