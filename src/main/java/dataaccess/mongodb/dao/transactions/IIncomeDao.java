package dataaccess.mongodb.dao.transactions;

import budgetapp.model.transactions.Income;

import java.util.List;

public interface IIncomeDao {

    Income addIncome(Income income, String budgetMonthId);

    Income deleteIncome(Income income);

    List<Income> getAllIncomesByBudgetMonth(String budgetMonthId);
}
