package Repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import models.Task;
import play.db.jpa.JPAApi;

public class JPATaskRepository implements TaskRepository {

	private final JPAApi jpaApi;
	private final DatabaseExecutionContext executionContext;

	@Inject
	public JPATaskRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		this.jpaApi = jpaApi;
		this.executionContext = executionContext;
	}
	
	//inserir tareda
	@Override
	public CompletionStage<Task> add(Task task) {

		return supplyAsync(() -> wrap(em -> insert(em, task)), executionContext);

	}
	
	//listar todas
	@Override
	public CompletionStage<Stream<Task>> list() {

		return supplyAsync(() -> wrap(em -> list(em)), executionContext);
	}
	
	//Listar tarefas ativas
	@Override
	public CompletionStage<Stream<Task>> listAtivas() {
		return supplyAsync(() -> wrap(em -> listAtivas(em)), executionContext);
	}

	private <T> T wrap(Function<EntityManager, T> function) {
		return jpaApi.withTransaction(function);
	}

	private Task insert(EntityManager em, Task tarefa) {
		em.persist(tarefa);

		return tarefa;
	}
	
	private Stream<Task> listAtivas(EntityManager em) {
		List<Task> tasks = em.createQuery("select t from Task t where flag_concluido is false", Task.class).getResultList();

		return tasks.stream();
	}

	private Stream<Task> list(EntityManager em) {
		List<Task> tasks = em.createQuery("select t from Task t", Task.class).getResultList();

		return tasks.stream();
	}

	
	
	

}
