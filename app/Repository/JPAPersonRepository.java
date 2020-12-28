package Repository;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import models.Person;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import play.db.jpa.JPAApi;

public class JPAPersonRepository implements PersonRepository{
	
	private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;
    
    
    @Inject
    public JPAPersonRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }
	

	@Override
	public CompletionStage<Person> add(Person person) {
		return supplyAsync(() -> wrap(em -> insert(em, person)), executionContext);
	}

	@Override
	public CompletionStage<Stream<Person>> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Person insert(EntityManager em, Person person) {
        em.persist(person);
        return person;
    }

}
