package world;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    
    public static void main(String[] args) throws Exception {
        CityDAO dao = new CityDAO();
        for(City city : dao.getCities("United States")){
            System.out.println(city);
        }
    }
    
    public List<City> getCities(String country) throws Exception {
        List<City> l = new ArrayList<>();
        String query = "SELECT * FROM `city` NATURAL JOIN `country` WHERE `CountryName` = '"+country+"';";
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            int id = rs.getInt("ID");
            int population = rs.getInt("Population");
            String cityName = rs.getString("CityName");
            String district = rs.getString("District");
            City city = new City(id, population, cityName, district);
            l.add(city);
        }
        return l;
    }

}
