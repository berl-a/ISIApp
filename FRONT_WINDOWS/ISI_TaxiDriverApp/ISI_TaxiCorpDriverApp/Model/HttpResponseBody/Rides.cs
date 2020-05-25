using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpRequestBody
{
    class Rides
    {
        [JsonProperty("Rides")]
        public List<Ride> RidesList { get; set; }
    }
}
