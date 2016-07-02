package br.com.pdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.pdb.model.LogTarefa;
import br.com.pdb.model.Tarefa;

public class LogTarefaDao extends GenericDao {


	

	public LogTarefaDao (){
		try {
			
			Connection connection = getConnection();
			Statement stm = connection.createStatement();
			String createLogTarefa = "CREATE TABLE IF NOT EXISTS log_tarefa("
					+ "id_log_tarefa serial not null, " + 
					 "id_tarefa int not null, " +
					 "porcentagem_tarefa int, " +
					 "novo_resp_tarefa int, " +
					 "mensagem_log varchar(200), " +
					 "PRIMARY KEY(id_log_tarefa))";
			stm.executeUpdate(createLogTarefa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inserirLogTarefa(LogTarefa logTarefa){

		Connection connection;
		try {
			connection = getConnection();
		
			PreparedStatement stm = connection.prepareStatement("INSERT INTO log_tarefa (id_tarefa, porcentagem_tarefa, novo_resp_tarefa, mensagem_log) "
																				+ "VALUES (?,?,?,?)");
			stm.setInt(1, logTarefa.getTarefa().getId());
			stm.setInt(2, logTarefa.getPorcetagemTarefa());
			if(logTarefa.getNovoRespTarefa() > 0)
				stm.setInt(3, logTarefa.getNovoRespTarefa()); 
			else
				stm.setDate(3, null);
			stm.setString(4, logTarefa.getMensagemLog());
			
			stm.executeUpdate();
			
			try{
				connection.setAutoCommit(false);
				//chama metodo para atualizar a tarefa pelo log
				String updateSql = "UPDATE public.tarefa SET progresso = ? ";
				if(logTarefa.getNovoRespTarefa() > 0 && logTarefa.getPorcetagemTarefa() == 100)
					updateSql += ", id_usuario_responsavel = ? , id_usuario_fechamento = ?, data_fechamento = ? ";
				else if(logTarefa.getNovoRespTarefa() > 0)
					updateSql += ", id_usuario_responsavel = ? ";
						
				PreparedStatement stmUpdateTarefa = connection.prepareStatement(updateSql+"WHERE id_tarefa = ?");
				
				stmUpdateTarefa.setInt(1, logTarefa.getPorcetagemTarefa());
				if(logTarefa.getNovoRespTarefa() > 0 && logTarefa.getPorcetagemTarefa() == 100){
					stmUpdateTarefa.setInt(2, logTarefa.getNovoRespTarefa());
					stmUpdateTarefa.setInt(3, logTarefa.getNovoRespTarefa());
					Date hoje = new Date();
					stmUpdateTarefa.setDate(4, new java.sql.Date(hoje.getTime()));
					stmUpdateTarefa.setInt(5, logTarefa.getTarefa().getId());
				}else if(logTarefa.getNovoRespTarefa() > 0){
					stmUpdateTarefa.setInt(2, logTarefa.getNovoRespTarefa());
					stmUpdateTarefa.setInt(3, logTarefa.getTarefa().getId());
				}
				stmUpdateTarefa.executeUpdate();
				connection.commit();
			}catch (SQLException e){
				e.printStackTrace();
				connection.rollback();
			}
			System.out.println("Log cadastrado com sucesso!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Método que busca os logs de uma tarefa
	 */
	public Collection<LogTarefa> buscarLogs(int idTarefa) {
		
		Connection connection;
		Collection<LogTarefa> tarefas = new ArrayList<LogTarefa>();
		try {
			connection = getConnection();
		
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM log_tarefa WHERE id_tarefa = ?");
		stm.setInt(1, idTarefa);
		
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			LogTarefa logTarefa = new LogTarefa();
			logTarefa.setId(rs.getInt("id_log_tarefa"));
			Tarefa tarefa = new Tarefa();
			tarefa.setId(rs.getInt("id_tarefa"));
			logTarefa.setTarefa(tarefa);
			logTarefa.setPorcetagemTarefa(rs.getInt("porcentagem_tarefa"));
			logTarefa.setNovoRespTarefa(rs.getInt("novo_resp_tarefa"));
			tarefas.add(logTarefa);
		}
		
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tarefas;
	}

	public void removerLogTarefa(LogTarefa logTarefa) {
		
		Connection connection;
		try {
			connection = getConnection();
		
			PreparedStatement stm = connection.prepareStatement("DELETE FROM log_tarefa WHERE id_log_tarefa = ?");
			stm.setInt(1, logTarefa.getId());
			
			stm.executeUpdate();
			
			System.out.println("Remoção realizada com sucesso!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
