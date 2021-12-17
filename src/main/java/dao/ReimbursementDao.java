package dao;

import models.Reimbursement;

import java.util.List;

public interface ReimbursementDao {

    List<Reimbursement> getAllReimbursements();

    Reimbursement getOneReimbursement(Integer reimburseId);

    List<Reimbursement> getUserReimbursements(Integer userId);

    Boolean createReimbursement(Reimbursement reimburse);

    Boolean updateReimbursement(Integer reimburseId, Integer resolverId, Integer statusId);

    Boolean deleteReimbursement(Integer reimburseId);
}
