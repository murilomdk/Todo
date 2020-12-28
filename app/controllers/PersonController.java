package controllers;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import Repository.PersonRepository;
import models.Person;
import play.libs.concurrent.HttpExecutionContext;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class PersonController extends Controller {
	
    private final FormFactory formFactory;
    private final PersonRepository personRepository;
    private final HttpExecutionContext ec;
    
    @Inject
    public PersonController(FormFactory formFactory, PersonRepository personRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.personRepository = personRepository;
        this.ec = ec;
    }
    
    public Result index(final Http.Request request) {
        return ok(views.html.index.render(request));
    }
    
    public CompletionStage<Result> addPerson(final Http.Request request) {
        Person person = formFactory.form(Person.class).bindFromRequest(request).get();
        return personRepository
                .add(person)
                .thenApplyAsync(p -> redirect(routes.PersonController.index()), ec.current());
    }
	

}
