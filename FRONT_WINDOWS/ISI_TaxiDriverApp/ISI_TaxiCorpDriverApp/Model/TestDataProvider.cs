using ISI_TaxiCorpDriverApp.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp
{
    class TestDataProvider
    {
        public static List<Ride> getTestRides()
        {
            List<Ride> ridesList = new List<Ride>
                {
                new Ride(
                    new WorldPosition(50.8189483, 20.5371519),
                    new WorldPosition(51.307133, 17.061118),
                    "Bartek"),
                new Ride(
                    new WorldPosition(50.064323, 19.950538),
                    new WorldPosition(51.307133, 17.061118),
                    "Bartek")
                };
            return ridesList;
        }
    }
}
