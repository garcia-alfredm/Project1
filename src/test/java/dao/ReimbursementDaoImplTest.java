package dao;

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

    Date date;

    ReimbursementDaoImplTest() {
        this.usersDao = new UsersDaoImpl(H2Util.url, H2Util.username, H2Util.password);
        this.reimbursementDao = new ReimbursementDaoImpl(H2Util.url, H2Util.username, H2Util.password);
        this.date = new Date(Calendar.getInstance().getTime().getTime());
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
                "One", "user1@email.com", 1 );
        usersDao.createUser(user);

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
    }

    @Test
    void createReimbursement() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 );
        usersDao.createUser(user);

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
    }

    @Test
    void deleteReimbursement() {
        //assign
        Users user = new Users(1, "user1", "password", "User",
                "One", "user1@email.com", 1 );
        usersDao.createUser(user);

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