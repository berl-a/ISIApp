using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model.HttpRequest
{
    interface IHttpRequest
    {
        string GetRequestUri();
        HttpContent GetRequestContent();
        AuthenticationHeaderValue GetAuthenticationHeaderValue();
    }
}
