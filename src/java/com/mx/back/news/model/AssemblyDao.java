package com.mx.back.news.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Fco Juárez
 */
public class AssemblyDao {
    LoginBean loginBean = null;
    Conexion con = null;
    ResultSet rs = null;
    HomeBean noticias = null;
    ArrayList<HomeBean> homeArray = null;
    Statement sentencia;

    public AssemblyDao() {
        System.out.println("*********************************************Execute AssemblyDao");
    }

    public LoginBean valUsuarios(String user, String pass) {
        try {
            con = new Conexion();
            con.ps = con.conexion.prepareStatement("SELECT nombre, apellidos, tipo FROM usuarios WHERE usuario ='" + user + "' AND contrasenia= '" + pass + "' AND estatus=1;");
            rs = con.ps.executeQuery();
            while (rs.next()) {
                loginBean = new LoginBean();
                loginBean.setNombre(rs.getString("nombre"));
                loginBean.setApellido(rs.getString("apellidos"));
                loginBean.setTipo(rs.getString("tipo"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//                con.ps.close();
//                rs.close();
        }
        return loginBean;
    }

    public ArrayList muestraNoticias() {
        System.out.println("Execute muestraNoticias()");
        try {
            homeArray = new ArrayList<HomeBean>();
            con = new Conexion();
            con.ps = con.conexion.prepareStatement("SELECT id_componente, titulo, estatus, alias_img, desc_componente, genero, url_video,fecha FROM componentes WHERE estatus=1");
            rs = con.ps.executeQuery();
            while (rs.next()) {
                noticias = new HomeBean();
                noticias.setIdComponente(rs.getInt("id_componente"));
                noticias.setEstatus(rs.getInt("estatus"));
                noticias.setTitulo(rs.getString("titulo"));
                noticias.setAliasImg(rs.getString("alias_img"));
                noticias.setDescComponente(rs.getString("desc_componente"));
                noticias.setGenero(rs.getString("genero"));
                noticias.setUrlVideo(rs.getString("url_video"));
                noticias.setFecha(rs.getDate("fecha"));
                homeArray.add(noticias);
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        return homeArray;
    }

    public boolean creaNoticias(String titulo, String aliasImg, String usuario, String descComponente, int estatus, String genero, String urlVideo) {
        System.out.println("Execute creaNoticias");
        boolean flag = false;
        try {
            con = new Conexion();
            java.util.Date fecha = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            con.ps = con.conexion.prepareStatement("INSERT INTO componentes VALUES(null,'" + titulo + "', '" + usuario + "', '" + aliasImg + "', '" + descComponente + "', " + estatus + ", '" + genero + "', '" + urlVideo + "', '" + sdf.format(fecha) + "')");
            con.ps.executeUpdate();
            flag = true;
        } catch (SQLException e2) {
            flag = false;
            e2.printStackTrace();
        }
        return flag;
    }
}
