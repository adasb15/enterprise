package pl.edu.enterprise.movieshop.cart;

import java.math.BigDecimal;
import pl.edu.enterprise.movieshop.movie.Movie;

public class CartItemView {

    private final Movie movie;
    private final int quantity;
    private final BigDecimal subtotal;

    public CartItemView(Movie movie, int quantity) {
        this.movie = movie;
        this.quantity = quantity;
        this.subtotal = movie.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Movie getMovie() {
        return movie;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}
