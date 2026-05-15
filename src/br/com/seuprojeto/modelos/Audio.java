package br.com.seuprojeto.modelos;

public class Audio {
    private String titulo;
    private int totalReproducoes;
    private int curtidas;
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
