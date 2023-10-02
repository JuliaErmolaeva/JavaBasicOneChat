package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Server server = new Server(8080, new DatabaseAuthenticationProvider());
        server.start();
    }
}