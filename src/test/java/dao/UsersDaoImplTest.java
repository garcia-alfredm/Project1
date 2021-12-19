package dao;

import models.Users;
import org.junit.jupiter.api.*;
import util.H2Util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersDaoImplTest {

    UsersDao usersDao;

    UsersDaoImplTest(){ this.usersDao = new UsersDaoImpl(H2Util.url, H2Util.username, H2Util.password); }

    @BeforeEach
    void setUp() {
        H2Util.createUserRolesTable();
        H2Util.createErsUsersTable();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropErsUsersTable();
        H2Util.dropUserRolesTable();
    }

    @Test
    void getAllUsers() {
        //assign
        List<Users> expectedResults = new ArrayList<>();
        expectedResults.add(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" ));
        expectedResults.add(new Users(2, "user2", "password", "User",
                "Two", "user2@email.com", "MANAGER" ));
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1));
        usersDao.createUser(new Users(2, "user2", "password", "User",
                "Two", "user2@email.com", 2 ));

        //act
        List<Users> actualResults = usersDao.getAllUsers();

        //assert
        assertEquals(expectedResults.toString(), actualResults.toString());
    }

    @Test
    void getOneUser() {
        //assign
        Users expectedResult = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" );
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1));

        //act
        Users actualResult = usersDao.getOneUser(expectedResult.getId());

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void createUser() {
        //assign
        List<Users> expectedResults = new ArrayList<>();
        expectedResults.add(new Users(1, "user1", "password", "User", "One", "user1@email.com", "EMPLOYEE" ));
        expectedResults.add(new Users(2, "user2", "password", "User", "Two", "user2@email.com", "EMPLOYEE" ));

        //act
        usersDao.createUser(new Users(1, "user1", "password", "User", "One", "user1@email.com", 1 ));
        usersDao.createUser(new Users(2, "user2", "password", "User", "Two", "user2@email.com", 2 ));
        Integer actualResult = usersDao.getAllUsers().size();

        //assert
        assertEquals(expectedResults.size(), actualResult);
    }

    @Test
    void updateUser() {
        //assign
        Boolean expectedResult = true;
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" );
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 ));

        //act
        Boolean actualResult = usersDao.updateUser(1,"ers_password", "p4ssw0rd");

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteUser() {
        //assign
        Boolean expectedResult = true;
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" );
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 ));

        //act
        Boolean actualResult = usersDao.deleteUser(user.getId());

        //assert
        assertEquals(expectedResult, actualResult);
    }
}
