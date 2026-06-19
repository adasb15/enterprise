package pl.edu.enterprise.movieshop.movie;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Long id) {
        super("Nie znaleziono filmu o id: " + id);
    }
}
