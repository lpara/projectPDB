package br.com.pdb.model;
import java.util.Date;

public class Tarefa {

	/*
	 * ID, título da tarefa, descrição da tarefa, data de abertura, data de
	 * fechamento, porcentagem, projeto, usuário de abertura e usuário de
	 * fechamento
	 */

	private int id;
	private String titulo;
	private String descricao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public int getProgresso() {
		return progresso;
	}
	public void setProgresso(int progresso) {
		this.progresso = progresso;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public Usuario getUsuarioAbertura() {
		return usuarioAbertura;
	}
	public void setUsuarioAbertura(Usuario usuarioAbertura) {
		this.usuarioAbertura = usuarioAbertura;
	}
	public Usuario getUsuarioFechamento() {
		return usuarioFechamento;
	}
	public void setUsuarioFechamento(Usuario usuarioFechamento) {
		this.usuarioFechamento = usuarioFechamento;
	}
	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}
	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}
	private Date dataAbertura;
	private Date dataFechamento;
	private int progresso;
	private Projeto projeto;
	private Usuario usuarioAbertura;
	private Usuario usuarioFechamento;
	private Usuario usuarioResponsavel;

	

}
