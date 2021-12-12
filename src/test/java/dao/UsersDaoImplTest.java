package dao;

import org.junit.jupiter.api.*;
import util.H2Util;

public class UsersDaoImplTest {

    UsersDao usersDao;

    UsersDaoImplTest(){ this.usersDao = new UsersDaoImpl(H2Util.url, H2Util.username, H2Util.password); }

    @BeforeAll
    void setLookUpTables(){
        H2Util.createUserRolesTable();
    }

    @AfterAll
    void dropLookUpTables(){
        H2Util.dropUserRolesTable();
    }

    @BeforeEach
    void setUp() {
        H2Util.createErsUsersTable();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropErsUsersTable();
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getOneUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}
