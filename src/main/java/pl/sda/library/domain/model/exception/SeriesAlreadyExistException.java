package pl.sda.library.domain.model.exception;

import pl.sda.library.domain.model.Series;

public class SeriesAlreadyExistException extends ObjectAlreadyExistException {
    public SeriesAlreadyExistException(Series series) {
        super("Series already exists: " + series.toString());
    }
}
