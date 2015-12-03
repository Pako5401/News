package com.mx.back.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.SessionScoped;

/**
 * @author Fco Juárez
 */
@SessionScoped
public class Conexion {

    public static Connection conexion;
    public static PreparedStatement ps;

    public Conexion() {
        System.out.println("************************************** Execute Conexion");
        valConexion();
    }

    public static boolean valConexion() {
        System.out.println("Execute conexion");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/news", "root", "rock");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

}
