package store.domain;

import java.util.HashMap;
import java.util.Map;

public class Products {
    private Map<String, Product> products;

    public Products() {
        this.products = new HashMap<>();
    }

    public void add(Product product) {
        products.put(product.getName(), product);
    }

    public int getPriceByName(String name) {
        return products.get(name).getPrice();
    }

    public int getQuantityByName(String name) {
        return products.get(name).getQuantity();
    }
}
