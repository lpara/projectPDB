package br.com.pdb.aplication;

import java.sql.SQLException;
import java.util.Date;

import br.com.pdb.dao.TarefaDao;
import br.com.pdb.negocio.Projeto;
import br.com.pdb.negocio.Tarefa;
import br.com.pdb.negocio.Usuario;

public class Aplicacao {

	public static void main(String[] args) {
		//Testando o DAO da Tarefa
		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo("TesteTitulo");
		tarefa.setDescricao("TesteDescrição");
		tarefa.setProgresso(30);
		Date data_abertura = new Date();
		tarefa.setData_abertura(data_abertura);
		tarefa.setData_fechamento(null);
		Projeto projeto = new Projeto();
		projeto.setId_projeto(1);
		tarefa.setProjeto(projeto);
		Usuario usuario_abertura = new Usuario();
		usuario_abertura.setId_usuario(1);
		tarefa.setUsuario_abertura(usuario_abertura);
		tarefa.setUsuario_fechamento(null);
		
		
		Tarefa tarefaNova = new Tarefa();
		tarefaNova.setTitulo("TesteTituloNovo");
		tarefaNova.setDescricao("TesteDescriçãoNova");
		tarefaNova.setProgresso(30);
		Date data_abertura_nova = new Date();
		tarefaNova.setData_abertura(data_abertura_nova);
		tarefaNova.setData_fechamento(null);
		Projeto projetoNovo = new Projeto();
		projeto.setId_projeto(1);
		tarefaNova.setProjeto(projetoNovo);
		Usuario usuario_abertura_novo = new Usuario();
		usuario_abertura.setId_usuario(1);
		tarefaNova.setUsuario_abertura(usuario_abertura_novo);
		tarefaNova.setUsuario_fechamento(null);
		
		TarefaDao tdao = new TarefaDao();
		
		try {
			tdao.inserirTarefa(tarefa);
			tdao.inserirTarefa(tarefaNova);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			Tarefa tarefaBusca = new Tarefa();
			tarefaBusca = tdao.buscarTarefaPorId(1);
			System.out.println("Essa tarefa "+tarefaBusca.getTitulo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	/*	try {
			Tarefa tarefaAtualizacao = tarefaNova;
			tarefaAtualizacao.setTitulo("TituloAtualizado");
			tarefaAtualizacao.setDescricao("DescriçãoAtualizada");
			Date data_fechamento = new Date(System.currentTimeMillis());
			tarefaAtualizacao.setData_fechamento(data_fechamento);
			Usuario usuarioFechamento = new Usuario();
			usuarioFechamento.setId_usuario(-1);
			tarefaAtualizacao.setUsuario_fechamento(usuarioFechamento);
			tdao.atualizarTarefa(2, tarefaAtualizacao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			tdao.removerTarefa(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	
		
	}
}
