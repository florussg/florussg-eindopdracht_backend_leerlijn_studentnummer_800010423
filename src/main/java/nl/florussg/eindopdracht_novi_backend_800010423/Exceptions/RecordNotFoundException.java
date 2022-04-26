package nl.florussg.eindopdracht_novi_backend_800010423.Exceptions;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public RecordNotFoundException() {
        super();
    }
    public RecordNotFoundException(String message) {
        super(message);

    }
}
