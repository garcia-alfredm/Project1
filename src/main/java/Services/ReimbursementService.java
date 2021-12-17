package Services;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import models.Reimbursement;

import java.util.List;

public class ReimbursementService {
    ReimbursementDao reimbursementDao;
    UserService userService;

    public ReimbursementService() {
        this.reimbursementDao = new ReimbursementDaoImpl();
        this.userService = new UserService();
    }

    public ReimbursementService(ReimbursementDao reimbursementDao, UserService userService) {
        this.reimbursementDao = reimbursementDao;
        this.userService = userService;
    }

    public List<Reimbursement> getAllReimbursements(){
        //todo consider if want to use INNER JOIN to get varchar values in lookup tables
        return this.reimbursementDao.getAllReimbursements();
    }

    public Reimbursement getOneReimbursement(Integer reimburseId){
        return this.reimbursementDao.getOneReimbursement(reimburseId);
    }

    public List<Reimbursement> getUserReimbursements(Integer userId){
        return this.reimbursementDao.getUserReimbursements(userId);
    }

    public Boolean createReimbursement(Reimbursement reimbursement){
        return this.reimbursementDao.createReimbursement(reimbursement);
    }

    public Boolean updateReimbursement(Integer reimburseId, Integer resolverId, Integer statusId){
        return this.reimbursementDao.updateReimbursement(reimburseId, resolverId, statusId);
    }

    public Boolean deleteReimbursement(Integer reimburseId){
        return this.reimbursementDao.deleteReimbursement(reimburseId);
    }
}
