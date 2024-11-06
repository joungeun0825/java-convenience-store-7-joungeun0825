package store.domain.product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class ProductLoader {
    private static final String FILE_PATH = "src/main/resources/products.md";

    public static void stockProducts(Products products, PromotionProducts promotionProducts) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH, Charset.forName("UTF-8")))) {
            loadProducts(reader, products, promotionProducts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadProducts(BufferedReader reader, Products products, PromotionProducts promotionProducts) throws IOException {
        String inputProduct;
        boolean isFirstLine = true;

        while ((inputProduct = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            processProductLine(inputProduct, products, promotionProducts);
        }
    }

    private static void processProductLine(String inputProduct, Products products, PromotionProducts promotionProducts) {
        List<String> currentProduct = parseProductLine(inputProduct);
        String name = currentProduct.get(0);
        int price = Integer.parseInt(currentProduct.get(1));
        int quantity = Integer.parseInt(currentProduct.get(2));
        String promotion = currentProduct.get(3);

        if (isRegularProduct(promotion)) {
            addRegularProduct(products, name, price, quantity);
            return;
        }

        addPromotionProduct(promotionProducts, name, price, quantity, promotion);
    }

    private static List<String> parseProductLine(String inputProduct) {
        return Arrays.asList(inputProduct.split(","));
    }

    private static boolean isRegularProduct(String promotion) {
        return "null".equals(promotion);
    }

    private static void addRegularProduct(Products products, String name, int price, int quantity) {
        Product product = new Product(name, price, quantity);
        products.add(product);
    }

    private static void addPromotionProduct(PromotionProducts promotionProducts, String name, int price, int quantity, String promotion) {
        PromotionProduct promotionProduct = new PromotionProduct(name, price, quantity, promotion);
        promotionProducts.add(promotionProduct);
    }
}
