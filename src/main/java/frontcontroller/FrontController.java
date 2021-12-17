package frontcontroller;

import dto.LoginDTO;
import io.javalin.Javalin;
import models.JsonResponse;
import models.Login;

public class FrontController {
    public FrontController(Javalin app){
        /*
        * Middleware
        */

        app.post("/api/login", context -> {
            Login login = context.bodyAsClass(Login.class);

            /* todo validate user credentials here */

            context.sessionAttribute("user-session", login);
            context.json(
                    new JsonResponse(true, "login successful",
                            new LoginDTO(login.getUsername(), login.getRole())));
        });

        app.get("/api/check-session", context -> {
           Login login = context.sessionAttribute("user-session");

           if(login == null){
               context.json(new JsonResponse(false, "no session found", null));
           } else {
               context.json(new JsonResponse(true, "session found",
                       new LoginDTO(login.getUsername(), login.getRole())));
           }
        });

        app.delete("/api/logout", context -> {
           context.sessionAttribute("user-session", null);
           context.json(new JsonResponse(true, "Session ended", null));
        });

        new Dispatcher(app);
    }
}
