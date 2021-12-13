package dao;

import models.Reimbursement;
import org.apache.log4j.Logger;

import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao {
    String url;
    String username;
    String password;

    Logger logger = Logger.getLogger(ReimbursementDaoImpl.class);

    public ReimbursementDaoImpl(){
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/ers";
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ReimbursementDaoImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        return null;
    }

    @Override
    public Reimbursement getOneReimbursement(Integer reimburseId) {
        return null;
    }

    @Override
    public Boolean createReimbursement(Reimbursement reimburse) {
        return null;
    }

    @Override
    public Boolean updateReimbursement(Integer reimburseId, String resolver) {
        return null;
    }

    @Override
    public Boolean deleteReimbursement(Integer reimburseId) {
        return null;
    }
}
