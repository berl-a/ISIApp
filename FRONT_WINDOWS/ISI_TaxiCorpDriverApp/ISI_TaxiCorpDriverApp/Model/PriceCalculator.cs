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

namespace ISI_TaxiCorpDriverApp.Model
{
    class PriceCalculator
    {
        public static async Task<double> CalculatePrice(WorldPosition origin, WorldPosition destination) {
            double result = -1;

            HttpResponseMessage distanceResponse    = await GetDistanceResponse(origin, destination);
            XDocument           xmlResp             = new XDocument();

            switch (distanceResponse.StatusCode) {
                case HttpStatusCode.OK:
                    string path = await SaveTempXmlFile(distanceResponse);

                    xmlResp = XDocument.Load(path);

                    string outputFile = Environment.CurrentDirectory + @"\outputFile.xml";
                    string xsltDirectory = Directory.GetParent(Environment.CurrentDirectory).Parent.FullName + @"\DistanceMatrixXSLT.xsl";

                    using (StreamWriter writer = new StreamWriter(outputFile))
                    {
                        XslCompiledTransform xslt = new XslCompiledTransform();
                        xslt.Load(XmlReader.Create(xsltDirectory));
                        xslt.Transform(xmlResp.CreateReader(), null, writer);
                        writer.Close();
                    }
                    // validate xml and perform xslt transformation

                    File.Delete(path);
                    break;

                default:
                    //TODO: throw an exception, call this function in a try-catch block
                    break;
            }

            return result;
        }

        private static async Task<HttpResponseMessage> GetDistanceResponse(WorldPosition origin, WorldPosition destination) {
            Dictionary<string, string> values = new Dictionary<string, string> {
                { "origins", "Washington,DC" },
                { "destinations", "New York City,NY" }
            };

            values.Add("key", Properties.Settings.Default.GoogleApiKey);

            FormUrlEncodedContent content = new FormUrlEncodedContent(values);

            string requestUri = await HttpClientManager.BuiilRequestUri(Properties.Settings.Default.GoogleApiUrlXml, content);

            Logger.AddLine(requestUri);

            HttpResponseMessage distanceResponse = await HttpClientManager.PostAsync(requestUri, null);

            Logger.AddLine(string.Format("{0} - {1}", distanceResponse.StatusCode.Description(), distanceResponse.Content.ReadAsStringAsync()));

            return distanceResponse;
        }

        private static async Task<string> SaveTempXmlFile(HttpResponseMessage response) {
            string path = PathUtils.GetTempFileNameWithExtension(".xml");

            Stream stream = await response.Content.ReadAsStreamAsync();

            XmlDocument xml = new XmlDocument();
            xml.Load(stream);
            xml.Save(path);

            return path;
        }
    }
}
