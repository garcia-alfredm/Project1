package dao;

import models.Reimbursement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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
        List<Reimbursement> reimbursements = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "SELECT er.reimb_id id, er.reimb_amount amount, er.reimb_submitted submitted, er.reimb_resolved resolved, er.reimb_description description,\n" +
                    "er.reimb_receipt receipt, eu1.ers_username author_username, eu1.user_first_name author_first_name, eu1.user_last_name author_last_name, eu1.user_email author_email,\n" +
                    "es.reimb_status staus, et.reimb_type type_name, er.reimb_resolver_fk\n" +
                    "FROM ers_reimbursement er\n" +
                    "INNER JOIN ers_users eu1\n" +
                    "ON er.reimb_author_fk = eu1.ers_user_id \n" +
                    "INNER JOIN ers_reimbursement_status es\n" +
                    "ON er.reimb_status_id_fk = es.reimb_status_id\n" +
                    "INNER JOIN ers_reimbursement_type et\n" +
                    "ON er.reimb_type_id_fk = et.reimb_type_id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimb = new Reimbursement(rs.getInt(1), rs.getBigDecimal(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getBytes(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), rs.getInt(13));
                reimbursements.add(reimb);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return reimbursements;
    }

    @Override
    public Reimbursement getOneReimbursement(Integer reimburseId) {
        Reimbursement reimbursement = null;
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM ers_reimbursement where reimb_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimburseId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursement = new Reimbursement(rs.getInt(1), rs.getBigDecimal(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return reimbursement;
    }

    @Override
    public List<Reimbursement> getUserReimbursements(Integer userId) {
        List<Reimbursement> reimbursements = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "SELECT er.reimb_id id, er.reimb_amount amount, er.reimb_submitted submitted, er.reimb_resolved resolved, er.reimb_description description,\n" +
                    "er.reimb_receipt receipt, eu1.ers_username author_username, eu1.user_first_name author_first_name, eu1.user_last_name author_last_name, eu1.user_email author_email,\n" +
                    "es.reimb_status staus, et.reimb_type type_name, er.reimb_resolver_fk\n" +
                    "FROM ers_reimbursement er\n" +
                    "INNER JOIN ers_users eu1\n" +
                    "ON er.reimb_author_fk = eu1.ers_user_id\n" +
                    "INNER JOIN ers_reimbursement_status es\n" +
                    "ON er.reimb_status_id_fk = es.reimb_status_id\n" +
                    "INNER JOIN ers_reimbursement_type et\n" +
                    "ON er.reimb_type_id_fk = et.reimb_type_id\n" +
                    "WHERE er.reimb_author_fk = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimb = new Reimbursement(rs.getInt(1), rs.getBigDecimal(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getBytes(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), rs.getInt(13));
                reimbursements.add(reimb);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return reimbursements;
    }

    @Override
    public Boolean createReimbursement(Reimbursement reimburse) {
        /*java.util.Date value = new Date(Calendar.getInstance().getTime().getTime());
        String date = value + " 00:00:00";*/
        Date date = new Date(Calendar.getInstance().getTime().getTime());

        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            /* id amount submitted resolved desc receiptImg author resolver status typeid */
            String sql = "INSERT INTO ers_reimbursement VALUES(DEFAULT, ?, ?, DEFAULT, ?, ?, ?, DEFAULT, ?, ? );";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, reimburse.getAmount());
            ps.setDate(2, date);
            ps.setString(3, reimburse.getDescription());
            ps.setBytes(4, reimburse.getReceiptImg());
            ps.setInt(5, reimburse.getAuthor());
            ps.setInt(6, reimburse.getStatusId());
            ps.setInt(7, reimburse.getTypeId());

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateReimbursement(Integer reimburseId, Integer resolverId, Integer statusId) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE ers_reimbursement SET reimb_resolved = now(), reimb_resolver_fk = ?"
                    +", reimb_status_id_fk = ? WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, resolverId);
            ps.setInt(2, statusId);
            ps.setInt(3, reimburseId);

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteReimbursement(Integer reimburseId) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM ers_reimbursement WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimburseId);

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
        return true;
    }
}
