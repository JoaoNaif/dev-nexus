package br.com.joaonaif.main.exceptions;

public class ProgrammerAlreadyExist extends RuntimeException {
    public ProgrammerAlreadyExist() {
        super("Usuário já existe");
    }
}
