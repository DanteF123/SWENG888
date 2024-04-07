package sweng.dante.practice5.fragment.Class;

public class City {
    private String Name;
    private String Population;

    public City() {
    }

    public City(String name, String population) {
        Name = name;
        Population = population;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }
}
