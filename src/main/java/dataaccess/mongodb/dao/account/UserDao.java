package dataaccess.mongodb.dao.account;

import com.mongodb.client.model.Filters;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.account.UserDto;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDao implements IUserDao {




    MongoCollection<UserDto> collection = MongoDBService.database.getCollection(
            User.class.getSimpleName().toLowerCase(Locale.ROOT), UserDto.class);

    private final ModelMapper modelMapper;

    public UserDao() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);


    }

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
    public void addUser(User user, Account account) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setAccount(account.getUsername());
        collection.insertOne(userDto);
        System.out.println(userDto);
        User user1 = modelMapper.map(userDto, User.class);
        //user1.setId(userDto.getId().toString());
        System.out.println(user1);
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getUsersByUsername(String username) {
        List<User> users = new ArrayList<>();
        collection.find(Filters.eq("account", username))
                .into(new ArrayList<>())
                .forEach(userDto -> users.add(modelMapper.map(userDto, User.class).setId(userDto.getId().toString())));
        return users;
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
