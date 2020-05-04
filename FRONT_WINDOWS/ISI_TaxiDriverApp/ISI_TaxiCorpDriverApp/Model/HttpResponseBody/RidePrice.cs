using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpResponseBody
{
    class RidePrice
    {
        [JsonProperty("price")]
        public double Price { get; set; }
    }
}
