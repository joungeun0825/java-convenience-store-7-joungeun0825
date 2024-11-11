package store.discount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.discount.PromotionDiscount;
import store.domain.product.ProductManager;
import store.domain.product.PromotionProduct;
import store.domain.promotion.Promotion;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionDiscountTest {
    private Promotion promotion;
    private PromotionProduct promotionProduct;

    @BeforeEach
    void setUp() {
        promotion = new Promotion("탄산2+1");
        promotion.updatePromotion(2,1,"2024-01-01","2024-12-31");
        promotionProduct = new PromotionProduct("콜라", 1000, 2, promotion);
        ProductManager.putPromotionProduct("콜라", promotionProduct);
    }

    @Test
    @DisplayName("PromotionDiscount 생성 시 제품명과 수량이 올바르게 설정되고 가격이 계산되는지 테스트")
    void testPromotionDiscountCreation() {
        PromotionDiscount promotionDiscount = new PromotionDiscount("콜라", 1);

        assertEquals("콜라", promotionDiscount.getProductName(), "제품명이 올바르지 않습니다.");
        assertEquals(1, promotionDiscount.getQuantity(), "수량이 올바르지 않습니다.");
        assertEquals(1000, promotionDiscount.getPrice(), "할인 가격이 예상과 다릅니다.");
    }

    @Test
    @DisplayName("수량 변경 시 할인 가격이 올바르게 업데이트되는지 테스트")
    void testUpdatePromotionDiscountWithQuantity() {
        PromotionDiscount promotionDiscount = new PromotionDiscount("콜라", 1);
        promotionDiscount.updatePromotionDiscountWithQuantity(2);

        assertEquals(2, promotionDiscount.getQuantity(), "수량이 올바르지 않습니다.");
        assertEquals(2000, promotionDiscount.getPrice(), "변경 후 할인 가격이 예상과 다릅니다.");
    }
}
