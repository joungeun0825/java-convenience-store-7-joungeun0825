package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.product.Product;
import store.domain.product.ProductRegistry;
import store.domain.product.ProductType;
import store.domain.product.PromotionProduct;
import store.domain.purchase.PurchaseProduct;
import store.domain.purchase.PurchaseProducts;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseProductInputView {
    private static final String INPUT_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String EMPTY_VALUE_ERROR_MESSAGE = "잘못된 입력입니다. 다시 입력해 주세요.";
    private static final String FORMAT_ERROR_MESSAGE = "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.";
    private static final String NOT_EXIST_PRODUCT = "존재하지 않는 상품입니다. 다시 입력해 주세요.";
    private static final String CANNOT_PURCHASE_PRODUCT = "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";
    private static final String INPUT_OVER_ZERO = "0 이상의 구매량을 입력하세요";
    private static final String PRODUCT_INPUT_REGEX = "^\\[(.+)-(\\d+)]$";

    public static PurchaseProducts inputProducts() {
        System.out.println(INPUT_MESSAGE);
        try {
            return processInput(Console.readLine().trim());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputProducts();
        }
    }

    private static PurchaseProducts processInput(String input) {
        validateNonEmptyInput(input);
        List<String> purchaseProductList = Arrays.asList(input.split(","))
                .stream()
                .map(String::trim)
                .toList();
        return createPurchaseProducts(purchaseProductList);
    }

    private static void validateNonEmptyInput(String inputString) {
        if (inputString == null || inputString.isBlank()) {
            throw new IllegalArgumentException(EMPTY_VALUE_ERROR_MESSAGE);
        }
    }

    private static PurchaseProducts createPurchaseProducts(List<String> inputProducts) {
        PurchaseProducts purchaseProducts = new PurchaseProducts();
        for (String input : inputProducts) {
            purchaseProducts.add(parsePurchaseProduct(input));
        }
        return purchaseProducts;
    }

    private static PurchaseProduct parsePurchaseProduct(String inputProduct) {
        Matcher matcher = createMatcher(inputProduct);
        String name = extractProductName(matcher);
        int quantity = extractProductQuantity(matcher);
        validateProductExists(name);
        validatePurchaseQuantity(name, quantity);
        return new PurchaseProduct(name, quantity);
    }

    private static Matcher createMatcher(String inputProduct) {
        Pattern pattern = Pattern.compile(PRODUCT_INPUT_REGEX);
        Matcher matcher = pattern.matcher(inputProduct);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
        }
        return matcher;
    }

    private static String extractProductName(Matcher matcher) {
        return matcher.group(1);
    }

    private static int extractProductQuantity(Matcher matcher) {
        return parsePositiveInt(matcher.group(2));
    }

    private static void validateProductExists(String name) {
        try {
            ProductType.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NOT_EXIST_PRODUCT);
        }
    }

    private static void validatePurchaseQuantity(String name, int quantity) {
        Product product = ProductRegistry.getProduct(name);
        PromotionProduct promotionProduct = ProductRegistry.getPromotionProduct(name);
        int availableProductQuantity = product.getQuantity();
        if (ProductRegistry.existValidPromotion(name)) {
            availableProductQuantity += promotionProduct.getQuantity();
        }
        if (availableProductQuantity < quantity) {
            throw new IllegalArgumentException(CANNOT_PURCHASE_PRODUCT);
        }
    }

    private static int parsePositiveInt(String value) {
        int quantity = Integer.parseInt(value);
        if (quantity <= 0) {
            throw new IllegalArgumentException(INPUT_OVER_ZERO);
        }
        return quantity;
    }
}

