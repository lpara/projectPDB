package br.com.pdb.negocio;
import java.util.Date;

public class Tarefa {

	/*
	 * ID, título da tarefa, descrição da tarefa, data de abertura, data de
	 * fechamento, porcentagem, projeto, usuário de abertura e usuário de
	 * fechamento
	 */

	private int id_tarefa;
	private String titulo;
	private String descricao;
	private Date data_abertura;
	private Date data_fechamento;
	private int porcetagem;
	private Projeto projeto;
	private Usuario usuario_abertura;
	private Usuario usuario_fechamento;

	public int getId_tarefa() {
		return id_tarefa;
	}

	public void setId_tarefa(int id_tarefa) {
		this.id_tarefa = id_tarefa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Date data_abertura) {
		this.data_abertura = data_abertura;
	}

	public Date getData_fechamento() {
		return data_fechamento;
	}

	public void setData_fechamento(Date data_fechamento) {
		this.data_fechamento = data_fechamento;
	}

	public int getPorcetagem() {
		return porcetagem;
	}

	public void setPorcetagem(int porcetagem) {
		this.porcetagem = porcetagem;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Usuario getUsuario_abertura() {
		return usuario_abertura;
	}

	public void setUsuario_abertura(Usuario usuario_abertura) {
		this.usuario_abertura = usuario_abertura;
	}

	public Usuario getUsuario_fechamento() {
		return usuario_fechamento;
	}

	public void setUsuario_fechamento(Usuario usuario_fechamento) {
		this.usuario_fechamento = usuario_fechamento;
	}

}
