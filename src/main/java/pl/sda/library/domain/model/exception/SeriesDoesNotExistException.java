package pl.sda.library.domain.model.exception;

public class SeriesDoesNotExistException extends ObjectDoesNotExistException {
    public SeriesDoesNotExistException(Long id) {
        super("Series with id = " + id + " does not exist");
    }
}
