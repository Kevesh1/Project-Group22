package dataaccess.mongodb.dao.account;

import budgetapp.model.account.User;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.account.AccountDto;
import dataaccess.mongodb.dto.account.UserDto;
import budgetapp.model.account.Account;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.and;

public class AccountDao implements IAccountDao {

    MongoCollection<AccountDto> collection = MongoDBService.database.getCollection(
            Account.class.getSimpleName().toLowerCase(Locale.ROOT), AccountDto.class);

    private final ModelMapper modelMapper;
    private final UserDao userDao = new UserDao();

    public AccountDao() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public Optional<Account> getAccountById(ObjectId id) {
        AccountDto accountDto = collection.find(Filters.eq("_id", id), AccountDto.class).first();
        return Optional.of(modelMapper.map(accountDto, Account.class));
    }

    @Override
    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();
        collection.find(new Document(), AccountDto.class)
                .into(new ArrayList<>())
                .forEach(accountDto -> accounts.add(modelMapper.map(accountDto, Account.class)));
        return accounts;
    }

    @Override
    public Account addAccount(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        collection.insertOne(accountDto);
        User user = new User("Test", null);
        userDao.addUser(user, account);
        Account acc = modelMapper.map(accountDto, Account.class);
        System.out.println("ID");
        System.out.println(acc.getId());
        return acc;
    }

    @Override
    public void updateAccount(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        List<UserDto> users = account.getUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        accountDto.setUsers(users);
        collection.insertOne(accountDto);
    }

    @Override
    public void deleteAccount(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        //collection.deleteOne(new Document(), );
    }

    @Override
    public Optional<Account> validateAccount(String username, String password) {
        Optional<AccountDto> account = Optional.ofNullable(collection.find(
                and(Filters.eq("username", username), Filters.eq("password", password)), AccountDto.class).first());
        Optional<Account> newAccount = Optional.empty();
        if (account.isPresent()) {
            newAccount = Optional.of(modelMapper.map(account.get(), Account.class));
        }
        return newAccount;
    }

    @Override
    public boolean accountExists(String username) {
        boolean accountExists = true;
        Optional<AccountDto> account = Optional.ofNullable(collection.find(Filters.eq("username", username), AccountDto.class).first());
        if(account.isEmpty()) {
            accountExists = false;
        }
        return accountExists;
    }
}
