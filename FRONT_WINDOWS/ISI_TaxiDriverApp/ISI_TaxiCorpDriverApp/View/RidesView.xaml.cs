using ISI_TaxiCorpDriverApp.Model;
using ISI_TaxiCorpDriverApp.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace ISI_TaxiCorpDriverApp.View
{
    /// <summary>
    /// Interaction logic for RidesView.xaml
    /// </summary>
    public partial class RidesView : Window
    {
        public RidesView()
        {
            InitializeComponent();
            var ridesVM = new RidesVM();
            DataContext = ridesVM;

            //Test();
        }
        public async Task Test()
        {
            LoginController loginController = new LoginController();
            DistanceCalculator distanceCalculator = new DistanceCalculator();
            PriceCalculator priceCalculator = new PriceCalculator();

            try {
                string token = await loginController.GetToken(
                    Properties.Settings.Default.ServerUsername, 
                    Properties.Settings.Default.ServerPassword);

                WorldPosition origin = new WorldPosition(51.369835, 16.964561);
                WorldPosition destination = new WorldPosition(51.107139, 17.060234);

                double distance = await distanceCalculator.CalculateDistance(origin, destination);

                Logger.AddLine(string.Format("distance - {0}", distance));

                double price = await priceCalculator.CalculatePrice(distance, token);
            } catch (Exception e) {
                Logger.AddLine(e.Message, LogType.Error);
            }
        }
    }
}
