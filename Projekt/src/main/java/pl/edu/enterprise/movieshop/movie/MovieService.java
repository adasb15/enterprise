package pl.edu.enterprise.movieshop.movie;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findMovies(Category category) {
        if (category == null) {
            return movieRepository.findAllByOrderByTitleAsc();
        }
        return movieRepository.findByCategoryOrderByTitleAsc(category);
    }

    public Movie getMovie(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(functionalMovieNotFound(id));
    }

    public List<Category> getCategories() {
        return Arrays.asList(Category.values());
    }

    private java.util.function.Supplier<MovieNotFoundException> functionalMovieNotFound(final Long id) {
        return new java.util.function.Supplier<MovieNotFoundException>() {
            @Override
            public MovieNotFoundException get() {
                return new MovieNotFoundException(id);
            }
        };
    }
}
