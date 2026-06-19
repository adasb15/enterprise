package pl.edu.enterprise.movieshop.cart;

import java.math.BigDecimal;
import java.util.List;

public class CartView {

    private final List<CartItemView> items;
    private final BigDecimal total;

    public CartView(List<CartItemView> items, BigDecimal total) {
        this.items = items;
        this.total = total;
    }

    public List<CartItemView> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
