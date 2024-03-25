package br.com.wollygator.main.models.enums;

public enum TipoUsuario {
    ADMIN("admin"),
    COMUM("comum");

    private String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }

}
