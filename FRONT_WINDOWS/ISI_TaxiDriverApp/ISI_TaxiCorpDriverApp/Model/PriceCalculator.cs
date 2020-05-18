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

namespace ISI_TaxiCorpDriverApp.Model
{
    class PriceCalculator
    {
        public async Task<double> CalculatePrice(double distance, string token) {
            double price = -1;

            HttpResponseMessage priceResponse = await GetPriceResponse(distance, token);

            switch (priceResponse.StatusCode) {
                case HttpStatusCode.OK:
                    string priceResponseBody = await priceResponse.Content.ReadAsStringAsync();

                    RidePrice ridePrice = JsonConvert.DeserializeObject<RidePrice>(priceResponseBody);
                    price = ridePrice.Price;

                    Logger.AddLine(string.Format("price - {0}", price));
                    break;

                default:
                    //TODO: throw an exception, call this function in a try-catch block
                    break;
            }

            return price;
        }

        private async Task<HttpResponseMessage> GetPriceResponse(double distance, string token) {
            HttpRequestPrice priceRequest = new HttpRequestPrice(distance, token);

            Logger.AddLine(priceRequest.GetRequestUri());

            HttpResponseMessage priceResponse = await HttpClientManager.GetAsync(priceRequest);

            HttpClientManager.LogRequestResponseAsync(priceResponse);

            return priceResponse;
        }
    }
}
