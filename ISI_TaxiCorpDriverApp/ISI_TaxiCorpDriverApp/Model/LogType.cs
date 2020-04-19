using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model
{
    enum LogType
    {
        [Description("Info")]
        Info,
        [Description("Warning")]
        Warning,
        [Description("Error")]
        Error
    }
}
