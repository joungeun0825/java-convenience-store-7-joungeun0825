package store.domain.product;

import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionType;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ProductLoader {
    private static final String FILE_PATH = "src/main/resources/products.md";

    public static void stockProducts() {
        try {
            Files.lines(Paths.get(FILE_PATH), Charset.forName("UTF-8"))
                    .skip(1)
                    .forEach(ProductLoader::processProductLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processProductLine(String inputProduct) {
        List<String> currentProduct = parseProductLine(inputProduct);
        String name = currentProduct.get(0);
        int price = Integer.parseInt(currentProduct.get(1));
        int quantity = Integer.parseInt(currentProduct.get(2));
        String promotion = currentProduct.get(3);

        if (isRegularProduct(promotion)) {
            addRegularProduct(name, price, quantity);
            return;
        }

        addPromotionProduct(name, price, quantity, promotion);
    }

    private static List<String> parseProductLine(String inputProduct) {
        return Arrays.asList(inputProduct.split(","));
    }

    private static boolean isRegularProduct(String promotion) {
        return "null".equals(promotion);
    }

    private static void addRegularProduct(String name, int price, int quantity) {
        ProductRegistry.putProduct(name, new Product(name, price, quantity));
    }

    private static void addPromotionProduct(String name, int price, int promotionQuantity, String promotionName) {
        Promotion promotion = PromotionType.fromDisplayName(promotionName).getPromotion();
        PromotionProduct promotionProduct = new PromotionProduct(name,price,promotionQuantity,promotion);
        ProductRegistry.putPromotionProduct(name, promotionProduct);
    }
}
