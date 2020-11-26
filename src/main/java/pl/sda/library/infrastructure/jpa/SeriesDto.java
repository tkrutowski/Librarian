package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.library.domain.model.Series;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "series")
class SeriesDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String title;
    private String description;

    Series toDomain() {
        Series series = new Series();
        series.setId(getId());
        series.setTitle(getTitle());
        series.setDescription(getDescription());
        return series;
    }

    static SeriesDto fromDomain(Series series) {
        SeriesDto seriesDto = new SeriesDto();
        seriesDto.setId(series.getId());
        seriesDto.setTitle(series.getTitle());
        seriesDto.setDescription(series.getDescription());
        return seriesDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeriesDto)) return false;
        SeriesDto seriesDto = (SeriesDto) o;
        return getId().equals(seriesDto.getId()) &&
                getTitle().equals(seriesDto.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}
