package store.domain.receipt;

import store.domain.discount.MembershipDiscount;
import store.domain.discount.PromotionDiscount;
import store.domain.purchase.PurchaseProduct;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private static List<PurchaseProduct> realPurchaseProducts = new ArrayList<>();
    private static List<PromotionDiscount> promotionDiscounts = new ArrayList<>();
    private static List<MembershipDiscount> membershipDiscounts = new ArrayList<>();
    private static int totalPrice = 0;

    public static void addPurchaseProduct(PurchaseProduct purchaseProduct){
        realPurchaseProducts.add(purchaseProduct);
    }

    public static void addPromotionDiscounts(PromotionDiscount promotionDiscount){
        promotionDiscounts.add(promotionDiscount);
    }

    public static void addMembershipDiscounts(MembershipDiscount membershipDiscount){
        membershipDiscounts.add(membershipDiscount);
    }

    public static List<PromotionDiscount> getPromotionDiscounts(){
        return promotionDiscounts;
    }
}
