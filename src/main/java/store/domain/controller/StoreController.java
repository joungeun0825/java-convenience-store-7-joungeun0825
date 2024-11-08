package store.domain.controller;

import store.domain.product.ProductLoader;
import store.domain.promotion.PromotionLoader;
import store.domain.purchase.PurchaseProducts;
import store.domain.purchase.PurchaseService;
import store.domain.view.InputView;
import store.domain.view.OutputView;

public class StoreController {

    public static void run(){
        PromotionLoader.registerPromotion();
        ProductLoader.stockProducts();
        OutputView.printStartMessage();
        PurchaseProducts purchaseProducts = InputView.inputProducts();
        PurchaseService.purchaseProducts(purchaseProducts);
        OutputView.printReceipt(purchaseProducts);
    }
}
