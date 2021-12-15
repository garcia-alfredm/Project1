package controllers;

import Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import models.Users;

import java.lang.reflect.Array;
import java.util.List;

public class UserController {
    static UserService userService = new UserService();

    public UserController(){};

    public static void getAllUsers(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        List<Users> users = userService.getAllUsers();

        String jsonString = new ObjectMapper().writeValueAsString(users);
        context.result(jsonString);
    }

    public static void getOneUser(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        //todo I need to get the id of the user record, will probably not use this
        Integer userId = Integer.parseInt(context.pathParam("id"));

        Users user = userService.getOneUser(userId);
        context.result(new ObjectMapper().writeValueAsString(user));
    }

    public static void createUser(Context context){
        Users user = context.bodyAsClass(Users.class);
        if( userService.createUser(user)){
            context.result("User created");
        } else {
            context.result("Failed to create user");
        }
    }

    public static void updateUser(Context context){
        Integer userId = Integer.parseInt(context.pathParam("id"));
        //includes column and value
        String columnValue = context.body();
        String values[] = columnValue.split(" ");

        Boolean result = userService.updateUser(userId, values[0], values[1]);
        if(result){
            context.result("Use updated").status(201);
        } else{
            context.result("Error");
        }
    }

    public static void deleteUser(Context context){
        Integer userId = Integer.parseInt(context.pathParam("id"));

        Boolean result = userService.deleteUser(userId);

        if(result){
            context.result("User deleted");
        } else {
            context.result("Error");
        }
    }

}
