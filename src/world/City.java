package world;

public class City {
    private int id, population;
    private String cityName, district;

    public City(int id, int population, String cityName, String district) {
        this.id = id;
        this.population = population;
        this.cityName = cityName;
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public int getPopulation() {
        return population;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDistrict() {
        return district;
    }
    
}
