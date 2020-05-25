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

        private String _DistanceTB;
        public String DistanceTB {
            get { return _DistanceTB; }
            set {
                _DistanceTB = value;
                OnPropertyChanged("DistanceTB");
            }
        }

        private String _StartTB;
        public String StartTB {
            get { return _StartTB; }
            set {
                _StartTB = value;
                OnPropertyChanged("StartTB");
            }
        }

        private String _DestinationTB;
        public String DestinationTB {
            get { return _DestinationTB; }
            set {
                _DestinationTB = value;
                OnPropertyChanged("DestinationTB");
            }
        }

        private String _PriceTB;
        public String PriceTB {
            get { return _PriceTB; }
            set {
                _PriceTB = value;
                OnPropertyChanged("PriceTB");
            }
        }

        private ObservableCollection<Ride> _RidesList;
        public ObservableCollection<Ride> RidesList {
            get { return _RidesList; }
            set 
            {
                _RidesList = value;
                OnPropertyChanged("RidesList");
            }
        }

        private Ride _SelectedRide;
        public Ride SelectedRide {
            get {
                return _SelectedRide;
            }
            set {
                _SelectedRide = value;
                OnPropertyChanged("SelectedRide");
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

        public async void fillRideFields()
        {
            LoginController loginController = new LoginController();


            string token = await loginController.GetToken(
                Properties.Settings.Default.ServerUsername,
                Properties.Settings.Default.ServerPassword);

            PriceCalculator priceCalc = new PriceCalculator();
            this.PriceTB = (await priceCalc.CalculatePrice(SelectedRide.distance, token)).ToString();
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
            StartTB = SelectedRide.startPosition.ToString();
            DestinationTB = SelectedRide.destinationPosition.ToString();
            DistanceTB = SelectedRide.distance.ToString();
            fillRideFields();
        }

        virtual protected void OnPropertyChanged(string _propertyName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(_propertyName));
        }
    }
}
