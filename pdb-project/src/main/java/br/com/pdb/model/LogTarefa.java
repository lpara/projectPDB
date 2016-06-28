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
	private int id_log;
	private Tarefa tarefa;
	private int porcetagem_tarefa;
	private int novo_resp_tarefa;

	public int getId_log() {
		return id_log;
	}

	public void setId_log(int id_log) {
		this.id_log = id_log;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public int getPorcetagem_tarefa() {
		return porcetagem_tarefa;
	}

	public void setPorcetagem_tarefa(int porcetagem_tarefa) {
		this.porcetagem_tarefa = porcetagem_tarefa;
	}

	public int getNovo_resp_tarefa() {
		return novo_resp_tarefa;
	}

	public void setNovo_resp_tarefa(int novo_resp_tarefa) {
		this.novo_resp_tarefa = novo_resp_tarefa;
	}

}
