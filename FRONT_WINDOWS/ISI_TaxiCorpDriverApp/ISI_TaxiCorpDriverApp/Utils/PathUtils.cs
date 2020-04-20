using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Utils
{
    static class PathUtils
    {
        public static string GetTempFileNameWithExtension(string extension) {
            string path = Path.GetTempPath();
            string filename = Guid.NewGuid().ToString() + extension;
            return Path.Combine(path, filename);
        }
    }
}
