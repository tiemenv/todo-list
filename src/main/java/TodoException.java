public class TodoException extends RuntimeException {
    public TodoException(String msg) {
        super(msg);
    }

    public TodoException(String msg, Exception ex) {
        super(msg, ex);

    }
}
