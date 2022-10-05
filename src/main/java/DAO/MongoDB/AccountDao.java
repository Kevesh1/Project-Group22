package DAO.MongoDB;

import DAO.IAccountDao;
import DAO.IDao;
import DAO.MongoDB.DTO.AccountDto;
import DAO.MongoDB.DTO.UserDto;
import budgetapp.model.Account;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.*;
import java.util.stream.Collectors;

public class AccountDao implements IAccountDao {

    MongoCollection<AccountDto> collection = MongoDBService.database.getCollection(
            AccountDto.class.getSimpleName().toLowerCase(Locale.ROOT), AccountDto.class);

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Account> getAccountById(ObjectId id) {
        AccountDto accountDto = collection.find(Filters.eq("_id", id), AccountDto.class).first();
        return Optional.of(modelMapper.map(accountDto, Account.class));
    }

    @Override
    public List<Account> getAllAccounts() {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        ArrayList<Account> accounts = new ArrayList<>();
        collection.find(new Document(), AccountDto.class)
                .into(new ArrayList<>())
                .forEach(accountDto -> accounts.add(modelMapper.map(accountDto, Account.class)));
        return accounts;
    }

    @Override
    public void updateAccount(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        List<UserDto> users = account.getUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        accountDto.setUsers(users);
        //collection.updateOne(accountDto.getId());
    }

    @Override
    public void deleteAccount(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        //collection.deleteOne(new Document(),Filters.eq("id", account.getUsername()), accountDto);
    }
}
