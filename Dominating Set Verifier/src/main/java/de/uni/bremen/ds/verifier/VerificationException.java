package de.uni.bremen.ds.verifier;

public class VerificationException extends RuntimeException{
    public VerificationException() {
        super();
    }

    public VerificationException(final String message) {
        super(message);
    }
}
