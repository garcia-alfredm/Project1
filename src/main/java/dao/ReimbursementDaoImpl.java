package dao;

import models.Reimbursement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
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
            String sql = "SELECT * FROM ers_reimbursement;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimb = new Reimbursement(rs.getInt(1), rs.getBigDecimal(2),
                        rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
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
                        rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return reimbursement;
    }

    @Override
    public Boolean createReimbursement(Reimbursement reimburse) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            /* id amount submitted resolved desc receiptImg author resolver status typeid */
            String sql = "INSERT INTO ers_reimbursement VALUES(DEFAULT, ?, ?, DEFAULT, ?, ?, ?, DEFAULT, ?, ? );";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, reimburse.getAmount());
            ps.setDate(2, (Date) reimburse.getSubmitted());
            ps.setString(3, reimburse.getDescription());
            ps.setBytes(4, reimburse.getReceiptImg());
            ps.setInt(5, reimburse.getAuthor());
            ps.setInt(6, reimburse.getStatus());
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
