package store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.discount.PromotionDiscount;
import store.domain.product.Product;
import store.domain.product.ProductManager;
import store.domain.product.PromotionProduct;
import store.domain.promotion.Promotion;
import store.domain.purchase.PurchaseProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PurchaseProductTest {
    private Promotion promotion;
    private Product product;
    private PromotionProduct promotionProduct;
    private PurchaseProduct purchaseProduct;

    @BeforeEach
    void setUp() {
        promotion = new Promotion("탄산2+1");
        promotion.updatePromotion(2,1,"2024-01-01","2024-12-31");
        promotionProduct = new PromotionProduct("콜라", 1000, 10, promotion);
        product = new Product("콜라");
        product.updatePrice(1000);
        ProductManager.putProduct("콜라", product);
        ProductManager.putPromotionProduct("콜라", promotionProduct);
        purchaseProduct = new PurchaseProduct("콜라", 2);
    }

    @Test
    @DisplayName("가격이 정상적으로 계산되는지 테스트")
    void testCalcPrice() {
        assertEquals(2000, purchaseProduct.getPrice(), "상품 가격이 잘못 계산되었습니다.");
    }

    @Test
    @DisplayName("수량 변경 시 가격이 업데이트되는지 테스트")
    void testUpdatePurchaseWithQuantity() {
        purchaseProduct.updatePurchaseWithQuantity(3);
        assertEquals(3000, purchaseProduct.getPrice(), "수량을 변경한 후 가격이 잘못 계산되었습니다.");
    }

    @Test
    @DisplayName("할인 적용이 정상적으로 이루어지는지 테스트")
    void testApplyPromotionDiscount() {
        PromotionDiscount promotionDiscount = new PromotionDiscount("콜라",50);
        purchaseProduct.applyPromotionDiscount(promotionDiscount);

        assertNotNull(purchaseProduct.checkPromotionAndGetPrice(), "할인이 적용되지 않았습니다.");
    }

    @Test
    @DisplayName("할인 없이 가격을 확인할 때 원래 가격이 반환되는지 테스트")
    void testCheckPromotionAndGetPriceWithoutDiscount() {
        assertEquals(2000, purchaseProduct.checkPromotionAndGetPrice(), "할인이 없는 경우 원래 가격이 잘못 반환되었습니다.");
    }

    @Test
    @DisplayName("할인 적용 후 가격이 정상적으로 반영되는지 테스트")
    void testCheckPromotionAndGetPriceWithDiscount() {
        PromotionDiscount promotionDiscount = new PromotionDiscount("콜라",50);
        purchaseProduct.applyPromotionDiscount(promotionDiscount);

        assertEquals(0, purchaseProduct.checkPromotionAndGetPrice(), "할인 적용 후 가격이 잘못 계산되었습니다.");
    }

    @Test
    @DisplayName("할인 가격이 0일 때 할인 적용이 되지 않는지 테스트")
    void testApplyPromotionDiscountWithInvalidDiscount() {
        PromotionDiscount promotionDiscount = new PromotionDiscount("콜라",0);
        purchaseProduct.applyPromotionDiscount(promotionDiscount);

        assertEquals(2000, purchaseProduct.checkPromotionAndGetPrice(), "할인 가격이 0일 때 가격이 잘못 계산되었습니다.");
    }
}
