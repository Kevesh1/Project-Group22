package DAO.MongoDB;

import budgetapp.model.Account;
import budgetapp.model.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IAccountDao{
    public Optional<Account> getAccountById(ObjectId id);
    public List<Account> getAllAccounts();
    public void updateAccount(Account account);
    public void deleteAccount(Account account);
    Optional<Account> validateAccount(String username, String password);
    boolean accountExists(String username);
}
