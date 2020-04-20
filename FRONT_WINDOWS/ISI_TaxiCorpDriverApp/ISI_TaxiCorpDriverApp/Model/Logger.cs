using ISI_TaxiCorpDriverApp.Utils;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model
{
    class Logger
    {
        public static void AddLine(string line, LogType logType = LogType.Info) {
            using (StreamWriter streamWriter = File.AppendText(Properties.Settings.Default.LogFile)) {
                string text = PrepareLogLine(line, logType);
                streamWriter.WriteLine(text);
            }
        }

        private static string PrepareLogLine(string line, LogType logType) {
            string dateText = DateTime.Now.ToString("G", new CultureInfo("pl-PL"));
            return string.Format("({0}) {1}: {2}", dateText, logType.Description(), line);
        }
    }
}
