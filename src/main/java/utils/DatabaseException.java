package utils;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = -3414901923005262724L;

	public DatabaseException() {
        super();
    }

    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
