package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface SeriesDtoRepository extends CrudRepository<SeriesDto, Long> {
    Optional<SeriesDto> findSeriesDtoByTitle (String title);
}
