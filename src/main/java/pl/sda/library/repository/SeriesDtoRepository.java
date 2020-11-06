package pl.sda.library.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface SeriesDtoRepository extends CrudRepository<SeriesDto, Long> {
    Optional<SeriesDto> findSeriesDtoByTitle (String title);
}
