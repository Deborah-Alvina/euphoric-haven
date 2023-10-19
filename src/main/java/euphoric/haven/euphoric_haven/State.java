package euphoric.haven.euphoric_haven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class State {
    private final StringProperty name;

    public void setName(String name) {
        this.name.set(name);
    }

    public State() {
        name = new SimpleStringProperty(null);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
}