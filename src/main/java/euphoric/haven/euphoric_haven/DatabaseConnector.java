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

    final MongoCollection<State> states;

    final MongoCollection<City> cities;

    static User currentUser;

    public static DatabaseConnector instance;

    private DatabaseConnector() {
        client = MongoClients.create("mongodb://localhost:27017");
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).register(User.class)
                .register(City.class).register(Place.class).register(State.class).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        this.db = client.getDatabase("euphoric_haven").withCodecRegistry(pojoCodecRegistry);
        users = db.getCollection("users", User.class);
        states = db.getCollection("states", State.class);
        places = db.getCollection("places", Place.class);
        cities = db.getCollection("cities", City.class);
    }

    static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
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

    List<State> getAllStates() {
        List<State> statesList = new ArrayList<>();
        states.find().into(statesList);
        return statesList;
    }

    List<City> getCitiesForState(State state) {
        List<City> citiesList = new ArrayList<>();
        cities.find(eq("state", state.getName())).into(citiesList);
        return citiesList;
    }

    List<Place> getPlacesForCity(City city) {
        List<Place> placesList = new ArrayList<>();
        places.find(eq("city", city.getName())).into(placesList);
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

        if (userList.get(0).getPassword().equals(password)) {
            currentUser = userList
                    .get(0);
            return true;
        }
        return false;
    }

    String signupUser(String name,String password,String username) {
        var user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        users.insertOne(user);
        return name;
    }
}
