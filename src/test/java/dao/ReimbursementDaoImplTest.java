package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementDaoImplTest {

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

        //act

        //assert
    }

    @Test
    void getOneReimbursement() {
    }

    @Test
    void createReimbursement() {
    }

    @Test
    void updateReimbursement() {
    }

    @Test
    void deleteReimbursement() {
    }
}