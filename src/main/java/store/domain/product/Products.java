package store.domain.product;

import java.util.HashMap;
import java.util.Map;

public class Products {
    private static final String NOT_EXIST_PRODUCT = "존재하지 않는 상품입니다. 다시 입력해 주세요.";
    private static final String CANNOT_PURCHASE_PRODUCT = "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";
    private Map<String, Product> products;

    public Products() {
        this.products = new HashMap<>();
    }

    public void add(Product product) {
        products.put(product.getName(), product);
    }

    public void checkExistProduct(String name) {
        if (!products.containsKey(name)) {
            throw new IllegalArgumentException(NOT_EXIST_PRODUCT);
        }
    }

    public void checkCanPurchase(String name, int quantity) {
        if (products.get(name).getQuantity() < quantity) {
            throw new IllegalArgumentException(CANNOT_PURCHASE_PRODUCT);
        }
    }

    public int getPriceByName(String name) {
        return products.get(name).getPrice();
    }

    public int getQuantityByName(String name) {
        return products.get(name).getQuantity();
    }
}
