package com.turchinsky.db;

import java.sql.*;
import java.util.Properties;

public class TestDB {

    public static void main(String[] args) {
        try{
            String dbURL = "jdbc:postgresql://localhost:5432/sbb";
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user","postgres");
            String db_password = System.getenv("DB_PASSWORD");
            props.setProperty("password",db_password);
            Connection conn = DriverManager.getConnection(dbURL,props);
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from test");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                int capa = resultSet.getInt(3);
                System.out.printf("%-3d %-10s %-3d%n",id,name,capa);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}

