package store.controller;

import store.domain.purchase.PurchaseProducts;
import store.service.PurchaseService;
import store.domain.receipt.Receipt;
import store.view.PurchaseProductInputView;
import store.view.OutputView;

public class StoreController {

    public static void startPurchase() {
        OutputView.printStartMessage();
        Receipt.initReceipt();
        PurchaseProducts purchaseProducts = PurchaseProductInputView.inputProducts();
        PurchaseService.purchaseProducts(purchaseProducts);
        OutputView.printReceipt(purchaseProducts);
    }
}
