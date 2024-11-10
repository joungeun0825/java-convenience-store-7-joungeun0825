package store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.product.Product;
import store.domain.product.ProductManager;
import store.domain.product.PromotionProduct;
import store.domain.promotion.Promotion;
import store.domain.purchase.PurchaseProduct;

import static org.junit.jupiter.api.Assertions.*;

public class PromotionProductTest {
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
        purchaseProduct = new PurchaseProduct("콜라", 3);
    }

    @Test
    @DisplayName("구매량이 프로모션 주기의 조건을 만족하면 프로모션 적용 가능")
    void testCanApplyPromotion() {
        boolean result = promotionProduct.canApplyPromotion(promotion, purchaseProduct);
        assertFalse(result);
    }

    @Test
    @DisplayName("프로모션 주기를 초과하는 수량에서는 프로모션 적용 불가.")
    void testCannotApplyPromotion_InsufficientQuantity() {
        purchaseProduct = new PurchaseProduct("콜라", 9);
        boolean result = promotionProduct.canApplyPromotion(promotion, purchaseProduct);
        assertFalse(result);
    }

    @Test
    @DisplayName("프로모션에 따라 할인 수량을 계산")
    void testCalculateRealDiscountQuantity() {
        int discountQuantity = promotionProduct.calculateRealDiscountQuantity();
        assertEquals(3, discountQuantity);
    }

    @Test
    @DisplayName("할인 수량만큼 재고를 감소시킴")
    void testDecreasePromotionStock_WithSufficientStock() {
        promotionProduct.decreasePromotionStock(3);
        assertEquals(7, promotionProduct.getQuantity());
    }

    @Test
    @DisplayName("재고가 마이너스 되면 0으로 수정")
    void testDecreasePromotionStock_WithInsufficientStock() {
        promotionProduct.decreasePromotionStock(12);
        assertEquals(0, promotionProduct.getQuantity());
    }

    @Test
    @DisplayName("재고가 있을 때 true, 없을 때 false 반환")
    void testIsStockExist() {
        assertTrue(promotionProduct.isStockExist());

        promotionProduct.decreasePromotionStock(10);
        assertFalse(promotionProduct.isStockExist());
    }

    @Test
    @DisplayName("현재 프로모션 기간 내라면 프로모션은 활성화됨")
    void testIsActivePromotion() {
        assertTrue(promotionProduct.isActivePromotion());
    }

    @Test
    @DisplayName("할인 가격을 계산함")
    void testCalcPromotionDiscount() {
        int promotionQuantity = 3;
        int discount = promotionProduct.calcPromotionDiscount(promotionQuantity);
        assertEquals(3000, discount);
    }

    @Test
    @DisplayName("재고가 있으면 개수와 함께 출력")
    void testToString_WithStock() {
        String expectedOutput = "- 콜라 1,000원 10개 탄산2+1";
        assertEquals(expectedOutput, promotionProduct.toString());
    }

    @Test
    @DisplayName("재고가 없으면 재고 없음과 함께 출력")
    void testToString_EmptyStock() {
        promotionProduct.decreasePromotionStock(10);
        String expectedOutput = "- 콜라 1,000원 재고 없음 탄산2+1";
        assertEquals(expectedOutput, promotionProduct.toString());
    }
}
