package store.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductLoaderTest {
    private static final String FILE_PATH = "src/main/resources/products.md";
    private Products products;
    private PromotionProducts promotionProducts;

    @BeforeEach
    void setUp() throws IOException {
        products = new Products();
        promotionProducts = new PromotionProducts();
        ProductLoader.stockProducts(FILE_PATH, products, promotionProducts);
    }

    @Test
    @DisplayName("적재한 일반 제품의 가격이 파일과 일치한다.")
    void testProductPrices() {
        assertEquals(1000, products.getPriceByName("콜라"));
        assertEquals(1000, products.getPriceByName("사이다"));
        assertEquals(1500, products.getPriceByName("비타민워터"));
        assertEquals(1500, products.getPriceByName("감자칩"));
        assertEquals(1200, products.getPriceByName("초코바"));
        assertEquals(2000, products.getPriceByName("에너지바"));
        assertEquals(6400, products.getPriceByName("정식도시락"));
        assertEquals(1700, products.getPriceByName("컵라면"));
    }

    @Test
    @DisplayName("적재한 일반 제품의 수량이 파일과 일치한다.")
    void testProductQuantities() {
        assertEquals(10, products.getQuantityByName("콜라"));
        assertEquals(7, products.getQuantityByName("사이다"));
        assertEquals(6, products.getQuantityByName("비타민워터"));
        assertEquals(5, products.getQuantityByName("감자칩"));
        assertEquals(5, products.getQuantityByName("초코바"));
        assertEquals(5, products.getQuantityByName("에너지바"));
        assertEquals(8, products.getQuantityByName("정식도시락"));
        assertEquals(10, products.getQuantityByName("컵라면"));
    }

    @Test
    @DisplayName("적재한 프로모션 제품의 가격이 파일과 일치한다.")
    void testPromotionProductPrices() {
        assertEquals(1000, promotionProducts.getPriceByName("콜라"));
        assertEquals(1000, promotionProducts.getPriceByName("사이다"));
        assertEquals(1800, promotionProducts.getPriceByName("오렌지주스"));
        assertEquals(1200, promotionProducts.getPriceByName("탄산수"));
        assertEquals(1500, promotionProducts.getPriceByName("감자칩"));
        assertEquals(1200, promotionProducts.getPriceByName("초코바"));
        assertEquals(1700, promotionProducts.getPriceByName("컵라면"));
    }

    @Test
    @DisplayName("적재한 프로모션 제품의 수량이 파일과 일치한다.")
    void testPromotionProductQuantities() {
        assertEquals(10, promotionProducts.getQuantityByName("콜라"));
        assertEquals(8, promotionProducts.getQuantityByName("사이다"));
        assertEquals(9, promotionProducts.getQuantityByName("오렌지주스"));
        assertEquals(5, promotionProducts.getQuantityByName("탄산수"));
        assertEquals(5, promotionProducts.getQuantityByName("감자칩"));
        assertEquals(5, promotionProducts.getQuantityByName("초코바"));
        assertEquals(1, promotionProducts.getQuantityByName("컵라면"));
    }

    @Test
    @DisplayName("적재한 프로모션 제품의 프로모션이 파일과 일치한다.")
    void testPromotionProductPromotions() {
        assertEquals("탄산2+1", promotionProducts.getPromotionByName("콜라"));
        assertEquals("탄산2+1", promotionProducts.getPromotionByName("사이다"));
        assertEquals("MD추천상품", promotionProducts.getPromotionByName("오렌지주스"));
        assertEquals("탄산2+1", promotionProducts.getPromotionByName("탄산수"));
        assertEquals("반짝할인", promotionProducts.getPromotionByName("감자칩"));
        assertEquals("MD추천상품", promotionProducts.getPromotionByName("초코바"));
        assertEquals("MD추천상품", promotionProducts.getPromotionByName("컵라면"));
    }
}
