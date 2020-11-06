package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.library.model.Series;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "series")
class SeriesDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_series")
    private Long idSeries;
    private String title;
    private String description;

    public Series toModel() {
        Series series = new Series();
        series.setIdSeries(getIdSeries());
        series.setTitle(getTitle());
        series.setDescription(getDescription());
        return series;
    }
}
