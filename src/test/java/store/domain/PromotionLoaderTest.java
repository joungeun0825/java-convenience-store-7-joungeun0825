package store.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.promotion.PromotionLoader;
import store.domain.promotion.Promotions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionLoaderTest {
    private static final String FILE_PATH = "src/main/resources/promotions.md";
    private Promotions promotions;

    @BeforeEach
    void setUp() throws IOException {
        promotions = new Promotions();
        PromotionLoader.registerPromotion(FILE_PATH, promotions);
    }

    @Test
    @DisplayName("프로모션의 구매량이 파일과 일치한다.")
    void testPromotionBuy() {
        assertEquals(2, promotions.getBuyByPromotion("탄산2+1"));
        assertEquals(1, promotions.getBuyByPromotion("MD추천상품"));
        assertEquals(1, promotions.getBuyByPromotion("반짝할인"));
    }

    @Test
    @DisplayName("프로모션의 증정량이 파일과 일치한다.")
    void testPromotionGet() {
        assertEquals(1, promotions.getGetByPromotion("탄산2+1"));
        assertEquals(1, promotions.getGetByPromotion("MD추천상품"));
        assertEquals(1, promotions.getGetByPromotion("반짝할인"));
    }

    @Test
    @DisplayName("프로모션의 시작 날짜가 파일과 일치한다.")
    void testPromotionStartDate() {
        assertEquals("2024-01-01", promotions.getStartDateByPromotion("탄산2+1"));
        assertEquals("2024-01-01", promotions.getStartDateByPromotion("MD추천상품"));
        assertEquals("2024-11-01", promotions.getStartDateByPromotion("반짝할인"));
    }

    @Test
    @DisplayName("프로모션의 끝나는 날짜가 파일과 일치한다.")
    void testPromotionEndDate() {
        assertEquals("2024-12-31", promotions.getEndDateByPromotion("탄산2+1"));
        assertEquals("2024-12-31", promotions.getEndDateByPromotion("MD추천상품"));
        assertEquals("2024-11-30", promotions.getEndDateByPromotion("반짝할인"));
    }
}
