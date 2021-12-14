package Services;

import dao.UsersDao;
import dao.UsersDaoImpl;
import models.Users;

import java.util.List;

public class UserService {
    UsersDao usersDao;

    public UserService(){ this.usersDao = new UsersDaoImpl(); }

    public UserService(UsersDao usersDao) { this.usersDao = usersDao; }

    public List<Users> getAllUsers() { return this.usersDao.getAllUsers();}

    public Users getOneUser(Integer userId){ return this.usersDao.getOneUser(userId); }

    public Boolean createUser(Users user) { return this.usersDao.createUser(user); }

    public Boolean updateUser(Integer userId, String column, String value) {
        return this.usersDao.updateUser(userId, column, value);
    }

    public Boolean deleteUser( Integer userId) { return this.usersDao.deleteUser(userId);};

}
