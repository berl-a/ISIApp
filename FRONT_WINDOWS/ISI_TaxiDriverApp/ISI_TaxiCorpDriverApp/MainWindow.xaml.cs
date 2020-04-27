using ISI_TaxiCorpDriverApp.Model;
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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ISI_TaxiCorpDriverApp
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow() {
            InitializeComponent();

            Test();
        }

        public async Task Test() {
            LoginController loginController = new LoginController();

            string token = await loginController.GetToken("driver_app_1", "pass");

            WorldPosition origin = new WorldPosition(51.369835, 16.964561);
            WorldPosition destination = new WorldPosition(51.107139, 17.060234);

            PriceCalculator priceCalculator = new PriceCalculator();

            double price = await priceCalculator.CalculatePrice(origin, destination);
        }
    }
}
