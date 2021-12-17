package frontcontroller;

import Services.UserService;
import dto.LoginDTO;
import io.javalin.Javalin;
import models.JsonResponse;
import models.Login;
import models.Users;

public class FrontController {
    UserService userService = new UserService();

    public FrontController(Javalin app){
        /*
        * Middleware
        */

        app.post("/api/login", context -> {
            Login login = context.bodyAsClass(Login.class);

            /* todo validate user credentials here, gets user id */
            Users user = userService.getOneUser(login.getUsername());
            System.out.println(user);
            String userrole = "";
            if(user.getRoleId() == 1){
                userrole = "EMPLOYEE";
            } else{
                userrole = "MANAGER";
            }

            context.sessionAttribute("user-session", login);
            context.json( new JsonResponse(true, "login successful",
                            new LoginDTO(user.getId(), login.getUsername(), userrole)));
        });

        app.get("/api/check-session", context -> {
            //is used on every page, should include userid, full name, role
           Login login = context.sessionAttribute("user-session");

           if(login == null){
               context.json(new JsonResponse(false, "no session found", null));
           } else {
               context.json(new JsonResponse(true, "session found",
                       new LoginDTO(login.getUserId(), login.getUsername(), login.getRole())));
           }
        });

        app.delete("/api/logout", context -> {
           context.sessionAttribute("user-session", null);
           context.json(new JsonResponse(true, "Session ended", null));
        });

        new Dispatcher(app);
    }
}
