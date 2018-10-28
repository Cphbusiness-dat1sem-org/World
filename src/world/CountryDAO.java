package world;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    public static void main(String[] args) throws Exception {
        CountryDAO dao = new CountryDAO();
        for(String value : dao.getAllCountries()){
            System.out.println(value);
        }
        System.out.println("-----------------------------");
        System.out.println(dao.getContinent("uganda"));
        
    }
    
    public List<String> getAllCountries() throws Exception {
        List<String> list = new ArrayList<>();
        String query = "SELECT DISTINCT `CountryName` FROM `country`;";
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            String countryName = rs.getString("CountryName");
            list.add(countryName);
        }
        return list;
    }
    
    public List<String> getContinents() throws Exception{
        List<String> list = new ArrayList<>();
        String query = "SELECT DISTINCT `Continent` FROM `country`;";
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            String continent = rs.getString("Continent");
            list.add(continent);
        }
        return list;
    }
    
    public List<String> getCountries(String continent) throws Exception{
        List<String> list = new ArrayList<>();
        String query = "SELECT DISTINCT `CountryName` FROM `country` WHERE `continent` = '"+continent+"';";
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            String countryName = rs.getString("CountryName");
            list.add(countryName);
        }
        return list;
    }
    
    public String getContinent(String country) throws Exception {
        String query = "SELECT `Continent` FROM `country` WHERE `CountryName` = '"+country+"';";
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if(rs.next()){
            String continent = rs.getString("Continent");
            return continent;
        }
        return null;
    }
}
