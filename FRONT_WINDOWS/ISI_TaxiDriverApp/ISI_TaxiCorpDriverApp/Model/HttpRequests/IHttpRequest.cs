using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpRequests
{
    interface IHttpRequest
    {
        string GetRequestUri();
        HttpContent GetRequestContent();
    }
}
