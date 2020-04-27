using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpResponseBody
{
    public class UserToken
    {
        [JsonProperty("username")]
        public string Username { get; set; }
        [JsonProperty("token")]
        public string Token { get; set; }
    }
}
