package store.service;

import store.domain.purchase.PurchaseProduct;
import store.domain.receipt.Receipt;

import java.util.List;

public class MembershipDiscountService {
    public static void applyMembershipDiscount(List<PurchaseProduct> purchaseProducts) {
        int totalPrice = 0;
        for (PurchaseProduct purchaseProduct : purchaseProducts) {
            totalPrice += purchaseProduct.checkPromotionAndGetPrice();
        }
        int membershipDiscountPrice = Math.min((int) (totalPrice * 0.3), 8000);
        Receipt.applyMembershipDiscounts(membershipDiscountPrice);
    }
}
