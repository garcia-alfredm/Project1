package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Util {
    /* location of dummy db inc username, password */
    public static String url = "jdbc:h2:./h2/db";
    public static String username = "sa";
    public static String password = "sa";

    /* Creating look up tables */
    /* ReimbStatusTable */
    public static void createReimbStatusTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "CREATE TABLE ers_reimbursement_status(\n" +
                    "\treimb_status_id serial PRIMARY KEY,\n" +
                    "\treimb_status varchar(10) NOT NULL\n" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropReimbStatusTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "DROP TABLE ers_reimbursement_status;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ReimbTypeTable */
    public static void createReimbTypeTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "CREATE TABLE ers_reimbursement_type(\n" +
                    "\treimb_type_id serial PRIMARY KEY,\n" +
                    "\treimb_type varchar(10) NOT NULL\n" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropReimbTypeTable() {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "DROP TABLE ers_reimbursement_type;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* userRolesTable */
    public static void createUserRolesTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "CREATE TABLE ers_user_roles(\n" +
                    "\ters_user_role_id serial PRIMARY KEY,\n" +
                    "\tuser_role varchar(10) NOT NULL\n" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(sql);



            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropUserRolesTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "DROP TABLE ers_user_roles;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ErsUsersTable */
    public static void createErsUsersTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "CREATE TABLE ers_users(\n" +
                    "\ters_user_id serial PRIMARY KEY,\n" +
                    "\ters_username varchar(50) UNIQUE NOT NULL,\n" +
                    " \ters_password varchar(50) NOT NULL DEFAULT 'password',\n" +
                    "\tuser_first_name varchar(100) NOT NULL,\n" +
                    "\tuser_last_name varchar(100) NOT NULL,\n" +
                    "\tuser_email varchar(150) UNIQUE NOT NULL,\n" +
                    "\tuser_role_id_fk int REFERENCES ers_user_roles(ers_user_role_id) NOT NULL\n" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropErsUsersTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "DROP TABLE ers_users;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ErsReimburseTable */
    public static void createErsReimburseTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "CREATE TABLE ers_reimbursement( \n" +
                    "\treimb_id serial PRIMARY KEY,\n" +
                    "\treimb_amount NUMERIC (7,2) NOT NULL,\n" +
                    "\treimb_submitted timestamp DEFAULT now(),\n" +
                    "\treimb_resolved timestamp DEFAULT NULL,\n" +
                    "\treimb_description varchar(250) DEFAULT NULL,\n" +
                    "\treimb_receipt bytea UNIQUE DEFAULT NULL,\n" +
                    "\treimb_author_fk int REFERENCES ers_users(ers_user_id) NOT NULL,\n" +
                    "\treimb_resolver_fk int REFERENCES ers_users(ers_user_id) DEFAULT NULL,\n" +
                    "\treimb_status_id_fk int REFERENCES ers_reimbursement_status(reimb_status_id) DEFAULT 1,\n" +
                    "\treimb_type_id_fk int REFERENCES ers_reimbursement_type(reimb_type_id) NOT NULL\n" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropErsReimburseTable(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "DROP TABLE ers_reimbursement;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}