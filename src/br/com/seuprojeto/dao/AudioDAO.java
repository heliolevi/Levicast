package br.com.seuprojeto.dao;

import br.com.seuprojeto.FabricaConexao;
import br.com.seuprojeto.modelos.Musica;
import br.com.seuprojeto.modelos.Podcast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AudioDAO {

    public List<Podcast> listarPodcasts(){
        List<Podcast> lista = new ArrayList<>();
        String sql = "SELECT * FROM podcasts";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Podcast p = new Podcast();
                p.setTitulo(rs.getString("titulo"));
                p.setHost(rs.getString("host"));
                p.setDescricao(rs.getString("descricao"));
                lista.add(p);

            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar podecasts: " + e.getMessage());
        }
        return lista;
        }

    public List<Musica> listarMusicas() {
        List<Musica> lista = new ArrayList<>();
        String sql = "SELECT * FROM musicas";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Musica m = new Musica();
                m.setTitulo(rs.getString("titulo"));
                m.setAlbum(rs.getString("album"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar músicas: " + e.getMessage());
        }
        return lista;
    }


    public void salvarPodcast(Podcast podcast) {
        String sql = "INSERT INTO podcasts (titulo, host, descricao) VALUES (?, ?, ?)";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, podcast.getTitulo());
            stmt.setString(2, podcast.getHost());
            stmt.setString(3, podcast.getDescricao());

            stmt.execute();
            System.out.println("✅ Dados salvos no banco de dados!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao salvar no banco: " + e.getMessage());
        }
    }

    public void salvarMusica(Musica musica) {
        String sql = "INSERT INTO musicas (titulo, album) VALUES (?, ?)";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, musica.getTitulo());
            stmt.setString(2, musica.getAlbum());

            stmt.execute();
            System.out.println("Dados salvos no banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

}
