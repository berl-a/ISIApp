using System;
using System.Threading.Tasks;
using System.Net.Http;
using System.Net;
using ISI_TaxiCorpDriverApp.Utils;
using System.IO;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Schema;
using System.Xml.Xsl;
using ISI_TaxiCorpDriverApp.Model.HttpRequest;

namespace ISI_TaxiCorpDriverApp.Model
{
    class DistanceCalculator
    {
        public async Task<double> CalculateDistance(WorldPosition origin, WorldPosition destination) {
            double distance = -1;

            HttpResponseMessage distanceResponse = await GetDistanceResponse(origin, destination);

            switch (distanceResponse.StatusCode) {
                case HttpStatusCode.OK:
                    string distanceResponsePath = await SaveTempXmlFile(distanceResponse);

                    distance = TransformDistance(distanceResponsePath);

                    File.Delete(distanceResponsePath);
                    break;

                default:
                    //TODO: throw an exception, call this function in a try-catch block
                    break;
            }

            return distance;
        }

        private async Task<HttpResponseMessage> GetDistanceResponse(WorldPosition origin, WorldPosition destination) {
            HttpRequestDistance distanceRequest = new HttpRequestDistance(origin, destination);

            Logger.AddLine(distanceRequest.GetRequestUri());

            HttpResponseMessage distanceResponse = await HttpClientManager.PostAsync(distanceRequest);

            //HttpClientManager.LogRequestResponseAsync(distanceResponse);

            return distanceResponse;
        }

        private async Task<string> SaveTempXmlFile(HttpResponseMessage response) {
            string path = PathUtils.GetTempFileNameWithExtension(".xml");

            using (Stream stream = await response.Content.ReadAsStreamAsync()) {
                XmlDocument xml = new XmlDocument();
                xml.Load(stream);
                xml.Save(path);
            }

            return path;
        }

        private double TransformDistance(string distanceResponsePath) {
            double result = 0;
            string xslSchema = Directory.GetParent(Environment.CurrentDirectory).Parent.FullName + @"\XMLReturn.xsd";
            string outputFile = Environment.CurrentDirectory + @"\outputFile.xml";
            string xsltDirectory = Directory.GetParent(Environment.CurrentDirectory).Parent.FullName + @"\DistanceMatrixXSLT.xsl";
            ValidationEventHandler eventHandler = new ValidationEventHandler(ValidationEventHandler);
            XmlDocument outputXml = new XmlDocument();

            try 
            {
                XDocument xmlResp = XDocument.Load(distanceResponsePath);

                XmlReaderSettings xmlReaderSettings = new XmlReaderSettings();
                xmlReaderSettings.Schemas.Add("", xslSchema);
                xmlReaderSettings.ValidationType = ValidationType.Schema;

                using (XmlReader xmlReader = XmlReader.Create(distanceResponsePath, xmlReaderSettings)) {
                    XmlDocument xmlDocument = new XmlDocument();

                    xmlDocument.Load(xmlReader);
                    xmlDocument.Validate(eventHandler);

                    using (StreamWriter writer = new StreamWriter(outputFile)) {
                        XslCompiledTransform xslt = new XslCompiledTransform();
                        xslt.Load(XmlReader.Create(xsltDirectory));
                        xslt.Transform(xmlResp.CreateReader(), null, writer);
                        writer.Close();
                    }
                }

            } 
            catch (Exception ex) //Catches xml schema validation exception
            {
                Logger.AddLine(ex.Message);
                return -1;
            }

            using (FileStream fs = new FileStream(outputFile, FileMode.Open, FileAccess.Read)) {
                outputXml.Load(fs);
                result = extractNumber(outputXml.InnerText);
            }

            return result;
        }

        static void ValidationEventHandler(object sender, ValidationEventArgs e) {
            switch (e.Severity) {
                case XmlSeverityType.Error:
                    Console.WriteLine("Error: {0}", e.Message);
                    break;
                case XmlSeverityType.Warning:
                    Console.WriteLine("Warning {0}", e.Message);
                    break;
            }
        }


        public double extractNumber(string _number) {
            string a = _number;
            string b = string.Empty;
            double val = 0;

            for (int i = 0; i < a.Length; i++) {
                if (Char.IsDigit(a[i]) || a[i] == '.')
                    if (a[i] == '.')
                        b += ",";
                    else
                        b += a[i];
            }

            if (b.Length > 0)
                val = Double.Parse(b);

            return val;
        }
    }
}
