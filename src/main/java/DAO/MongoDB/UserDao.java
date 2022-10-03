package DAO.MongoDB;

import DAO.Dao;
import budgetapp.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDao implements Dao<User> {

    MongoCollection<DAO.MongoDB.DTO.User> collection = MongoDBService.database.getCollection(
            DAO.MongoDB.DTO.User.class.getSimpleName().toLowerCase(Locale.ROOT), DAO.MongoDB.DTO.User.class);

    @Override
    public Optional<User> get(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {;
        ArrayList<User> userArray = new ArrayList<>();
        collection.find(new Document(), DAO.MongoDB.DTO.User.class).into(new ArrayList<DAO.MongoDB.DTO.User>())
                .forEach(user -> userArray.add(new User(
                        user.getFirstname(),
                        user.getLastName(),
                        user.getPassword())));
        return userArray;
    }

    @Override
    public void save(User user) {
        DAO.MongoDB.DTO.User newUser = new DAO.MongoDB.DTO.User()
                .setFirstname(user.getFirstName())
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
