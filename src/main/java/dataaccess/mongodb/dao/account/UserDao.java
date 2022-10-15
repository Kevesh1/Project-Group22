package dataaccess.mongodb.dao.account;

import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.account.AccountDto;
import dataaccess.mongodb.dto.account.UserDto;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDao implements IUserDao {

    MongoCollection<UserDto> collection = MongoDBService.database.getCollection(
            AccountDto.class.getSimpleName().toLowerCase(Locale.ROOT), UserDto.class);

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void updateUser(User user) {
        /*UserDto userDto = modelMapper.map(user, UserDto.class);
        System.out.println(user.getFirstName());
        System.out.println(userDto.getFirstName());
        UserDto newUser = new UserDto()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPassword(user.getPassword());
        collection.insertOne(newUser);*/

        /*Document doc = new Document("firstname", user.getFirstName())
                .append("lastname", user.getLastName())
                .append("password", user.getPassword());
        collection.insertOne(doc);*/
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addUser(Account account, User user) {

    }

    @Override
    public void deleteUser(User user) {

    }


    @Override
    public Optional<Account> getUserById(ObjectId id) {
        return Optional.empty();
    }

    @Override
    public List<Account> getAllAccounts() {
        return null;
        /*ArrayList<User> userArray = new ArrayList<>();
        collection.find(new Document(), UserDto.class).into(new ArrayList<UserDto>())
                .forEach(user -> userArray.add(new User(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPassword())));
        return userArray;*/
    }
}
