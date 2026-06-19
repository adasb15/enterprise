package pl.edu.enterprise.movieshop.movie;

import pl.edu.enterprise.movieshop.cart.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final Cart cart;

    public MovieController(MovieService movieService, Cart cart) {
        this.movieService = movieService;
        this.cart = cart;
    }

    @GetMapping({"/", "/movies"})
    public String listMovies(@RequestParam(value = "category", required = false) Category category, Model model) {
        model.addAttribute("movies", movieService.findMovies(category));
        model.addAttribute("categories", movieService.getCategories());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("cartItemCount", cart.getItemCount());
        return "movies/list";
    }

    @GetMapping("/movies/{id}")
    public String showMovie(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovie(id));
        model.addAttribute("cartItemCount", cart.getItemCount());
        return "movies/details";
    }
}
