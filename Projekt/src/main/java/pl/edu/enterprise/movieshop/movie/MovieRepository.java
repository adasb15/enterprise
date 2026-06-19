package pl.edu.enterprise.movieshop.movie;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategoryOrderByTitleAsc(Category category);

    List<Movie> findAllByOrderByTitleAsc();
}
