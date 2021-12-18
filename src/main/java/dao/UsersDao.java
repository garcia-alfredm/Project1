package dao;

/* defining CRUD operations */

import models.Users;

import java.util.List;

public interface UsersDao {

    List<Users> getAllUsers();

    Users getOneUser(Integer userId);

    Users getOneUser(String identifier, String password);

    Boolean createUser(Users user);

    //todo, allow update name, email, username, password?
    Boolean updateUser(Integer userId, String column, String value);

    Boolean deleteUser(Integer userId);

}
