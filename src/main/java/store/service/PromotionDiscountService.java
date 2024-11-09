package store.service;

import store.domain.discount.PromotionDiscount;
import store.domain.product.PromotionProduct;
import store.domain.promotion.Promotion;
import store.domain.purchase.PurchaseProduct;
import store.domain.receipt.Receipt;
import store.view.AskCustomerInputView;

public class PromotionDiscountService {
    public static void applyPromotionDiscount(PromotionProduct promotionProduct, PurchaseProduct purchaseProduct) {
        Promotion promotion = promotionProduct.getPromotion();

        PromotionDiscount updatedDiscount = getDiscounts(promotionProduct, promotion, purchaseProduct);

        Receipt.addPromotionDiscounts(updatedDiscount);
        promotionProduct.decreasePromotionStock(purchaseProduct.getQuantity());
    }

    private static PromotionDiscount getDiscounts(PromotionProduct promotionProduct, Promotion promotion, PurchaseProduct purchaseProduct) {
        checkCanGetMoreProduct(promotionProduct, promotion, purchaseProduct);

        int needDiscountQuantity = promotion.calculateDiscountQuantity(purchaseProduct.getQuantity());
        int realDiscountQuantity = promotionProduct.calculateRealDiscountQuantity();

        PromotionDiscount promotionDiscount = new PromotionDiscount(purchaseProduct.getName(), needDiscountQuantity);
        return checkStockQuantity(promotion, purchaseProduct, promotionDiscount, realDiscountQuantity);
    }

    private static void checkCanGetMoreProduct(PromotionProduct promotionProduct, Promotion promotion, PurchaseProduct purchaseProduct) {
        int promotionCycle = promotion.getBuy() + promotion.getGet();
        boolean existMoreQuantity = promotionProduct.canGiveMore(purchaseProduct.getQuantity() + promotion.getGet());

        if (purchaseProduct.getQuantity() % promotionCycle == promotion.getBuy() && existMoreQuantity) {
            askCustomerToGetMoreProduct(promotion, purchaseProduct);
        }
    }

    private static PromotionDiscount checkStockQuantity(Promotion promotion, PurchaseProduct purchaseProduct, PromotionDiscount promotionDiscount, int realDiscountQuantity) {
        int totalSet = promotion.calculateTotalPromotionQuantity(realDiscountQuantity);
        if (totalSet < purchaseProduct.getQuantity()) {
            askCustomerToKeepGoing(purchaseProduct, purchaseProduct.getQuantity() - totalSet);
            return promotionDiscount.updatePromotionDiscountWithQuantity(realDiscountQuantity);
        }
        return promotionDiscount;
    }

    private static void askCustomerToGetMoreProduct(Promotion promotion, PurchaseProduct purchaseProduct) {
        boolean answer = AskCustomerInputView.askCustomerToGetMoreProduct(purchaseProduct.getName(), promotion.getGet());
        if (answer) {
            purchaseProduct.updatePurchaseWithQuantity(purchaseProduct.getQuantity() + promotion.getGet());
        }
    }

    private static void askCustomerToKeepGoing(PurchaseProduct purchaseProduct, int notDiscountProduct) {
        boolean answer = AskCustomerInputView.askCustomerToKeepGoing(purchaseProduct.getName(), notDiscountProduct);
        if (!answer) {
            purchaseProduct.updatePurchaseWithQuantity(purchaseProduct.getQuantity() - notDiscountProduct);
        }
    }
}
