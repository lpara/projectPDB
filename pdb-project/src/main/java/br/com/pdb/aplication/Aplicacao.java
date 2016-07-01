package br.com.pdb.aplication;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.pdb.dao.LogTarefaDao;
import br.com.pdb.dao.ProjetoDao;
import br.com.pdb.dao.TarefaDao;
import br.com.pdb.dao.UsuarioDao;
import br.com.pdb.model.LogTarefa;
import br.com.pdb.model.Projeto;
import br.com.pdb.model.Tarefa;
import br.com.pdb.model.Usuario;

public class Aplicacao {

	public static void main(String[] args) {
		
		criaObjetos();
		
		criarProjetos();
		
		criarUsuarios();
		
		abrirTarefas();
		
		manipularTarefa1();
		
		manipularTarefa2();
		
		System.out.println("Fim das interações");
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
	
	private static void manipularTarefa1() {
		LogTarefaDao lDao = new LogTarefaDao();
		TarefaDao tDao = new TarefaDao();
		String tituloTarefa = "Tarefa 01";
		try {
			Tarefa tarefa = tDao.buscarTarefaPorTitulo(tituloTarefa);
			
			if(tarefa != null){
				LogTarefa logTarefa = new LogTarefa();
				
				logTarefa.setNovoRespTarefa(1);
				logTarefa.setTarefa(tarefa);
				logTarefa.setPorcetagemTarefa(30);
				logTarefa.setMensagemLog("Mensagem Log 30%");
				
				lDao.inserirLogTarefa(logTarefa);
			}else{
				System.out.println("Tarefa com titulo '"+tituloTarefa+ "' não encontrada para registrar o log.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log2
		try {
			Tarefa tarefa = tDao.buscarTarefaPorTitulo(tituloTarefa);
			
			if(tarefa != null){
				LogTarefa logTarefa = new LogTarefa();
				
				logTarefa.setNovoRespTarefa(1);
				logTarefa.setTarefa(tarefa);
				logTarefa.setPorcetagemTarefa(100);
				logTarefa.setMensagemLog("Mensagem Log 100%");
				
				lDao.inserirLogTarefa(logTarefa);
			}else{
				System.out.println("Tarefa com titulo '"+tituloTarefa+ "' não encontrada para registrar o log.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Tarefa tarefa = tDao.buscarTarefaPorTitulo(tituloTarefa);
			
			if(tarefa != null){
				if(tarefa.getProgresso() == 100)
					imrpimirTarefa(tarefa);
			}else{
				System.out.println("Tarefa com titulo '"+tituloTarefa+ "' não encontrada");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void manipularTarefa2() {
		LogTarefaDao lDao = new LogTarefaDao();
		TarefaDao tDao = new TarefaDao();
		String tituloTarefa = "Tarefa 02";
		try {
			Tarefa tarefa = tDao.buscarTarefaPorTitulo(tituloTarefa);
			
			if(tarefa != null){
				LogTarefa logTarefa = new LogTarefa();
				
				logTarefa.setNovoRespTarefa(1);
				logTarefa.setTarefa(tarefa);
				logTarefa.setPorcetagemTarefa(30);
				logTarefa.setMensagemLog("Mensagem Log 30%");
				
				lDao.inserirLogTarefa(logTarefa);
			}else{
				System.out.println("Tarefa com titulo '"+tituloTarefa+ "' não encontrada para registrar o log.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log Altera usuario
		try {
			Tarefa tarefa = tDao.buscarTarefaPorTitulo(tituloTarefa);
			
			if(tarefa != null){
				LogTarefa logTarefa = new LogTarefa();
				
				logTarefa.setNovoRespTarefa(2);
				logTarefa.setTarefa(tarefa);
				logTarefa.setPorcetagemTarefa(30);
				logTarefa.setMensagemLog("Alteração usuário");
				
				lDao.inserirLogTarefa(logTarefa);
			}else{
				System.out.println("Tarefa com titulo '"+tituloTarefa+ "' não encontrada para registrar o log.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Tarefa tarefa = tDao.buscarTarefaPorTitulo(tituloTarefa);
			
			if(tarefa != null){
				if(tarefa.getProgresso() == 100)
					imrpimirTarefa(tarefa);
			}else{
				System.out.println("Tarefa com titulo '"+tituloTarefa+ "' não encontrada.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log 3 100%
		try {
			Tarefa tarefa = tDao.buscarTarefaPorTitulo(tituloTarefa);
			
			if(tarefa != null){
				LogTarefa logTarefa = new LogTarefa();
				
				logTarefa.setNovoRespTarefa(2);
				logTarefa.setTarefa(tarefa);
				logTarefa.setPorcetagemTarefa(100);
				logTarefa.setMensagemLog("Mensagem Log 100%");
				
				lDao.inserirLogTarefa(logTarefa);
			}else{
				System.out.println("Tarefa com titulo '"+tituloTarefa+ "' não encontrada para registrar o log.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Tarefa tarefa = tDao.buscarTarefaPorTitulo(tituloTarefa);
			
			if(tarefa != null){
				if(tarefa.getProgresso() == 100)
					imrpimirTarefa(tarefa);
			}else{
				System.out.println("Tarefa com titulo '"+tituloTarefa+ "' não encontrada.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void imrpimirTarefa(Tarefa tarefa)  {
		
		SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy"); 
		
		UsuarioDao uDao = new UsuarioDao();
		Usuario usuario = new Usuario();
		usuario.setId(tarefa.getUsuarioResponsavel().getId());
		usuario = uDao.buscarUsuario(usuario);
		
		System.out.println("Titulo: " + tarefa.getTitulo());
		System.out.println("Descrição: " + tarefa.getDescricao());
		System.out.println("Progresso: " + tarefa.getProgresso());
		
		try {
			System.out.println("Aberta em: " + dt.parse(tarefa.getDataAbertura().toString()));
			System.out.println("Finalizada em: " + dt.parse(tarefa.getDataFechamento().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Usuario que finalizou: " + usuario.getNome());
		System.out.println("\n---------------------------------------------");
	}

	
}
