package store.domain.discount;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;
import store.domain.product.PromotionProducts;
import store.domain.promotion.Promotion;
import store.domain.promotion.Promotions;
import store.domain.purchase.PurchaseProduct;
import store.domain.receipt.Receipt;

public class PromotionDiscountManager {
    public static void applyPromotionDiscount(PurchaseProduct purchaseProduct) {
        Promotion promotion = checkAvailablePromotion(purchaseProduct);
        if (promotion == null || !isPromotionActive(promotion)) {
            return;
        }
        getDiscounts(promotion, purchaseProduct);
    }

    private static Promotion checkAvailablePromotion(PurchaseProduct purchaseProduct) {
        String promotionName = PromotionProducts.getPromotionByName(purchaseProduct.getName());
        return Promotions.getPromotionByName(promotionName);
    }

    private static boolean isPromotionActive(Promotion promotion) {
        return promotion.isWithinPromotionPeriod(DateTimes.now());
    }

    private static void getDiscounts(Promotion promotion, PurchaseProduct purchaseProduct) {
        checkCanGetMoreProduct(promotion, purchaseProduct);
        int leftPromotionStock = PromotionProducts.getQuantityByName(purchaseProduct.getName());
        int needDiscountQuantity = promotion.calculateDiscountQuantity(purchaseProduct.getQuantity());
        int realDiscountQuantity = promotion.calculateDiscountQuantity(leftPromotionStock);
        PromotionDiscount promotionDiscount = new PromotionDiscount(purchaseProduct.getName(), needDiscountQuantity);
        PromotionDiscount updatedDiscount = checkStockQuantity(promotion, purchaseProduct, promotionDiscount, realDiscountQuantity);
        Receipt.addPromotionDiscounts(updatedDiscount);
        PromotionProducts.getPromotionProducts().get(purchaseProduct.getName()).decreasePromotionStock(purchaseProduct.getQuantity());
    }

    private static PromotionDiscount checkStockQuantity(Promotion promotion, PurchaseProduct purchaseProduct, PromotionDiscount promotionDiscount, int realDiscountQuantity) {
        int totalSet = promotion.calculateTotalPromotionQuantity(realDiscountQuantity);
        if (totalSet < purchaseProduct.getQuantity()) {
            askCustomerToKeepGoing(purchaseProduct, purchaseProduct.getQuantity() - totalSet);
            return promotionDiscount.updatePromotionDiscountWithQuantity(realDiscountQuantity);
        }
        return promotionDiscount;
    }

    private static void askCustomerToKeepGoing(PurchaseProduct purchaseProduct, int notDiscountProduct) {
        System.out.println(String.format("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)",
                purchaseProduct.getName(), notDiscountProduct));
        String answer = Console.readLine().trim().toUpperCase();
        if ("N".equals(answer)) {
            purchaseProduct.updatePurchaseWithQuantity(purchaseProduct.getQuantity()-notDiscountProduct);
        }
    }

    private static void checkCanGetMoreProduct(Promotion promotion, PurchaseProduct purchaseProduct) {
        int promotionCycle = promotion.getBuy() + promotion.getGet();
        if (purchaseProduct.getQuantity() % promotionCycle == promotion.getBuy()) {
            askCustomerToGetMoreProduct(promotion, purchaseProduct);
        }
    }

    private static void askCustomerToGetMoreProduct(Promotion promotion, PurchaseProduct purchaseProduct) {
        System.out.println(String.format("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)",
                purchaseProduct.getName(), promotion.getGet()));
        String answer = Console.readLine().trim().toUpperCase();
        if ("Y".equals(answer)) {
            purchaseProduct.updatePurchaseWithQuantity(purchaseProduct.getQuantity() + promotion.getGet());
        }
    }
}
