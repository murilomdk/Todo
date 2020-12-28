package Repository;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import com.google.inject.ImplementedBy;

import models.Person;


@ImplementedBy(JPAPersonRepository.class)
public interface PersonRepository {
	
	
	CompletionStage<Person> add (Person person);
	
	
	CompletionStage<Stream<Person>> list();

}
