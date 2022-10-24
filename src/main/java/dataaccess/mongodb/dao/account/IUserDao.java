package dataaccess.mongodb.dao.account;

import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

/**
 * @author Johannes
 */
public interface IUserDao {
    Optional<Account> getUserById(ObjectId id);
    List<Account> getAllAccounts();
    void updateUser(User user);

    void addUser(User user);

    void addUser(User user, Account account);

    void deleteUser(User user);

    List<User> getUsersByUsername(String username);
}
