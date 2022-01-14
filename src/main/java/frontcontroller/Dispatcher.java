package frontcontroller;

import controllers.ReimbursementController;
import controllers.UserController;
import io.javalin.Javalin;
import org.h2.engine.User;

public class Dispatcher {

    UserController userController = new UserController();
    ReimbursementController reimbursementController = new ReimbursementController();

    public Dispatcher(Javalin app){

        app.get("/users", UserController::getAllUsers);
        app.get("/users/{id}", UserController::getOneUser);
        app.post("/users", UserController::createUser);
        app.patch("/users/{id}", UserController::updateUser);
        app.delete("/users/{id}", UserController::deleteUser);

        app.get("/reimbursements", ReimbursementController::getAllReimbursements);
        //app.get("/reimbursements/{reimbId}", ReimbursementController::getOneReimbursement);
        app.get("/reimbursements/{userId}", ReimbursementController::getUserReimbursements);
        app.post("/reimbursements", ReimbursementController::createReimbursement);
        app.patch("/reimbursements/{reimbId}", ReimbursementController::updateReimbursement);
        app.delete("/reimbursements/{reimbId}", ReimbursementController::deleteReimbursement);

    }
}
