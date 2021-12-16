package frontcontroller;

import io.javalin.Javalin;

public class FrontController {
    public FrontController(Javalin app){
        /*
        * Middleware
        */

        new Dispatcher(app);
    }
}
