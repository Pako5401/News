package com.mx.back.news.control;

import com.mx.back.news.model.Conexion;
import com.mx.back.news.model.LoginBean;
import com.mx.back.news.model.AssemblyDao;
import com.mx.back.news.model.CreateBean;
import com.mx.back.news.model.HomeBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/**
 * @author Fco Juárez
 */
@ManagedBean(name = "ctrlNews")
@SessionScoped
public class AssemblyNews implements Serializable {

    Conexion con = null;
    AssemblyDao ad = null;
    LoginBean lb = null;
    CreateBean cb = null;

    private String titulo;
    private String aliasImg;
    private String descComponente;
    private int estatus;
    private String genero;
    private String urlVideo;

    boolean estadoCon = true;
    private String usuario = "";
    private String contrasenia = "";
    private List<HomeBean> laNota = null;
    private List<HomeBean> noticias = null;
    CreateBean nuevaNota = null;
    Part archivo = null;
    public String destination = "C:\\Users\\1079354\\Documents\\NetBeansProjects\\News\\web\\resources\\img\\portfolio\\";

    public AssemblyNews() {
        System.out.println("*********************************************Execute AssemblyNews");
    }

    public String valUsuarios() {
        con = new Conexion();
        ad = new AssemblyDao();
        estadoCon = con.valConexion();
        if (estadoCon == false) {
            System.out.println("MSM------Fallo la conexion a bd, Intenta mas tarde");
            return "admin";
        } else if (estadoCon == true) {
            lb = ad.valUsuarios(usuario, contrasenia);
            if (lb != null) {
                return "home";
            } else {
                System.out.println(" MSM---Usuario incorrecto");
            }
        }
        return "admin";
    }

    public List<HomeBean> getNoticias() {
        System.out.println("Execute getNoticias");
        if (laNota == null) {
            laNota = new ArrayList<HomeBean>();
            ad = new AssemblyDao();
            laNota = ad.muestraNoticias();
        }
        return laNota;
    }

    public String creaNoticia() {
        System.out.println("Execute creaNoticia()");
        boolean six = false;
        String cadena = "";
        ad = new AssemblyDao();
        copyFile();
        aliasImg = archivo.getSubmittedFileName();
        six = ad.creaNoticias(titulo, aliasImg, usuario, descComponente, estatus, genero, urlVideo);
        if (six == true) {
            getNoticias();
            cadena = "home";
            return cadena;
        } else {
            System.out.println("MSM-------- Vuelve a crear la nota");
        }
        return cadena;
    }

    public void copyFile() {
        try {
            System.out.println("Execute copyFile()");
            InputStream in = null;
            in = archivo.getInputStream();
            OutputStream out = new FileOutputStream(new File(destination + archivo.getSubmittedFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("MSM----No se copio el archivo¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

//    Setters y getters de createNews
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAliasImg() {
        return aliasImg;
    }

    public void setAliasImg(String aliasImg) {
        this.aliasImg = aliasImg;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getDescComponente() {
        return descComponente;
    }

    public void setDescComponente(String descComponente) {
        this.descComponente = descComponente;
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }

}
