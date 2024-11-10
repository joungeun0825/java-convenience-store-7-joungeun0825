package store.view;

import store.domain.product.*;
import store.domain.discount.PromotionDiscount;
import store.domain.purchase.PurchaseProduct;
import store.domain.purchase.PurchaseProducts;
import store.domain.receipt.Receipt;

public class OutputView {
    private static final String START_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.";

    private static final String ERROR_MESSAGE = "[ERROR] ";

    private static final String START_RECEIPT_MESSAGE = "==============W 편의점================";
    private static final String PURCHASE_MESSAGE = "상품명\t\t수량 \t금액";
    private static final String PURCHASE_FORMAT = "%s\t\t%d \t%s";

    private static final String PROMOTION_RECEIPT_MESSAGE = "=============증	정===============";
    private static final String PROMOTION_FORMAT = "%s\t\t%d";

    private static final String STATICS_RECEIPT_MESSAGE = "====================================";
    private static final String TOTAL_PURCHASE_PRICE = "총구매액\t\t%d\t%s";
    private static final String PROMOTION_DISCOUNT = "행사할인\t\t-%s";
    private static final String MEMBERSHIP_DISCOUNT = "멤버십할인\t\t-%s";
    private static final String TOTAL_PAY_DISCOUNT = "내실돈\t\t%s";

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
        printStocks();
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE + message);
    }

    public static void printReceipt(PurchaseProducts purchaseProducts) {
        System.out.println(START_RECEIPT_MESSAGE);
        printPurchase(purchaseProducts);
        printPromotion();
        printStatics();
    }

    private static void printStocks() {
        for (ProductType productType : ProductType.values()) {
            Product product = ProductRegistry.getProduct(String.valueOf(productType));
            PromotionProduct promotionProduct = ProductRegistry.getPromotionProduct(String.valueOf(productType));
            if (promotionProduct != null) {
                printPromotionProducts(promotionProduct);
            }
            printProducts(product);
        }
    }

    private static void printPromotionProducts(PromotionProduct promotionProduct) {
        System.out.println(promotionProduct.toString());
    }

    private static void printProducts(Product product) {
        System.out.println(product.toString());
    }

    private static void printPurchase(PurchaseProducts purchaseProducts) {
        System.out.println(PURCHASE_MESSAGE);
        for (PurchaseProduct purchaseProduct : purchaseProducts.getProducts()) {
            System.out.println(String.format(PURCHASE_FORMAT,
                    purchaseProduct.getName(),
                    purchaseProduct.getQuantity(),
                    String.format("%,d", purchaseProduct.getPrice())
            ));
        }
    }

    private static void printPromotion() {
        System.out.println(PROMOTION_RECEIPT_MESSAGE);
        for (PromotionDiscount promotionDiscount : Receipt.getPromotionDiscounts()) {
            System.out.println(String.format(PROMOTION_FORMAT,
                    promotionDiscount.getProductName(),
                    promotionDiscount.getQuantity()
            ));
        }
    }

    private static void printStatics() {
        System.out.println(STATICS_RECEIPT_MESSAGE);
        System.out.println(String.format(TOTAL_PURCHASE_PRICE, Receipt.getTotalPurchaseSize(), String.format("%,d", Receipt.getTotalPurchasePrice())));
        System.out.println(String.format(PROMOTION_DISCOUNT, String.format("%,d", Receipt.getPromotionDiscountsPrice())));
        System.out.println(String.format(MEMBERSHIP_DISCOUNT, String.format("%,d", Receipt.getMembershipDiscountPrice())));
        System.out.println(String.format(TOTAL_PAY_DISCOUNT, String.format("%,d", Receipt.getTotalPayPrice())));
    }
}
