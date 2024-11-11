package store.controller;

import store.domain.product.ProductLoader;
import store.domain.product.ProductManager;
import store.domain.promotion.PromotionLoader;
import store.domain.purchase.PurchaseManager;
import store.domain.receipt.Receipt;
import store.view.AskCustomerInputView;
import store.view.PurchaseProductInputView;
import store.view.OutputView;

public class StoreController {
    private boolean keepFlag = true;

    public void start(){
        init();
        run();
    }

    public void init() {
        new ProductManager();
        PromotionLoader.registerPromotion();
        ProductLoader.stockProducts();
    }

    public void run() {
        while(keepFlag){
            printStocks();
            startPurchase();
            keepFlag = AskCustomerInputView.askToBuyAgain();
        }
    }

    public void printStocks(){
        OutputView.printStartMessage();
    }

    public void startPurchase() {
        Receipt.initReceipt();
        PurchaseManager purchaseManager = PurchaseProductInputView.inputProducts();
        purchaseManager.purchaseProducts();
        OutputView.printReceipt(purchaseManager);
    }
}
