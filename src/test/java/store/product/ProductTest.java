package store.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.product.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("콜라", 1000, 10);
    }

    @Test
    @DisplayName("상품 재고가 5만큼 증가했을 때, 재고는 15여야 합니다.")
    void testAdjustMainProductStock_increaseQuantity() {
        product.adjustMainProductStock(5);
        assertEquals(15, product.getQuantity());
    }

    @Test
    @DisplayName("상품 재고가 3만큼 감소했을 때, 재고는 7여야 합니다.")
    void testAdjustMainProductStock_decreaseQuantity() {
        product.adjustMainProductStock(-3);
        assertEquals(7, product.getQuantity());
    }

    @Test
    @DisplayName("상품을 4개 구매했을 때, 재고는 6여야 합니다.")
    void testAdjustPurchase_decreaseQuantity() {
        product.adjustPurchase(4);
        assertEquals(6, product.getQuantity());
    }

    @Test
    @DisplayName("상품 재고보다 더 많은 수량을 구매했을 때, 재고는 -2여야 합니다.")
    void testAdjustPurchase_exceedStock() {
        product.adjustPurchase(12);
        assertEquals(-2, product.getQuantity());
    }

    @Test
    @DisplayName("상품 재고가 있을 때, 상품 개수와 함께 출력합니다.")
    void testToString_withStock() {
        String expectedOutput = "- 콜라 1,000원 10개";
        assertEquals(expectedOutput, product.toString());
    }

    @Test
    @DisplayName("상품 재고가 없을 때, 재고없음 메시지와 함께 출력합니다.")
    void testToString_withoutStock() {
        product.adjustPurchase(10);  // 재고를 0으로 만든 후 확인
        String expectedOutput = "- 콜라 1,000원 재고 없음";
        assertEquals(expectedOutput, product.toString());
    }
}
