using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model
{
    class WorldPosition
    {
        public double Latitude { get; set; }
        public double Longitude { get; set; }

        public WorldPosition(double latitude, double longitude) {
            Latitude = latitude;
            Longitude = longitude;
        }

        public override string ToString() {
            // culture info must be provided to make sure that position string will be formatted with point as a decimal separator
            CultureInfo cultureInfo = new CultureInfo("en-US");
            return string.Format(cultureInfo, "{0},{1}", Latitude, Longitude);
        }
    }
}
