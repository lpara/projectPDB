package br.com.pdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.pdb.model.Projeto;
import br.com.pdb.model.Tarefa;
import br.com.pdb.model.Usuario;

public class TarefaDao extends GenericDao {
	

	public TarefaDao (){
		try {
			
			Connection connection = getConnection();
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
					 "id_usuario_responsavel integer, "+
					 "PRIMARY KEY(id_tarefa))";
			stm.executeUpdate(createTarefa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inserirTarefa(Tarefa tarefa) throws SQLException{

		Connection connection = getConnection();
		PreparedStatement stm = connection.prepareStatement("INSERT INTO public.tarefa (titulo, descricao, data_abertura, data_fechamento, progresso, id_projeto, id_usuario_abertura, id_usuario_fechamento, id_usuario_responsavel) "
																			+ "VALUES (?,?,?,?,?,?,?,?,?)");
		stm.setString(1, tarefa.getTitulo());
		stm.setString(2, tarefa.getDescricao());
		stm.setDate(3, new java.sql.Date(tarefa.getDataAbertura().getTime())); //converter de java util date para java sql date
		if(tarefa.getDataFechamento() != null)
			stm.setDate(4, new java.sql.Date(tarefa.getDataFechamento().getTime()));
		else
			stm.setDate(4, null);
		stm.setInt(5, tarefa.getProgresso());
		stm.setInt(6, tarefa.getProjeto().getIdProjeto());
		stm.setInt(7, tarefa.getUsuarioAbertura().getId());
		if(tarefa.getUsuarioFechamento() != null)
			stm.setInt(8, tarefa.getUsuarioFechamento().getId());
		else
			stm.setInt(8, -1);
		stm.setInt(9, tarefa.getUsuarioResponsavel().getId());
		
		stm.executeUpdate();
		
		System.out.println("Inserção realizada com sucesso!");
		connection.close();
	}
	
	/*
	 * Método que busca uma tarefa pelo identificador
	 */
	public Tarefa buscarTarefaPorId(int id) throws SQLException{
		
		Tarefa tarefa = new Tarefa();
		
		Connection connection = getConnection();
		
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM public.tarefa WHERE id_tarefa = ?");
		stm.setInt(1, id);
		
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			tarefa.setId(rs.getInt("id_tarefa"));
			tarefa.setTitulo(rs.getString("titulo"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setDataAbertura(rs.getDate("data_abertura"));
			tarefa.setDataFechamento(rs.getDate("data_fechamento"));
			tarefa.setProgresso(rs.getInt("progresso"));
			Projeto projeto = new Projeto();
			projeto.setIdProjeto(rs.getInt("id_projeto"));
			tarefa.setProjeto(projeto);
			Usuario usuarioAbertura = new Usuario();
			usuarioAbertura.setId(rs.getInt("id_usuario_abertura"));
			tarefa.setUsuarioAbertura(usuarioAbertura);
			Usuario usuarioFechamento = new Usuario();
			usuarioFechamento.setId(rs.getInt("id_usuario_fechamento"));
			tarefa.setUsuarioFechamento(usuarioFechamento);
			if(rs.getString("id_usuario_fechamento") != null){
				Usuario usuarioResponsavel = new Usuario();
				usuarioResponsavel.setId(rs.getInt("id_usuario_fechamento"));
				tarefa.setUsuarioResponsavel(usuarioResponsavel);
			}
			
		}
		
		connection.close();
		return tarefa;

	}

	public void atualizarTarefa(int id, Tarefa tarefaNova) throws SQLException{
		
		Connection connection = getConnection();
		String updateSql = "UPDATE public.tarefa SET titulo = ?, descricao = ?, progresso = ?, data_abertura = ?, data_fechamento = ?, id_projeto = ?, id_usuario_abertura = ?, id_usuario_fechamento = ?, id_usuario_responsavel = ? ";
		PreparedStatement stm = connection.prepareStatement(updateSql+"WHERE id_tarefa = ?");
		
		stm.setString(1, tarefaNova.getTitulo());
		stm.setString(2, tarefaNova.getDescricao());
		stm.setInt(3, tarefaNova.getProgresso());
		stm.setDate(4, new java.sql.Date(tarefaNova.getDataAbertura().getTime()));
		stm.setDate(5, new java.sql.Date(tarefaNova.getDataFechamento().getTime()));
		stm.setInt(6, tarefaNova.getProjeto().getIdProjeto());
		stm.setInt(7, tarefaNova.getUsuarioAbertura().getId());
		if(tarefaNova.getUsuarioFechamento() != null)
			stm.setInt(8, tarefaNova.getUsuarioFechamento().getId());
		else 
			stm.setString(8, null);
		if(tarefaNova.getUsuarioResponsavel() != null)
			stm.setInt(9, tarefaNova.getUsuarioResponsavel().getId());
		else 
			stm.setString(9, null);
		stm.setInt(10, id);
		
		stm.executeUpdate();
		
		System.out.println("Atualização realizada com sucesso!");
		connection.close();
	}
	
	public void removerTarefa(int id) throws SQLException{
		
		Connection connection = getConnection();
		
		PreparedStatement stm = connection.prepareStatement("DELETE FROM public.tarefa WHERE id_tarefa = ?");
		stm.setInt(1, id);
		
		stm.executeUpdate();
		
		System.out.println("Remoção realizada com sucesso!");
		connection.close();
	}
	
}
