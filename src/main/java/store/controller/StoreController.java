package store.controller;

import store.domain.product.ProductLoader;
import store.domain.promotion.PromotionLoader;
import store.domain.purchase.PurchaseManager;
import store.domain.receipt.Receipt;
import store.view.AskCustomerInputView;
import store.view.PurchaseProductInputView;
import store.view.OutputView;

public class StoreController {
    private static boolean keepFlag = true;

    public static void start(){
        init();
        run();
    }

    public static void init() {
        PromotionLoader.registerPromotion();
        ProductLoader.stockProducts();
    }

    public static void run() {
        while(keepFlag){
            printStocks();
            startPurchase();
            keepFlag = AskCustomerInputView.askToBuyAgain();
        }
    }

    public static void printStocks(){
        OutputView.printStartMessage();
    }

    public static void startPurchase() {
        Receipt.initReceipt();
        PurchaseManager purchaseManager = PurchaseProductInputView.inputProducts();
        purchaseManager.purchaseProducts();
        OutputView.printReceipt(purchaseManager);
    }
}
