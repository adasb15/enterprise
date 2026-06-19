package pl.edu.enterprise.movieshop.cart;

import pl.edu.enterprise.movieshop.movie.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {

    private final Cart cart;
    private final CartService cartService;
    private final MovieService movieService;

    public CartController(Cart cart, CartService cartService, MovieService movieService) {
        this.cart = cart;
        this.cartService = cartService;
        this.movieService = movieService;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.buildCartView(cart));
        model.addAttribute("cartItemCount", cart.getItemCount());
        return "cart/view";
    }

    @PostMapping("/cart/add/{movieId}")
    public String addMovie(@PathVariable Long movieId, RedirectAttributes redirectAttributes) {
        String title = movieService.getMovie(movieId).getTitle();
        cart.add(movieId);
        redirectAttributes.addFlashAttribute("message", "Dodano do koszyka: " + title);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{movieId}")
    public String removeMovie(@PathVariable Long movieId, RedirectAttributes redirectAttributes) {
        cart.remove(movieId);
        redirectAttributes.addFlashAttribute("message", "Usunięto film z koszyka.");
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart(RedirectAttributes redirectAttributes) {
        cart.clear();
        redirectAttributes.addFlashAttribute("message", "Koszyk został wyczyszczony.");
        return "redirect:/cart";
    }
}
