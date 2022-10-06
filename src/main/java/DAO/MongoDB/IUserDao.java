package DAO.MongoDB;

import DAO.IDao;
import budgetapp.model.Account;
import budgetapp.model.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IUserDao {
    Optional<Account> getUserById(ObjectId id);
    List<Account> getAllAccounts();
    void updateUser(User user);
    void deleteUser(User user);
}
