using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace ISI_TaxiCorpDriverApp.Model
{
    [DataContract]
    class Ride
    {
        [DataMember]
        public WorldPosition startPosition { get; set; }
        [DataMember]
        public WorldPosition destinationPosition { get; set; }
        [DataMember]
        public string customerName { get; set; }
        [DataMember]
        public double distance { get; set; }

        public Ride(WorldPosition _initPos, WorldPosition _destPos, string _custName, double _distance = 0)
        {
            startPosition       = _initPos;
            destinationPosition = _destPos;
            customerName        = _custName;
            distance            = _distance;
        }
    }
}
