package BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions;

import java.util.UUID;

public class NotFoundExceptionUUID extends RuntimeException {
    public NotFoundExceptionUUID(UUID id) {
        super("Record con id " + id + " non trovato!");

    }
    public NotFoundExceptionUUID(String message) {
        super(message);
    }
}