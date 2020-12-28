package controllers;

import java.util.List;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import Repository.TaskRepository;
import models.Task;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TaskController extends Controller {

	private final TaskRepository taskRository;
	private final HttpExecutionContext ec;

	@Inject
	public TaskController(TaskRepository taskRository, HttpExecutionContext ec) {
		this.taskRository = taskRository;
		this.ec = ec;
	}


	public CompletionStage<Result> create(Http.Request request) {

		JsonNode jsonTask = request.body().asJson();

		Task tarefa = Json.fromJson(jsonTask, Task.class);
		
		System.out.println("/tasks - " + tarefa.toString());

		return taskRository.add(tarefa).thenApplyAsync(sr -> {
			return created(Json.toJson(sr));
		}, ec.current());

	}
	
	public CompletionStage<Result> list(){
		
		return taskRository.list().thenApplyAsync(
				tasks -> {
					
					final List<Task> taskList = tasks.collect(Collectors.toList());
					return ok(Json.toJson(taskList));
					
				},ec.current()			
				
				);
		
	}
	
public CompletionStage<Result> listAtivas(){
		
		return taskRository.listAtivas().thenApplyAsync(
				tasks -> {
					
					final List<Task> taskList = tasks.collect(Collectors.toList());
					return ok(Json.toJson(taskList));
					
				},ec.current()			
				
				);
		
	}
	
	
	
	

}
