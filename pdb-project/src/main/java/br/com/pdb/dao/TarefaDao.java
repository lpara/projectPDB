package br.com.pdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.pdb.negocio.Projeto;
import br.com.pdb.negocio.Tarefa;
import br.com.pdb.negocio.Usuario;

public class TarefaDao {

	public TarefaDao (){
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdb_project", "pdb", "pdb");
			connection.createStatement().executeQuery("select 1");
			
			Statement stm = connection.createStatement();
			String createTarefa = "CREATE TABLE IF NOT EXISTS tarefa("
					+ "id_tarefa serial not null, " + //serial na verdade é um conjunto de comandos que gera a sequence e o tipo do campo fica inteiro.
					 "titulo VARCHAR(200) not null, " +
					 "descricao VARCHAR, " +
					 "data_abertura date, " +
					 "data_fechamento date, "+
					 "progresso integer, "+
					 "id_projeto integer, "+
					 "id_usuario_abertura integer, "+
					 "id_usuario_fechamento integer, "+
					 "PRIMARY KEY(id_tarefa))";
			stm.executeUpdate(createTarefa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inserirTarefa(Tarefa tarefa) throws SQLException{

		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdb_project", "pdb", "pdb");
		PreparedStatement stm = connection.prepareStatement("INSERT INTO public.tarefa (titulo, descricao, data_abertura, data_fechamento, progresso, id_projeto, id_usuario_abertura, id_usuario_fechamento) "
																			+ "VALUES (?,?,?,?,?,?,?,?)");
		stm.setString(1, tarefa.getTitulo());
		stm.setString(2, tarefa.getDescricao());
		stm.setDate(3, new java.sql.Date(tarefa.getData_abertura().getTime())); //converter de java util date para java sql date
		if(tarefa.getData_fechamento() != null)
			stm.setDate(4, new java.sql.Date(tarefa.getData_fechamento().getTime()));
		else
			stm.setDate(4, null);
		stm.setInt(5, tarefa.getProgresso());
		stm.setInt(6, tarefa.getProjeto().getId_projeto());
		stm.setInt(7, tarefa.getUsuario_abertura().getId_usuario());
		if(tarefa.getUsuario_fechamento() != null)
			stm.setInt(8, tarefa.getUsuario_fechamento().getId_usuario());
		else
			stm.setInt(8, -1);
		
		stm.executeUpdate();
		
		System.out.println("Inserção realizada com sucesso!");
		connection.close();
	}
	
	/*
	 * Método que busca uma tarefa pelo identificador
	 */
	public Tarefa buscarTarefaPorId(int id) throws SQLException{
		
		Tarefa tarefa = new Tarefa();
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdb_project", "pdb", "pdb");
		
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM public.tarefa WHERE id_tarefa = ?");
		stm.setInt(1, id);
		
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			tarefa.setId_tarefa(rs.getInt("id_tarefa"));
			tarefa.setTitulo(rs.getString("titulo"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setData_abertura(rs.getDate("data_abertura"));
			tarefa.setData_fechamento(rs.getDate("data_fechamento"));
			tarefa.setProgresso(rs.getInt("progresso"));
			Projeto projeto = new Projeto();
			projeto.setId_projeto(rs.getInt("id_projeto"));
			tarefa.setProjeto(projeto);
			Usuario usuarioAbertura = new Usuario();
			usuarioAbertura.setId_usuario(rs.getInt("id_usuario_abertura"));
			tarefa.setUsuario_abertura(usuarioAbertura);
			Usuario usuarioFechamento = new Usuario();
			usuarioFechamento.setId_usuario(rs.getInt("id_usuario_fechamento"));
			tarefa.setUsuario_fechamento(usuarioFechamento);
		}
		
		connection.close();
		return tarefa;

	}

	public void atualizarTarefa(int id, Tarefa tarefaNova) throws SQLException{
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdb_project", "pdb", "pdb");
		String updateSql = "UPDATE public.tarefa SET titulo = ?, descricao = ?, progresso = ?, data_abertura = ?, data_fechamento = ?, id_projeto = ?, id_usuario_abertura = ?, id_usuario_fechamento = ?";
		PreparedStatement stm = connection.prepareStatement(updateSql+"WHERE id_tarefa = ?");
		
		stm.setString(1, tarefaNova.getTitulo());
		stm.setString(2, tarefaNova.getDescricao());
		stm.setInt(3, tarefaNova.getProgresso());
		stm.setDate(4, new java.sql.Date(tarefaNova.getData_abertura().getTime()));
		stm.setDate(5, new java.sql.Date(tarefaNova.getData_fechamento().getTime()));
		stm.setInt(6, tarefaNova.getProjeto().getId_projeto());
		stm.setInt(7, tarefaNova.getUsuario_abertura().getId_usuario());
		stm.setInt(8, tarefaNova.getUsuario_fechamento().getId_usuario());
		stm.setInt(9, id);
		
		stm.executeUpdate();
		
		System.out.println("Atualização realizada com sucesso!");
		connection.close();
	}
	
	public void removerTarefa(int id) throws SQLException{
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdb_project", "pdb", "pdb");
		
		PreparedStatement stm = connection.prepareStatement("DELETE FROM public.tarefa WHERE id_tarefa = ?");
		stm.setInt(1, id);
		
		stm.executeUpdate();
		
		System.out.println("Remoção realizada com sucesso!");
		connection.close();
	}
	
}
