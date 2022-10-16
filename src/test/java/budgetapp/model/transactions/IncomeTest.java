package budgetapp.model.transactions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IncomeTest {

    private Income income;


    @Test
    void test() {
        income = new Income(10.0,"L","05-05-05");
    }

}

