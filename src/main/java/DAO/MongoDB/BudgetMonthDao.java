package DAO.MongoDB;

import DAO.IDao;
import DAO.MongoDB.DTO.BudgetMonthDto;
import budgetapp.model.BudgetMonth;
import com.mongodb.client.MongoCollection;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class BudgetMonthDao implements IDao<BudgetMonth> {

    MongoCollection<BudgetMonthDto> collection = MongoDBService.database.getCollection(
            BudgetMonthDto.class.getSimpleName().toLowerCase(Locale.ROOT), BudgetMonthDto.class);

    @Override
    public Optional<BudgetMonth> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<BudgetMonth> getAll() {
        /*ArrayList<budgetapp.model.BudgetMonth> budgetMonthArrayListArray = new ArrayList<>();
        collection.find(new Document(), DAO.MongoDB.DTO.BudgetMonth.class).into(new ArrayList<>())
                .forEach(budgetMonth -> budgetMonthArrayListArray.add(new budgetapp.model.BudgetMonth()));
        return budgetMonthArrayListArray;*/
        return null;
    }

    @Override
    public void save(BudgetMonth budgetMonth) {

    }

    @Override
    public void update(BudgetMonth budgetMonth, String[] params) {

    }

    @Override
    public void delete(BudgetMonth budgetMonth) {

    }
}
