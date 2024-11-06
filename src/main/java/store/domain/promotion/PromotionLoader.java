package store.domain.promotion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class PromotionLoader {
    private static final String FILE_PATH = "src/main/resources/promotions.md";
    public static void registerPromotion(Promotions promotions) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH, Charset.forName("UTF-8")))) {
            loadProducts(reader, promotions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadProducts(BufferedReader reader, Promotions promotions) throws IOException {
        String inputPromotion;
        boolean isFirstLine = true;

        while ((inputPromotion = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            processPromotionLine(inputPromotion, promotions);
        }
    }

    private static void processPromotionLine(String inputPromotion, Promotions promotions) {
        List<String> currentPromotion = parsePromotionLine(inputPromotion);
        String name = currentPromotion.get(0);
        int buy = Integer.parseInt(currentPromotion.get(1));
        int get = Integer.parseInt(currentPromotion.get(2));
        String startDate = currentPromotion.get(3);
        String endDate = currentPromotion.get(4);

        addPromotion(promotions, name, buy, get, startDate, endDate);
    }

    private static List<String> parsePromotionLine(String inputProduct) {
        return Arrays.asList(inputProduct.split(","));
    }

    private static void addPromotion(Promotions promotions, String name, int buy, int get, String startDate, String endDate) {
        Promotion promotion = new Promotion(name, buy, get, startDate, endDate);
        promotions.add(promotion);
    }
}
