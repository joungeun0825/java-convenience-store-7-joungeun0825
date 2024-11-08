package store.domain.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.TotalProduct;
import store.domain.product.Products;
import store.domain.purchase.PurchaseProduct;
import store.domain.purchase.PurchaseProducts;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String INPUT_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String EMPTY_VALUE_ERROR_MESSAGE = "잘못된 입력입니다. 다시 입력해 주세요.";
    private static final String FORMAT_ERROR_MESSAGE = "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.";
    private static final String NOT_EXIST_PRODUCT = "존재하지 않는 상품입니다. 다시 입력해 주세요.";
    private static final String CANNOT_PURCHASE_PRODUCT = "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";
    private static final String PRODUCT_REGEX = "^\\[(.+)-(\\d+)]$";

    public static PurchaseProducts inputProducts() {
        System.out.println(INPUT_MESSAGE);
        try {
            List<String> purchaseProducts = convertToArray(Console.readLine().trim());
            return productFormatChecker(purchaseProducts);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputProducts();
        }
    }

    private static List<String> convertToArray(String inputProduct) {
        isEmpty(inputProduct);
        return Arrays.asList(inputProduct.split(",")).stream()
                .map(String::trim)
                .toList();
    }

    private static void isEmpty(String inputString) {
        if (inputString == null || inputString.isBlank()) {
            throw new IllegalArgumentException(EMPTY_VALUE_ERROR_MESSAGE);
        }
    }

    public static PurchaseProducts productFormatChecker(List<String> inputPurchaseProducts) {
        PurchaseProducts purchaseProducts = new PurchaseProducts();
        for (String inputPurchaseProduct : inputPurchaseProducts) {
            checkProduct(purchaseProducts, inputPurchaseProduct);
        }
        return purchaseProducts;
    }

    private static void checkProduct(PurchaseProducts purchaseProducts, String inputPurchaseProduct) {
        Pattern pattern = Pattern.compile(PRODUCT_REGEX);
        Matcher matcher = pattern.matcher(inputPurchaseProduct);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
        }
        String name = matcher.group(1);
        int quantity = Integer.parseInt(matcher.group(2));
        checkExistProduct(name, quantity);
        checkCanPurchase(name, quantity);
        purchaseProducts.add(new PurchaseProduct(name, quantity));
    }

    private static void checkExistProduct(String name, int quantity) {
        try {
            TotalProduct.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NOT_EXIST_PRODUCT);
        }
    }

    private static void checkCanPurchase(String name, int quantity){
        if (TotalProduct.valueOf(name).getProduct().getQuantity()< quantity) {
            throw new IllegalArgumentException(CANNOT_PURCHASE_PRODUCT);
        }
    }

}

