package br.com.seuprojeto.dao;

import br.com.seuprojeto.FabricaConexao;
import br.com.seuprojeto.modelos.Musica;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {

    public void salvar(Musica musica){
        String sql = "INSERT INTO musicas (titulo, album) VALUES (?,?) ";
        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, musica.getTitulo());
            stmt.setString(2, musica.getAlbum());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar música: " + e.getMessage());
        }
    }

    public List<Musica> listar() {
        List<Musica> lista = new ArrayList<>();
        String sql = "SELECT * FROM musicas";
        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while(rs.next()){
                Musica m = new Musica();
                m.setTitulo(rs.getString("Titulo"));
                m.setAlbum(rs.getString("Album"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public  void excluir(String titulo) {
        String sql = "DELETE FROM musicas WHERE titulo = ?";
        try (Connection conn = FabricaConexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
