package store.domain;

import store.domain.product.Product;
import store.domain.product.PromotionProduct;

public enum TotalProduct {
    콜라(new Product(), new PromotionProduct()),
    사이다(new Product(), new PromotionProduct()),
    오렌지주스(new Product(), new PromotionProduct()),
    탄산수(new Product(), new PromotionProduct()),
    물(new Product(), new PromotionProduct()),
    비타민워터(new Product(), new PromotionProduct()),
    감자칩(new Product(), new PromotionProduct()),
    초코바(new Product(), new PromotionProduct()),
    에너지바(new Product(), new PromotionProduct()),
    정식도시락(new Product(), new PromotionProduct()),
    컵라면(new Product(), new PromotionProduct());

    private final Product product;
    private final PromotionProduct promotionProduct;

    TotalProduct(Product product, PromotionProduct promotionProduct){
        this.product = product;
        this.promotionProduct = promotionProduct;
    }

    public Product getProduct() {
        return product;
    }

    public PromotionProduct getPromotionProduct() {
        return promotionProduct;
    }
}
