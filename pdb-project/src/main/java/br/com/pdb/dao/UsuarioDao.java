package br.com.pdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.pdb.model.Usuario;

public class UsuarioDao extends GenericDao {

	public UsuarioDao() {
		try {
			
			Connection connection = getConnection();
			connection.createStatement().executeQuery("select 1");
			
			Statement stm = connection.createStatement();
			String createProjeto = "CREATE TABLE IF NOT EXISTS usuario("
					+ "id_usuario serial not null, " + 
					 "nome VARCHAR(200) not null, " +
					 "email VARCHAR(200) not null, " +
					 "senha VARCHAR(20) not null, "+
					 "PRIMARY KEY(id_usuario))";
			stm.executeUpdate(createProjeto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inserirUsuario(Usuario usuario){

		Connection connection;
		try {
			connection = getConnection();
		
			PreparedStatement stm = connection.prepareStatement("INSERT INTO usuario (nome, email, senha) "
																				+ "VALUES (?,?,?)");
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getEmail());
			stm.setString(3, usuario.getSenha());
			
			stm.executeUpdate();
			
			System.out.println("Inserção realizada com sucesso!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Método que busca um usuario
	 */
	public Usuario buscarUsuario(Usuario usuario) {
		
		Connection connection;
		try {
			connection = getConnection();
		
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
		stm.setInt(1, usuario.getId());
		
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			usuario.setId(rs.getInt("id_usuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));
			usuario.setSenha(rs.getString("senha"));
		}
		
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public void atualizarUsuario(Usuario usuario) {
		
		Connection connection;
		try {
			connection = getConnection();
		
			String updateSql = "UPDATE usuario SET nome = ?, email = ?, senha = ? ";
			PreparedStatement stm = connection.prepareStatement(updateSql+" WHERE id_usuario = ? ");
			
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getEmail());
			stm.setString(3, usuario.getSenha());
			stm.setInt(4, usuario.getId());
			
			
			stm.executeUpdate();
			
			System.out.println("Atualização realizada com sucesso!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerUsuario(Usuario usuario) {
		
		Connection connection;
		try {
			connection = getConnection();
		
			PreparedStatement stm = connection.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?");
			stm.setInt(1, usuario.getId());
			
			stm.executeUpdate();
			
			System.out.println("Remoção realizada com sucesso!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
