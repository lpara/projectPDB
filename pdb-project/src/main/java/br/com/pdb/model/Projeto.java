package br.com.pdb.model;
import java.util.Date;

public class Projeto {

	/*
	 * ID, a descri��o, a data de in�cio do projeto e data fim
	 */
	private int idProjeto;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	public int getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


}
