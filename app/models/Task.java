package models;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "tb_task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_tarefa;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "data_cadastro")
	private LocalDateTime data_cadastro = LocalDateTime.now();

	@Column(name = "flag_concluido")
	private boolean flag_concluido;

	// get - set

	public long getId_tarefa() {
		return id_tarefa;
	}

	public void setId_tarefa(long id_tarefa) {
		this.id_tarefa = id_tarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(LocalDateTime data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public boolean isFlag_concluido() {
		return flag_concluido;
	}

	public void setFlag_concluido(boolean flag_concluido) {
		this.flag_concluido = flag_concluido;
	}

	@Override
	public String toString() {
		return "Tarefa [id_tarefa=" + id_tarefa + ", descricao=" + descricao + ", data_cadastro=" + data_cadastro
				+ ", flag_concluido=" + flag_concluido + "]";
	}

}
