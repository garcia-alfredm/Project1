package dao;

import models.Reimbursement;

import java.util.List;

public interface ReimbursementDao {

    List<Reimbursement> getAllReimbursements();

    Reimbursement getOneReimbursement(Integer reimburseId);

    Boolean createReimbursement(Reimbursement reimburse);

    Boolean updateReimbursement(Integer reimburseId, String resolver);

    Boolean deleteReimbursement(Integer reimburseId);
}
