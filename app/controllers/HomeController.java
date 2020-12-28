package controllers;

import java.util.HashMap;

import play.libs.Json;
import play.mvc.*;


public class HomeController extends Controller {

   
    public Result index() {
        return ok("olá olá!");
    }
    
    public Result resultMap() {
    	
    	HashMap<String, Object> result = new HashMap<String, Object>(){
    		{
    			put("asdasd",12);
    			put("qwerty","murilo");
    		}
    	};
    	
    	return ok(Json.toJson(result));
    }

}
