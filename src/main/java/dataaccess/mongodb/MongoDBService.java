package dataaccess.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author Johannes
 */
public class MongoDBService {
    public static MongoDatabase database;

    public static void createDataBase(String databaseString) {
        Properties properties = new Properties();
        InputStream inputStream = MongoDBService.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        configureWarnings();
        CodecRegistry registry = createRegistry();
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://" + properties.getProperty("username") + ":" + properties.getProperty("password") + "@aws-eu-north-1-0.g1n2r9w.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(registry)
                        .build();
        try {
            MongoClient mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(databaseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CodecRegistry createRegistry() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        return fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);
    }

    private static void configureWarnings() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);
    }
}
