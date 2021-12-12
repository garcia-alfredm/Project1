package dao;

/* defining CRUD operations */

import models.Users;

import java.util.List;

public interface UsersDao {

    List<Users> getAllUsers();

    //todo override with username, password?
    Users getOneUser(Integer userId);

    Boolean createUser(Users user);

    //todo, allow update name, email, username, password?
    Boolean updateUser(Integer userId, String value);

    Boolean deleteUser(Integer userId);

}
