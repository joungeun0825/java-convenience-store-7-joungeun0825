package store.domain.receipt;

import store.domain.discount.MembershipDiscount;
import store.domain.discount.PromotionDiscount;
import store.domain.purchase.PurchaseProduct;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private static List<PurchaseProduct> realPurchaseProducts = new ArrayList<>();
    private static List<PromotionDiscount> promotionDiscounts = new ArrayList<>();
    private static MembershipDiscount membershipDiscount = new MembershipDiscount();
    private static int totalPurchasePrice = 0;
    private static int totalPayPrice = 0;

    public static void initReceipt() {
        realPurchaseProducts = new ArrayList<>();
        promotionDiscounts = new ArrayList<>();
        membershipDiscount = new MembershipDiscount();
        totalPurchasePrice = 0;
        totalPayPrice = 0;
    }

    public static void addPurchaseProduct(PurchaseProduct purchaseProduct) {
        realPurchaseProducts.add(purchaseProduct);
        totalPurchasePrice += purchaseProduct.getPrice();
        totalPayPrice += purchaseProduct.getPrice();
    }

    public static void addPromotionDiscounts(PromotionDiscount promotionDiscount) {
        if (promotionDiscount.getPrice() > 0) {
            promotionDiscounts.add(promotionDiscount);
            totalPayPrice -= promotionDiscount.getPrice();
        }
    }

    public static void applyMembershipDiscounts(int discountPrice) {
        membershipDiscount.applyMembershipDiscount(discountPrice);
        totalPayPrice -= discountPrice;
    }

    public static List<PromotionDiscount> getPromotionDiscounts() {
        return promotionDiscounts;
    }

    public static int getPromotionDiscountsPrice() {
        return promotionDiscounts.stream()
                .mapToInt(PromotionDiscount::getPrice)
                .sum();
    }

    public static int getTotalPurchasePrice(){
        return totalPurchasePrice;
    }

    public static int getTotalPurchaseSize() {
        return realPurchaseProducts.stream()
                .mapToInt(PurchaseProduct::getQuantity)
                .sum();
    }

    public static int getMembershipDiscountPrice(){
        return membershipDiscount.getDiscountPrice();
    }

    public static int getTotalPayPrice(){
        return totalPayPrice;
    }

}
