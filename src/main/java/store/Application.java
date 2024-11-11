package store;

import store.controller.StoreController;
import store.domain.product.ProductLoader;
import store.domain.promotion.PromotionLoader;
import store.view.AskCustomerInputView;

public class Application {
    public static void main(String[] args) {
        StoreController storeController = new StoreController();
        storeController.start();
    }
}
