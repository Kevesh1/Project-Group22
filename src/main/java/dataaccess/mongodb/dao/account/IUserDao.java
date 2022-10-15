package dataaccess.mongodb.dao.account;

import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IUserDao {
    Optional<Account> getUserById(ObjectId id);
    List<Account> getAllAccounts();
    void updateUser(User user);

    void addUser(User user);

    void addUser(Account account, User user);

    void deleteUser(User user);
}
