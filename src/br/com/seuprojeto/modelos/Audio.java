package br.com.seuprojeto.modelos;

public class Audio {
    private String titulo;
    private int totalReproducoes;
    private int curtidas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTotalReproducoes() {
        return totalReproducoes;
    }

    public void reproduz() {
        this.totalReproducoes++;
    }

    public void curte() {
        this.curtidas++;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }
}
