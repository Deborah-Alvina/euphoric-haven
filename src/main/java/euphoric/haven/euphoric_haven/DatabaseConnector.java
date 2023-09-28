package euphoric.haven.euphoric_haven;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DatabaseConnector {
    final MongoDatabase db;

    final MongoClient client;

    final MongoCollection<User> users;

    final MongoCollection<Place> places;

    static User currentUser;

    public DatabaseConnector() {
        client = MongoClients.create("mongodb://localhost:27017");
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).register(User.class).register(Place.class).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        this.db = client.getDatabase("nexpense").withCodecRegistry(pojoCodecRegistry);
        users = db.getCollection("users", User.class);
        places = db.getCollection("places", Place.class);
    }

    List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        users.find().into(userList);
        return userList;
    }

    List<Place> getAllPlaces() {
        List<Place> placesList = new ArrayList<>();
        places.find().into(placesList);
        return placesList;
    }

    List<Place> getPlacesForCity(String city) {
        List<Place> placesList = new ArrayList<>();
        places.find(eq("city", city)).into(placesList);
        return placesList;
    }

    boolean loginUser(String username, String password) {
        // create an empty user list, to be filled by MongoDB driver.
        List<User> userList = new ArrayList<>();

        // Query for users where the 'username' field is equal to the given username
        users.find(eq("username", username)).into(userList);

        if (userList.isEmpty()) {
            // The user list is empty since no users were found with the given name
            return false;
        }

        if (userList.get(0).password.equals(password)) {
            currentUser = userList
                    .get(0);
            return true;
        }
        return false;
    }

    void signupUser(User user) {
        users.insertOne(user);
    }
}
