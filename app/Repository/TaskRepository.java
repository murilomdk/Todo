package Repository;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import com.google.inject.ImplementedBy;

import models.Task;

@ImplementedBy(JPATaskRepository.class)
public interface TaskRepository {
	
	CompletionStage<Task> add (Task task);
	
	CompletionStage<Stream<Task>> list();

	CompletionStage<Stream<Task>> listAtivas();
}
