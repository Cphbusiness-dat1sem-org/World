package world;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {
    
    public static void main(String[] args) throws Exception {
        LanguageDAO dao = new LanguageDAO();
        System.out.println(dao.isOfficial("Canada", "English"));
    }
    
    public List<String> getAllLanguages() throws Exception {
        List<String> list = new ArrayList<>();
        String query = "SELECT DISTINCT `Language` FROM `language` ORDER BY `Language` ASC;";
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            String countryName = rs.getString("Language");
            list.add(countryName);
        }
        return list;
    }

    public List<String> getLanguages(String countryName) throws Exception {
        List<String> list = new ArrayList<>();
        String query = "SELECT `Language` FROM `language` NATURAL JOIN `country` WHERE `CountryName` = '"+countryName+"';";
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            String language = rs.getString("Language");
            list.add(language);
        }
        return list;
    }
    
    public List<String> getCountries(String language) throws Exception {
        List<String> list = new ArrayList<>();
        String query = "SELECT `CountryName` FROM `language` NATURAL JOIN `country` WHERE `Language` = '"+language+"';";
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
    
    public boolean isOfficial(String country, String language) throws Exception {
        String query =  "SELECT `IsOfficial` " +
                        "FROM `language` NATURAL JOIN `country` " +
                        "WHERE `CountryName` = '"+country+"' AND `Language` = '"+language+"';";
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if(rs.next()){
            String isOfficial = rs.getString("IsOfficial");
            return "T".equalsIgnoreCase(isOfficial);
        } else {
            return false;
        }
    }
}
