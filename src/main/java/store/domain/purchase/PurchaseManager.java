package store.domain.purchase;

import store.domain.product.Product;
import store.domain.product.ProductRegistry;
import store.domain.product.PromotionProduct;
import store.domain.receipt.Receipt;
import store.service.MembershipDiscountService;
import store.service.PromotionDiscountService;
import store.view.AskCustomerInputView;

import java.util.ArrayList;
import java.util.List;

public class PurchaseManager {
    private static final String PURCHASE_FORMAT = "%s\t\t%d \t%s";
    private static final String NEW_LINE = "\n";

    private List<PurchaseProduct> purchaseProducts;

    public PurchaseManager() {
        this.purchaseProducts = new ArrayList<>();
    }

    public void addPurchaseProduct(PurchaseProduct purchaseProduct) {
        this.purchaseProducts.add(purchaseProduct);
    }

    public void purchaseProducts() {
        for (PurchaseProduct purchaseProduct : this.purchaseProducts) {
            applyPromotionDiscount(purchaseProduct);
            Receipt.addPurchaseProduct(purchaseProduct);
        }
        applyMembershipDiscount(this.purchaseProducts);
    }

    private static void applyPromotionDiscount(PurchaseProduct purchaseProduct) {
        Product product = ProductRegistry.getProduct(purchaseProduct.getName());
        PromotionProduct promotionProduct = ProductRegistry.getPromotionProduct(purchaseProduct.getName());
        if (ProductRegistry.existValidPromotion(purchaseProduct.getName())) {
            PromotionDiscountService.applyPromotionDiscount(promotionProduct, purchaseProduct);
            return;
        }
        product.updatePurchase(purchaseProduct.getQuantity());
    }

    private static void applyMembershipDiscount(List<PurchaseProduct> purchaseProducts) {
        if (AskCustomerInputView.askCustomerToApplyMembership()) {
            MembershipDiscountService.applyMembershipDiscount(purchaseProducts);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PurchaseProduct purchaseProduct : this.purchaseProducts) {
            sb.append(String.format(PURCHASE_FORMAT, purchaseProduct.getName(), purchaseProduct.getQuantity(), String.format("%,d", purchaseProduct.getPrice())));
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }
}
