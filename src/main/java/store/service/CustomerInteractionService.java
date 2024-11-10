package store.service;

import store.domain.promotion.Promotion;
import store.domain.purchase.PurchaseProduct;
import store.view.AskCustomerInputView;

public class CustomerInteractionService {
    public static void handleExtraPromotion(Promotion promotion, PurchaseProduct purchaseProduct) {
        boolean answer = AskCustomerInputView.askCustomerToGetMoreProduct(purchaseProduct.getName(), promotion.getGet());
        if (answer) {
            purchaseProduct.updatePurchaseWithQuantity(purchaseProduct.getQuantity() + promotion.getGet());
        }
    }

    public static void confirmPurchaseWithCustomer(PurchaseProduct purchaseProduct, int notDiscountProduct) {
        boolean answer = AskCustomerInputView.askCustomerToKeepGoing(purchaseProduct.getName(), notDiscountProduct);
        if (!answer) {
            purchaseProduct.updatePurchaseWithQuantity(purchaseProduct.getQuantity() - notDiscountProduct);
        }
    }
}
