package br.com.pdb.aplication;

import java.sql.SQLException;
import java.util.Date;

import br.com.pdb.dao.LogTarefaDao;
import br.com.pdb.dao.ProjetoDao;
import br.com.pdb.dao.TarefaDao;
import br.com.pdb.dao.UsuarioDao;
import br.com.pdb.model.Projeto;
import br.com.pdb.model.Tarefa;
import br.com.pdb.model.Usuario;

public class Aplicacao {

	public static void main(String[] args) {
		
		criaObjetos();
		
		criarProjetos();
		
		criarUsuarios();
		
		abrirTarefas();
		
		
		
		//Testando o DAO da Tarefa
//		Tarefa tarefa = new Tarefa();
//		tarefa.setTitulo("TesteTitulo");
//		tarefa.setDescricao("TesteDescrição");
//		tarefa.setProgresso(30);
//		Date data_abertura = new Date();
//		tarefa.setData_abertura(data_abertura);
//		tarefa.setData_fechamento(null);
//		Projeto projeto = new Projeto();
//		projeto.setId_projeto(1);
//		tarefa.setProjeto(projeto);
//		Usuario usuario_abertura = new Usuario();
//		usuario_abertura.setId_usuario(1);
//		tarefa.setUsuario_abertura(usuario_abertura);
//		tarefa.setUsuario_fechamento(null);
//		
//		
//		Tarefa tarefaNova = new Tarefa();
//		tarefaNova.setTitulo("TesteTituloNovo");
//		tarefaNova.setDescricao("TesteDescriçãoNova");
//		tarefaNova.setProgresso(30);
//		Date data_abertura_nova = new Date();
//		tarefaNova.setData_abertura(data_abertura_nova);
//		tarefaNova.setData_fechamento(null);
//		Projeto projetoNovo = new Projeto();
//		projeto.setId_projeto(1);
//		tarefaNova.setProjeto(projetoNovo);
//		Usuario usuario_abertura_novo = new Usuario();
//		usuario_abertura.setId_usuario(1);
//		tarefaNova.setUsuario_abertura(usuario_abertura_novo);
//		tarefaNova.setUsuario_fechamento(null);
//		
//		TarefaDao tdao = new TarefaDao();
//		
//		try {
//			tdao.inserirTarefa(tarefa);
//			tdao.inserirTarefa(tarefaNova);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
	

	private static void criaObjetos() {
		try {
			TarefaDao tDao = new TarefaDao();
			tDao.buscarTarefaPorId(1);
			ProjetoDao pDao = new ProjetoDao();
			Projeto projeto = new Projeto();
			projeto.setIdProjeto(1);
			pDao.buscarProjeto(projeto);
			UsuarioDao uDao = new UsuarioDao();
			uDao.buscarUsuario(new Usuario());
			LogTarefaDao lDao = new LogTarefaDao();
			lDao.buscarLogs(1);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void criarProjetos() {
		
		ProjetoDao pDao = new ProjetoDao();
		Projeto projeto01 = new Projeto();
		projeto01.setDescricao("Projeto 01");
		projeto01.setDataInicio(new Date());
		pDao.inserirProjeto(projeto01);

		Projeto projeto02 = new Projeto();
		projeto02.setDescricao("Projeto 02");
		projeto02.setDataInicio(new Date());
		pDao.inserirProjeto(projeto02);
		
		Projeto projeto03 = new Projeto();
		projeto03.setDescricao("Projeto 03");
		projeto03.setDataInicio(new Date());
		pDao.inserirProjeto(projeto03);
		
	}
	
	private static void criarUsuarios() {
		
		UsuarioDao uDao = new UsuarioDao();
		
		Usuario usuario01 = new Usuario();
		usuario01.setNome("Usuario 01");
		usuario01.setEmail("usuario01@email.com");
		usuario01.setSenha("123456");
		uDao.inserirUsuario(usuario01);
		
		Usuario usuario02 = new Usuario();
		usuario02.setNome("Usuario 02");
		usuario02.setEmail("usuario02@email.com");
		usuario02.setSenha("123456");
		uDao.inserirUsuario(usuario02);
		
	}
	
	private static void abrirTarefas() {
		TarefaDao tDao = new TarefaDao();
		
		Tarefa tarefa01 = new Tarefa();
		tarefa01.setDataAbertura(new Date());
		tarefa01.setDescricao("Tarefa de teste 01");
		tarefa01.setTitulo("Tarefa 01");
		Usuario usuarioTarefa01 = new Usuario();
		usuarioTarefa01.setId(1);
		tarefa01.setUsuarioAbertura(usuarioTarefa01);
		tarefa01.setUsuarioResponsavel(usuarioTarefa01);
		tarefa01.setProgresso(0);
		Projeto projeto01 = new Projeto();
		projeto01.setIdProjeto(1);
		tarefa01.setProjeto(projeto01);
		
		try {
			tDao.inserirTarefa(tarefa01);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Tarefa tarefa02 = new Tarefa();
		tarefa02.setDataAbertura(new Date());
		tarefa02.setDescricao("Tarefa de teste 02");
		tarefa02.setTitulo("Tarefa 02");
		Usuario usuarioTarefa02 = new Usuario();
		usuarioTarefa02.setId(1);
		tarefa02.setUsuarioAbertura(usuarioTarefa02);
		tarefa02.setUsuarioResponsavel(usuarioTarefa02);
		tarefa02.setProgresso(0);
		Projeto projeto02 = new Projeto();
		projeto02.setIdProjeto(2);
		tarefa02.setProjeto(projeto02);
		
		
		try {
			tDao.inserirTarefa(tarefa02);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
