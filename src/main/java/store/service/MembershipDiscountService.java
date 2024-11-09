package store.service;

import store.domain.purchase.PurchaseProduct;
import store.domain.purchase.PurchaseProducts;
import store.domain.receipt.Receipt;

public class MembershipDiscountService {
    public static void applyMembershipDiscount(PurchaseProducts purchaseProducts) {
        int totalPrice = 0;
        for (PurchaseProduct purchaseProduct : purchaseProducts.getProducts()) {
            totalPrice += purchaseProduct.checkPromotionAndGetPrice();
        }
        int membershipDiscountPrice = Math.min((int) (totalPrice * 0.3), 8000);
        Receipt.applyMembershipDiscounts(membershipDiscountPrice);
    }
}
