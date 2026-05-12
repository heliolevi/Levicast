package br.com.seuprojeto.dao;

import br.com.seuprojeto.FabricaConexao;
import br.com.seuprojeto.modelos.Musica;
import br.com.seuprojeto.modelos.Podcast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AudioDAO {
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
