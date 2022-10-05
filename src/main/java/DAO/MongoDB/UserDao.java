package DAO.MongoDB;

import DAO.IDao;
import DAO.IUserDao;
import DAO.MongoDB.DTO.UserDto;
import budgetapp.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDao implements IUserDao {

    MongoCollection<UserDto> collection = MongoDBService.database.getCollection(
            UserDto.class.getSimpleName().toLowerCase(Locale.ROOT), UserDto.class);

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<User> get(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {;
        ArrayList<User> userArray = new ArrayList<>();
        collection.find(new Document(), UserDto.class).into(new ArrayList<UserDto>())
                .forEach(user -> userArray.add(new User(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPassword())));
        return userArray;
    }

    @Override
    public void save(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        System.out.println(user.getFirstName());
        System.out.println(userDto.getFirstName());
        UserDto newUser = new UserDto()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPassword(user.getPassword());
        collection.insertOne(newUser);

        /*Document doc = new Document("firstname", user.getFirstName())
                .append("lastname", user.getLastName())
                .append("password", user.getPassword());
        collection.insertOne(doc);*/
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
