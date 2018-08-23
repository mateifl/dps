package utils;

public class TransactionException extends Exception {
    public TransactionException() {
    }

    public TransactionException(String s) {
        super(s);
    }

    public TransactionException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
