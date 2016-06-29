package br.com.pdb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.pdb.model.Projeto;

public class ProjetoDao extends GenericDao {


	

	public ProjetoDao (){
		try {
			
			Connection connection = getConnection();
			connection.createStatement().executeQuery("select 1");
			
			Statement stm = connection.createStatement();
			String createProjeto = "CREATE TABLE IF NOT EXISTS projeto("
					+ "id_projeto serial not null, " + 
					 "descricao VARCHAR(200) not null, " +
					 "data_inicio date, " +
					 "data_fim date, "+
					 "PRIMARY KEY(id_projeto))";
			stm.executeUpdate(createProjeto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inserirProjeto(Projeto projeto){

		Connection connection;
		try {
			connection = getConnection();
		
			PreparedStatement stm = connection.prepareStatement("INSERT INTO projeto (descricao, data_inicio, data_fim) "
																				+ "VALUES (?,?,?)");
			stm.setString(1, projeto.getDescricao());
			stm.setDate(2, new java.sql.Date(projeto.getDataInicio().getTime()));
			if(projeto.getDataFim() != null)
				stm.setDate(3, new java.sql.Date(projeto.getDataFim().getTime()));
			else
				stm.setDate(3, null);
			
			stm.executeUpdate();
			
			System.out.println("Inserção realizada com sucesso!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Método que busca um projeto
	 */
	public Projeto buscarProjeto(Projeto projeto) {
		
		Connection connection;
		try {
			connection = getConnection();
		
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM projeto WHERE id_projeto = ?");
		stm.setInt(1, projeto.getIdProjeto());
		
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			projeto.setIdProjeto(rs.getInt("id_projeto"));
			projeto.setDataInicio(rs.getDate("data_inicio"));
			Date dataFim = rs.getDate("data_fim");
			if (dataFim != null)
				projeto.setDataInicio(dataFim);
		}
		
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projeto;
	}

	public void atualizarProjeto(Projeto projeto) {
		
		Connection connection;
		try {
			connection = getConnection();
		
			String updateSql = "UPDATE public.tarefa SET descricao = ?, data_inicio = ?, data_fim = ?, id_projeto = ?, id_usuario_abertura = ? ";
			PreparedStatement stm = connection.prepareStatement(updateSql+" WHERE id_projeto = ? ");
			
			stm.setString(1, projeto.getDescricao());
			stm.setFloat(2, projeto.getDataInicio().getTime());
			if(projeto.getDataFim() != null)
				stm.setFloat(3, projeto.getDataFim().getTime());
			else
				stm.setDate(3, null);
			stm.setInt(4, projeto.getIdProjeto());
			
			stm.executeUpdate();
			
			System.out.println("Atualização realizada com sucesso!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerProjeto(Projeto projeto) {
		
		Connection connection;
		try {
			connection = getConnection();
		
			PreparedStatement stm = connection.prepareStatement("DELETE FROM projeto WHERE id_projeto = ?");
			stm.setInt(1, projeto.getIdProjeto());
			
			stm.executeUpdate();
			
			System.out.println("Remoção realizada com sucesso!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

