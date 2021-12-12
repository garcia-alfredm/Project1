package dao;

import models.Users;
import org.apache.log4j.Logger;

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
        return null;
    }

    @Override
    public Users getOneUser(Integer userId) {
        return null;
    }

    @Override
    public Boolean createUser(Users user) {
        return null;
    }

    @Override
    public Boolean updateUser(Integer userId, String value) {
        return null;
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        return null;
    }
}
