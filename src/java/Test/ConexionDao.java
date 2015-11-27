package corejsf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "dataTable")
@SessionScoped
public class ConexionDao {

    Connection connection = null;
    ResultSet rs = null;
    DatosBean unEmpleado = null;
    String query;
    Boolean resultado=true;

    public ArrayList validarLogin() {
        System.out.println("Execute validarLogin");
        ArrayList<DatosBean> datosArray = new ArrayList<DatosBean>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/tcs_exa", "root", "root");
            query = "select IdEmpleado,NombreCompleto,Edad from empleados limit 0,10";
            rs = connection.prepareStatement(query).executeQuery();
            while (rs.next()) {
                unEmpleado = new DatosBean();
                unEmpleado.setIdEmpleado(rs.getString("idEmpleado"));
                unEmpleado.setNombreCompleto(rs.getString("NombreCompleto"));
                unEmpleado.setEdad(rs.getInt("Edad"));
                datosArray.add(unEmpleado);
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
            System.out.println("No se conecto =(");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        return datosArray;
    }

    public void eliminar(DatosBean usuario) {
        Connection connection = null;
        ResultSet rs = null;
        DatosBean unEmpleado = null;
        String query;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/tcs_exa", "root", "root");
            System.out.println("Se conecto");
            Statement sentencia = connection.createStatement();
            sentencia.executeUpdate("delete from empleados where idEmpleado='" + usuario.getIdEmpleado() + "' ;");
        } catch (SQLException e2) {
            e2.printStackTrace();
            System.out.println("No se conecto =(");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public boolean insertar(String ide, String nom, String eda) {
        boolean ban=true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/tcs_exa", "root", "root");
                query = "insert into empleados (IdEmpleado,NombreCompleto,Edad) VALUES ('" + ide + "','" + nom + "'," + eda + ");";
            resultado = connection.prepareStatement(query).execute();
            return ban;
        } catch (SQLException e2) {
            e2.printStackTrace();
            return ban=false;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        return ban;
    }

    public void modificar(String idem, String nomb, String edad) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/tcs_exa", "root", "root");
            System.out.println("Se conecto");
            Statement sentencia = connection.createStatement();
            sentencia.executeUpdate("UPDATE empleados SET NombreCompleto='" + nomb + "', Edad='" + edad + "' WHERE IdEmpleado='" + idem + "';");
        
        } catch (SQLException e2) {
            e2.printStackTrace();
            System.out.println("No se conecto =(");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

}
