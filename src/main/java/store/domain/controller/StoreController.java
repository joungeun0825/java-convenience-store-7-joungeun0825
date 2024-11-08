package store.domain.controller;

import store.domain.product.ProductLoader;
import store.domain.product.Products;
import store.domain.product.PromotionProducts;
import store.domain.promotion.PromotionLoader;
import store.domain.purchase.PurchaseProducts;
import store.domain.purchase.PurchaseService;
import store.domain.view.InputView;
import store.domain.view.OutputView;

public class StoreController {

    public static void run(){
        Products products = new Products();
        PromotionProducts promotionProducts = new PromotionProducts();
        ProductLoader.stockProducts(products,promotionProducts);
        PromotionLoader.registerPromotion();
        OutputView.printStartMessage();
        PurchaseProducts purchaseProducts = InputView.inputProducts(products);
        PurchaseService.purchaseProducts(purchaseProducts);
        OutputView.printReceipt(purchaseProducts);
    }
}
