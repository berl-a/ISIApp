using ISI_TaxiCorpDriverApp.Model;
using ISI_TaxiCorpDriverApp.ViewModel;
using Nito.AsyncEx;
using Nito.AsyncEx.Synchronous;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Input;

namespace ISI_TaxiCorpDriverApp
{
    class RidesVM : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;

        public ICommand GetRideCmd { get; set; }

        private ObservableCollection<Ride> _RidesList;
        public ObservableCollection<Ride> RidesList {
            get { return _RidesList; }
            set 
            {
                _RidesList = value;
                OnPropertyChanged("RidesList");
            }
        }

        public RidesVM()
        {
            GetRideCmd = new RelayCommand(param => GetRide());
            List<Ride> rides = TestDataProvider.getTestRides();
            RidesList = new ObservableCollection<Ride>(rides);
            populateDistance(rides);

            //RidesList = new ObservableCollection<Ride>(populateDistance(rides));
        }

        public async void populateDistance(List<Ride> _rides)
        {
            List<Ride> rides = new List<Ride>(_rides);

            foreach (Ride r in rides)
            {
                await Task.Run(() =>
                {

                    WorldPosition start = r.startPosition;
                    WorldPosition end = r.destinationPosition;
                    DistanceCalculator distanceCalculator = new DistanceCalculator();
                    r.distance = distanceCalculator.CalculateDistance(start, end).Result;

                });
            }
            RefreshList(new ObservableCollection<Ride>(rides));
        }

        public void RefreshList(ObservableCollection<Ride> rides)
        {
            RidesList = rides;
            
        }
            public void GetRide()
        {
            MessageBox.Show("BUM");
        }

        virtual protected void OnPropertyChanged(string _propertyName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(_propertyName));
        }
    }
}
