package pl.edu.enterprise.movieshop.movie;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private Category category;

    @Column(nullable = false)
    private Integer productionYear;

    @Lob
    @Column(nullable = false)
    private String plotDescription;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    protected Movie() {
    }

    public Movie(String title, Category category, Integer productionYear, String plotDescription, BigDecimal price) {
        this.title = title;
        this.category = category;
        this.productionYear = productionYear;
        this.plotDescription = plotDescription;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public String getPlotDescription() {
        return plotDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie)) {
            return false;
        }
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
