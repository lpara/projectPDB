package br.com.pdb.model;

public class LogTarefa {

	/*
	 * ID, a tarefa que está relacionada (será um relacionamento com o objeto
	 * tarefa: Tarefa tarefa), qual a porcentagem que a tarefa deverá ficar
	 * (caso fique 100%, definir a data de conclusão da tarefa relacionada - tal
	 * método deve se encontrar no LogTarefaDAO), o novo usuário responsável
	 * (caso seja alterado no log, alterar na tarefa - tal método deve se
	 * encontrar no LogTarefaDAO)
	 */
	private int id;
	private Tarefa tarefa;
	private int porcetagemTarefa;
	private int novoRespTarefa;
	private String mensagemLog;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public int getPorcetagemTarefa() {
		return porcetagemTarefa;
	}

	public void setPorcetagemTarefa(int porcetagemTarefa) {
		this.porcetagemTarefa = porcetagemTarefa;
	}

	public int getNovoRespTarefa() {
		return novoRespTarefa;
	}

	public void setNovoRespTarefa(int novoRespTarefa) {
		this.novoRespTarefa = novoRespTarefa;
	}

	public String getMensagemLog() {
		return mensagemLog;
	}

	public void setMensagemLog(String mensagemLog) {
		this.mensagemLog = mensagemLog;
	}

}
