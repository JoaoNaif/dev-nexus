package br.com.joaonaif.main.exceptions;

public class ProgrammerNotFound extends RuntimeException {
    public ProgrammerNotFound() {
        super("Programmer not found");
    }
}
