package pl.edu.enterprise.movieshop.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pl.edu.enterprise.movieshop.movie.Movie;
import pl.edu.enterprise.movieshop.movie.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CartService {

    private final MovieRepository movieRepository;

    public CartService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public CartView buildCartView(Cart cart) {
        List<CartItemView> itemViews = new ArrayList<CartItemView>();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : cart.getItems().entrySet()) {
            Movie movie = movieRepository.findById(entry.getKey()).orElse(null);
            if (movie == null) {
                continue;
            }
            CartItemView itemView = new CartItemView(movie, entry.getValue());
            itemViews.add(itemView);
            total = total.add(itemView.getSubtotal());
        }

        return new CartView(itemViews, total);
    }
}
