package Services;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import models.Reimbursement;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTest {
    ReimbursementDao reimbursementDao = Mockito.mock(ReimbursementDao.class);
    UserService userService = Mockito.mock(UserService.class);
    ReimbursementService reimbursementService;
    java.util.Date value;
    String date;

    public ReimbursementServiceTest(){
        this.reimbursementService = new ReimbursementService(reimbursementDao, userService);
        this.value = new Date(Calendar.getInstance().getTime().getTime());
        this.date = this.value + " 00:00:00";

    }

    @Test
    void getAllReimbursements() {
        //assign
        List<Reimbursement> expectedResults = new ArrayList<>();
        expectedResults.add(new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1));
        expectedResults.add(new Reimbursement(2, new BigDecimal("10.00"), date,
                null, "McDonalds", null, 1, 0, 1, 3));
        Mockito.when(reimbursementDao.getAllReimbursements()).thenReturn(expectedResults);

        //act
        List<Reimbursement> actualResults = reimbursementService.getAllReimbursements();

        //assert
        assertEquals(expectedResults.toString(), actualResults.toString());

    }

    @Test
    void getOneReimbursement() {
        //assign
        Reimbursement expectedResult = new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1);
        Mockito.when(reimbursementDao.getOneReimbursement(1)).thenReturn(expectedResult);

        //act
        Reimbursement actualResult = reimbursementService.getOneReimbursement(1);

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void createReimbursement() {
        //assign
        Reimbursement expectedResult = new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1);

        //act
        reimbursementService.createReimbursement(expectedResult);

        //assert
        Mockito.verify(reimbursementDao, Mockito.times(1)).
                createReimbursement(expectedResult);

    }

    @Test
    void updateReimbursement() {
        //assign
        Reimbursement expectedResult = new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1);

        //act
        reimbursementService.updateReimbursement(1,2,2);

        //assert
        Mockito.verify(reimbursementDao, Mockito.times(1)).
                updateReimbursement(1,2,2);
    }

    @Test
    void deleteReimbursement() {
        //assign
        Reimbursement expectedResult = new Reimbursement(1, new BigDecimal("200.00"), date,
                null, "Motel 6", null, 1, 0, 1, 1);

        //act
        reimbursementService.deleteReimbursement(1);

        //assert
        Mockito.verify(reimbursementDao, Mockito.times(1)).deleteReimbursement(1);
    }
}