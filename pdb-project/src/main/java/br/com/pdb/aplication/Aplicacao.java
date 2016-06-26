package br.com.pdb.aplication;

import java.sql.SQLException;

import br.com.pdb.dao.TarefaDao;
import br.com.pdb.negocio.Tarefa;

public class Aplicacao {

	public static void main(String[] args) {
		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo("Teste");
		TarefaDao tdao = new TarefaDao();
	
		
	}
}
