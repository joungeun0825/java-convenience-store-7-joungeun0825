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

        PromotionDiscount updatedDiscount = calculateDiscount(promotionProduct, promotion, purchaseProduct);

        Receipt.addPromotionDiscounts(updatedDiscount);
        purchaseProduct.applyPromotionDiscount(updatedDiscount);
        promotionProduct.decreasePromotionStock(purchaseProduct.getQuantity());
    }

    private static PromotionDiscount calculateDiscount(PromotionProduct promotionProduct, Promotion promotion, PurchaseProduct purchaseProduct) {
        if (promotionProduct.canApplyPromotion(promotion, purchaseProduct)) {
            CustomerInteractionService.handleExtraPromotion(promotion, purchaseProduct);
        }

        int requiredForDiscountQuantity = promotion.calculateDiscountQuantity(purchaseProduct.getQuantity());
        int maxDiscountQuantity = promotionProduct.calculateRealDiscountQuantity();

        PromotionDiscount promotionDiscount = new PromotionDiscount(purchaseProduct.getName(), requiredForDiscountQuantity);
        return checkAndAdjustStockQuantity(promotion, purchaseProduct, promotionDiscount, maxDiscountQuantity);
    }

    private static PromotionDiscount checkAndAdjustStockQuantity(Promotion promotion, PurchaseProduct purchaseProduct, PromotionDiscount promotionDiscount, int realDiscountQuantity) {
        int maxPurchasableWithDiscount = promotion.calculateTotalPromotionQuantity(realDiscountQuantity);
        if (maxPurchasableWithDiscount < purchaseProduct.getQuantity()) {
            CustomerInteractionService.confirmPurchaseWithCustomer(purchaseProduct, purchaseProduct.getQuantity() - maxPurchasableWithDiscount);
            return promotionDiscount.updatePromotionDiscountWithQuantity(realDiscountQuantity);
        }
        return promotionDiscount;
    }
}
