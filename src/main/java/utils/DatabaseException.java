package utils;

public class DatabaseException extends Exception {
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
