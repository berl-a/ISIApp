using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpRequestBody
{
    class RideDistance
    {
        [JsonProperty("distance")]
        public double Distance { get; set; }

        public override string ToString() {
            // culture info must be provided to make sure that position string will be formatted with point as a decimal separator
            CultureInfo cultureInfo = new CultureInfo("en-US");
            return string.Format(cultureInfo, "{0}", Distance);
        }
    }
}
