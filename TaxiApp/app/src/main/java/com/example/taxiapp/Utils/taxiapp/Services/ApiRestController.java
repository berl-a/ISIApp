package com.example.taxiapp.Utils.taxiapp.Services;

import com.example.taxiapp.Utils.taxiapp.RequestBody.UserCredentials;
import com.example.taxiapp.Utils.taxiapp.model.Cost;
import com.example.taxiapp.Utils.taxiapp.model.ResponseLogin;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiRestController {

    private RetrofitClient retrofitClient;

    public ApiRestController(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    private AuthService getAuthServiceImpl() {
        return retrofitClient.getRetrofitClient().create(AuthService.class);
    }

    private RideService getRideServiceImpl() {
        return retrofitClient.getRetrofitClient().create(RideService.class);
    }

    public String login(String username, String password) throws IOException {
        AuthService auth = getAuthServiceImpl();
        final String[] token = new String[1];
        auth.login(new UserCredentials(username, password)).enqueue(new Callback<ResponseLogin>(){

            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if(response.body() != null){
                    token[0] = response.body().getToken();
                }else{
                    System.out.println("No token");
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                System.out.println(t.getMessage());
            }

        });

        return token[0];
    }


    public double getCost() throws IOException {

        final double[] result = new double[1];
        result[0] = 30.00;

        RideService ride = getRideServiceImpl();

        ride.cost(10.12).enqueue(new Callback<Cost>(){
            @Override
            public void onResponse(Call<Cost> call, Response<Cost> response) {

                if(response.body() != null){
                    result[0] = response.body().getPrice();
                }else{
                    System.out.println("No token");
                }
            }

            @Override
            public void onFailure(Call<Cost> call, Throwable t) {
                System.out.println(t.getMessage());
            }

        });


        return result[0];
    }

    /*
    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {android login
        // get example product
        Product keyboard = getProductByName("Keyboard");
        System.out.println(keyboard);

        // add example product
        addProduct(new Product("Printer", 30));

        // get all products
        List<Product> products = getAllProducts();
        products.stream().forEach(System.out::println);
    }
        private List<Product> getAllProducts() {
            ProductService service = getProductServiceImpl();
            Response<List<Product>> response = null;
            try {
                response = service.getProduct().execute();
            } catch (IOException e) {
                //TODO exception handler
                e.printStackTrace();
            }
            return response.body();
        }

        private void addProduct(Product product) {
            ProductService service = getProductServiceImpl();
            try {
                service.addProduct(product).execute();
            } catch (IOException e) {
                //TODO exception handler
                e.printStackTrace();
            }
        }

        private Product getProductByName(String productName) {
            ProductService service = getProductServiceImpl();
            Response<Product> keyboard = null;
            try {
                keyboard = service.getProduct(productName).execute();
            } catch (IOException e) {
                //TODO exception handler
                e.printStackTrace();
            }
            return keyboard.body();
        }

     */
}
