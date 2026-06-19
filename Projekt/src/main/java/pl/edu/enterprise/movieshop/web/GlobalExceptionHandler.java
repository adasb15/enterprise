package pl.edu.enterprise.movieshop.web;

import pl.edu.enterprise.movieshop.cart.Cart;
import pl.edu.enterprise.movieshop.movie.MovieNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Cart cart;

    public GlobalExceptionHandler(Cart cart) {
        this.cart = cart;
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public String handleMovieNotFound(MovieNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        model.addAttribute("cartItemCount", cart.getItemCount());
        return "error/not-found";
    }
}
