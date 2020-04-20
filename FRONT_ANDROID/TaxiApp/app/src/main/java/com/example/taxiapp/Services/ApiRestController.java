package com.example.taxiapp.Services;

public class ApiRestController {

    private RetrofitClient retrofitClient;

    public ApiRestController(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }



    private AuthService getAuthServiceImpl() {
        return retrofitClient.getRetrofitClient().create(AuthService.class);
    }

    /*
    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {
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
