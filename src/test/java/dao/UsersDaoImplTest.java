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
        //H2Util.defineUserRolesTable();
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
                "One", "user1@email.com", 1 ));
        expectedResults.add(new Users(2, "user2", "password", "User",
                "Two", "user2@email.com", 1 ));
        usersDao.createUser(expectedResults.get(0));
        usersDao.createUser(expectedResults.get(1));

        //act
        List<Users> actualResults = usersDao.getAllUsers();

        //assert
        assertEquals(expectedResults.toString(), actualResults.toString());
    }

    @Test
    void getOneUser() {
        //assign
        Users expectedResult = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 );
        usersDao.createUser(expectedResult);

        //act
        Users actualResult = usersDao.getOneUser(expectedResult.getId());

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void createUser() {
        //assign
        List<Users> expectedResults = new ArrayList<>();
        expectedResults.add(new Users("user1", "password", "User",
                "One", "user1@email.com", 1 ));
        expectedResults.add(new Users("user2", "password", "User",
                "Two", "user2@email.com", 1 ));

        //act
        usersDao.createUser(expectedResults.get(0));
        usersDao.createUser(expectedResults.get(1));
        Integer actualResult = usersDao.getAllUsers().size();

        //assert
        assertEquals(expectedResults.size(), actualResult);
    }

    @Test
    void updateUser() {
        //assign

        //act

        //assert
    }

    @Test
    void deleteUser() {
        //assign
        Boolean expectedResult = true;
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 );
        usersDao.createUser(user);

        //act
        Boolean actualResult = usersDao.deleteUser(user.getId());

        //assert
        assertEquals(expectedResult, actualResult);
    }
}
