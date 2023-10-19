package euphoric.haven.euphoric_haven;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Place {
    private final StringProperty name;

    private final StringProperty description;

    private final DoubleProperty rating;

    private final StringProperty resourcePath;

    private final StringProperty city;

    public Place() {
        name = new SimpleStringProperty(null);
        description = new SimpleStringProperty(null);
        rating = new SimpleDoubleProperty(0.0);
        resourcePath = new SimpleStringProperty(null);
        city = new SimpleStringProperty(null);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getRating() {
        return rating.get();
    }

    public DoubleProperty ratingProperty() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating.set(rating);
    }

    public String getResourcePath() {
        return resourcePath.get();
    }

    public StringProperty resourcePathProperty() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath.set(resourcePath);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }
}
