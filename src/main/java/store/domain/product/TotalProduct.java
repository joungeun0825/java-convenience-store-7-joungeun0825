package store.domain.product;

public enum TotalProduct {
    콜라(new Product("콜라")),
    사이다(new Product("사이다")),
    오렌지주스(new Product("오렌지주스")),
    탄산수(new Product("탄산수")),
    물(new Product("물")),
    비타민워터(new Product("비타민워터")),
    감자칩(new Product("감자칩")),
    초코바(new Product("초코바")),
    에너지바(new Product("에너지바")),
    정식도시락(new Product("정식도시락")),
    컵라면(new Product("컵라면"));

    private final Product product;

    TotalProduct(Product product) {
        this.product = product;
    }

    public void updateProductPriceWithPromotionProductPrice() {
        if (this.product.getPrice() == 0) {
            this.product.updatePrice(this.product.getPromotionProduct().getPrice());
        }
    }

    public boolean checkValidPromotionProduct() {
        return this.product.existValidPromotion();
    }

    public Product getProduct() {
        return product;
    }
}
