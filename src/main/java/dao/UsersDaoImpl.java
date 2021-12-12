package dao;

import models.Users;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    String url;
    String username;
    String password;

    Logger logger = Logger.getLogger(UsersDaoImpl.class);

    public UsersDaoImpl() {
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/ers";
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public UsersDaoImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM ers_users;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return users;
    }

    @Override
    public Users getOneUser(Integer userId) {
        Users user = null;
        try {
            Connection conn = DriverManager.getConnection(url ,username, password);
            String sql = "SELECT * FROM ers_users WHERE ers_user_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next() ){
                user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }


        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public Boolean createUser(Users user) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO ers_users VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getRoleId());

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateUser(Integer userId, String value) {
        return false;
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM ers_users WHERE ers_user_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
        return true;
    }
}
