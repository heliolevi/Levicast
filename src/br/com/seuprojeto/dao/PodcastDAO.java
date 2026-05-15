package br.com.seuprojeto.dao;

import br.com.seuprojeto.FabricaConexao;
import br.com.seuprojeto.modelos.Podcast;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PodcastDAO {

    public void salvar(Podcast podcast) {
        String sql = "INSERT INTO podcasts (titulo, host, descricao) VALUES (?, ?, ?)";
        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, podcast.getTitulo());
            stmt.setString(2, podcast.getHost());
            stmt.setString(3, podcast.getDescricao());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Podcast> listar() {
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
            e.printStackTrace();
        }
        return lista;
    }

    public void excluir(String titulo) {
        String sql = "DELETE FROM podcasts WHERE titulo = ?";
        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}