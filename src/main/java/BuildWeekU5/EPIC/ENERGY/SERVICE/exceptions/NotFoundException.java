package BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Record con id " + id + " non trovato!");

    }
    public NotFoundException(String message) {
        super(message);
    }
}