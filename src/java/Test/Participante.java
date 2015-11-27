package test;

import corejsf.model.ConexionDao;
import corejsf.model.DatosBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * @author Fco Juárez
 */
@ManagedBean(name = "participante")
@SessionScoped
public class Participante implements Serializable {

    private String usuario = "";
    private String contraseña;
    private String ide;
    private String eda;
    private String nom;

    public String validar() {
        if (usuario.equals("admin") && contraseña.equals("12345")) {
            return "correcto";
        } else {
            return "error";
        }
    }

    public ArrayList<DatosBean> getPersonas() {
        ArrayList<DatosBean> personas = new ArrayList<DatosBean>();
        ConexionDao obj = new ConexionDao();
        personas = obj.validarLogin();

        return personas;
    }

    public String eliminar(DatosBean usuario) {

        ConexionDao con = new ConexionDao();
        con.eliminar(usuario);
        return "";
    }

    public String insertar() {
        
        ConexionDao con = new ConexionDao();
        Boolean band=con.insertar(ide, nom, eda);
        System.out.println(".............................."+band);
        if(band==false){
            con.modificar(ide, nom, eda);
        }
        return null;
    }

    public void validarUsuario(FacesContext fc, UIComponent c, Object value) {
        if (((String) value).contains("_")) {
            throw new ValidatorException(
                    new FacesMessage("EL usuario no puede contener el caracter (_) ")
            );
        }
    }

    public String modificar() {
        ConexionDao obj = new ConexionDao();
        System.out.println("*************************" + ide);
        obj.modificar(ide, nom, eda);
        return null;
    }

    public String inglesAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.GERMANY);
        return null;
    }

    public String españolAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.ENGLISH);
        return null;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public String getEda() {
        return eda;
    }

    public void setEda(String eda) {
        this.eda = eda;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
