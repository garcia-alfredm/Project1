package controllers;

import Services.ReimbursementService;
import Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UpdateDTO;
import io.javalin.http.Context;
import models.Reimbursement;

import java.util.List;

public class ReimbursementController {
    static ReimbursementService reimbursementService = new ReimbursementService();
    static UserService userService = new UserService();

    public ReimbursementController(){}

    public static void getAllReimbursements(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursements();

        String jsonString = new ObjectMapper().writeValueAsString(reimbursements);
        context.result(jsonString);
    }

    public static void getOneReimbursement(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        Integer reimbId = Integer.parseInt(context.pathParam("reimbId"));

        Reimbursement reimbursement = reimbursementService.getOneReimbursement(reimbId);
        context.result(new ObjectMapper().writeValueAsString(reimbursement));
    }

    public static void getUserReimbursements(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        Integer userId = Integer.parseInt(context.pathParam("userId"));

        List<Reimbursement> reimbursements = reimbursementService.getUserReimbursements(userId);
        String jsonString = new ObjectMapper().writeValueAsString(reimbursements);
        context.result(jsonString);
    }

    public static void createReimbursement(Context context){
        Reimbursement reimbursement = context.bodyAsClass(Reimbursement.class);
        if(reimbursementService.createReimbursement(reimbursement)){
            context.result("Reimbursement created");
        } else {
            context.result("Failed to create reimbursement");
        }
    }

    public static void updateReimbursement(Context context){
        //todo find way to get reimbursement id to context obj
        // likely have to use context body
        Integer reimbId = Integer.parseInt(context.pathParam("reimbId"));
        UpdateDTO updateDTO = context.bodyAsClass(UpdateDTO.class);
        //String columnValue = context.body();
        //resolverid and statusId
        /*String values[] = columnValue.split(" ");
        Integer resolverId = Integer.parseInt(values[0]);
        Integer statusId = Integer.parseInt(values[1]);*/

        //Boolean result = reimbursementService.updateReimbursement(reimbId, resolverId, statusId);
        Boolean result = reimbursementService.updateReimbursement(
                reimbId, updateDTO.getResolverId(), updateDTO.getStatusId());
        if(result){
            context.result("Reimbursement updated");
        } else {
            context.result("Error");
        }
    }

    public static void deleteReimbursement(Context context){
        Integer reimbId = Integer.parseInt(context.pathParam("reimbId"));

        Boolean result = reimbursementService.deleteReimbursement(reimbId);

        if(result){
            context.result("Reimbursement deleted");
        } else {
            context.result("Error");
        }
    }
}
