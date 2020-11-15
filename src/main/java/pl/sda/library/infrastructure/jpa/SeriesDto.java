package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.library.domain.model.Series;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "series")
class SeriesDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_series")
    private Long idSeries;
    private String title;
    private String description;

    Series toDomain() {
        Series series = new Series();
        series.setIdSeries(getIdSeries());
        series.setTitle(getTitle());
        series.setDescription(getDescription());
        return series;
    }

    static SeriesDto fromDomain(Series series) {
        SeriesDto seriesDto = new SeriesDto();
        seriesDto.setIdSeries(series.getIdSeries());
        seriesDto.setTitle(series.getTitle());
        seriesDto.setDescription(series.getDescription());
        return seriesDto;
    }
}
