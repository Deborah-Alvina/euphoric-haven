package euphoric.haven.euphoric_haven;

public class Place {
    final String name;

    final String description;

    final double rating;

    final String resourcePath;

    final String city;

    public Place(String name, String description, double rating, String resourcePath, String city) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.resourcePath = resourcePath;
        this.city = city;
    }
    public Place() {
        name = null;
        description = null;
        rating = 0.0;
        resourcePath = null;
        city = null;
    }
}
