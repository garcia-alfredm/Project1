package Services;

import dao.UsersDao;
import models.Users;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UsersDao usersDao = Mockito.mock(UsersDao.class);

    UserService userService;

    public UserServiceTest(){ this.userService = new UserService(usersDao);};

    @Test
    void getAllUsers() {
        //assign
        List<Users> expectedResults = new ArrayList<>();
        expectedResults.add(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 ));
        expectedResults.add(new Users(2, "user2", "password", "User",
                "Two", "user2@email.com", 1 ));
        Mockito.when(usersDao.getAllUsers()).thenReturn(expectedResults);

        //act
        List<Users> actualResults = userService.getAllUsers();

        //assert
        assertEquals(expectedResults.toString(), actualResults.toString());
    }

    @Test
    void getOneUser() {
        //assign
        Users expectedResult = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 );
        Mockito.when(usersDao.getOneUser(1)).thenReturn(expectedResult);

        //act
        Users actualResult = userService.getOneUser(1);

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void createUser() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 );

        //act
        userService.createUser(user);

        //assert
        Mockito.verify(usersDao, Mockito.times(1)).createUser(user);
    }

    @Test
    void updateUser() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 );
        userService.createUser(user);

        //act
        userService.updateUser(1, "ers_password", "p4ssw0rd");

        //assert
        Mockito.verify(usersDao, Mockito.times(1)).
                updateUser(1, "ers_password", "p4ssw0rd");

    }

    @Test
    void deleteUser() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 );
        userService.createUser(user);

        //act
        userService.deleteUser(1);

        //assert
        Mockito.verify(usersDao, Mockito.times(1)).deleteUser(1);
    }
}