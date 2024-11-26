package br.com.joaonaif.main.exceptions;

public class PostNotFound extends RuntimeException {
    public PostNotFound() {
        super("Post não localizado!");
    }
}
