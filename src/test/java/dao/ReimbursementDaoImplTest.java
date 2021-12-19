package dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import models.Reimbursement;
import models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementDaoImplTest {

    UsersDao usersDao;
    ReimbursementDao reimbursementDao;

    //Date date;
    java.util.Date value;
    String date;

    ReimbursementDaoImplTest() {
        this.usersDao = new UsersDaoImpl(H2Util.url, H2Util.username, H2Util.password);
        this.reimbursementDao = new ReimbursementDaoImpl(H2Util.url, H2Util.username, H2Util.password);
        this.value = new Date(Calendar.getInstance().getTime().getTime());
        this.date = this.value + " 00:00:00";
    }

    @BeforeEach
    void setUp() {
        H2Util.createReimbTypeTable();
        H2Util.createReimbStatusTable();
        H2Util.createUserRolesTable();
        H2Util.createErsUsersTable();
        H2Util.createErsReimburseTable();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropErsReimburseTable();
        H2Util.dropErsUsersTable();
        H2Util.dropReimbTypeTable();
        H2Util.dropReimbStatusTable();
        H2Util.dropUserRolesTable();
    }

    @Test
    void getAllReimbursements() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" );
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 ));

        List<Reimbursement> expectedResults = new ArrayList<>();
        expectedResults.add(new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1));
        expectedResults.add(new Reimbursement(2, new BigDecimal("10.00"), date,
                null, "McDonalds", null, 1, 0, 1, 3));

        reimbursementDao.createReimbursement(expectedResults.get(0));
        reimbursementDao.createReimbursement(expectedResults.get(1));

        //act
        List<Reimbursement> actualResults = reimbursementDao.getAllReimbursements();

        //assert
        assertEquals(expectedResults.toString(), actualResults.toString());
    }

    @Test
    void getOneReimbursement() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" );
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 ));

        Reimbursement expectedResult = new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1);
        reimbursementDao.createReimbursement(expectedResult);

        //act
        Reimbursement actualResult = reimbursementDao.getOneReimbursement(1);

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void createReimbursement() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" );
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 ));

        List<Reimbursement> expectedResults = new ArrayList<>();
        expectedResults.add(new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1));
        expectedResults.add(new Reimbursement(2, new BigDecimal("10.00"), date,
                null, "McDonalds", null, 1, 0, 1, 3));

        //act
        reimbursementDao.createReimbursement(expectedResults.get(0));
        reimbursementDao.createReimbursement(expectedResults.get(1));

        Integer actualResults = reimbursementDao.getAllReimbursements().size();

        //assert
        assertEquals(expectedResults.size(), actualResults);
    }

    @Test
    void updateReimbursement() {
        //assign
        Boolean expectedResult = true;
        List<Users> users = new ArrayList<>();
        users.add(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" ));
        users.add(new Users(2, "user2", "password", "User",
                "Two", "user2@email.com", "MANAGER" ));
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1));
        usersDao.createUser(new Users(2, "user2", "password", "User",
                "Two", "user2@email.com", 2 ));

        Reimbursement toPass = new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1);
        reimbursementDao.createReimbursement(toPass);

        //act
        Boolean actualResult = reimbursementDao.updateReimbursement(toPass.getId(), 2, 2);

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteReimbursement() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", "EMPLOYEE" );
        usersDao.createUser(new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1));

        List<Reimbursement> expectedResults = new ArrayList<>();
        expectedResults.add(new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1));
        expectedResults.add(new Reimbursement(2, new BigDecimal("10.00"), date,
                null, "McDonalds", null, 1, 0, 1, 3));
        reimbursementDao.createReimbursement(expectedResults.get(0));
        reimbursementDao.createReimbursement(expectedResults.get(1));

        //act
        expectedResults.remove(0);
        reimbursementDao.deleteReimbursement(1);
        List<Reimbursement> actualResults = reimbursementDao.getAllReimbursements();

        //assert
        assertEquals(expectedResults.toString(), actualResults.toString());
    }
}