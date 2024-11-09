package store;

import store.controller.StoreController;
import store.domain.product.ProductLoader;
import store.domain.promotion.PromotionLoader;
import store.view.AskCustomerInputView;

public class Application {
    private static boolean keepFlag = true;

    public static void main(String[] args) {
        init();
        run();
    }

    private static void init() {
        PromotionLoader.registerPromotion();
        ProductLoader.stockProducts();
    }

    private static void run() {
        while(keepFlag){
            StoreController.startPurchase();
            keepFlag = AskCustomerInputView.askToBuyAgain();
        }
    }
}
