package com.mx.back.news.model;

import java.util.Date;

/**
 * @author Fco Juárez
 */
public class CreateBean {

    private String titulo;
    private String usuario;
    private String aliasImg;
    private String descComponente;
    private int estatus;
    private String genero;
    private String urlVideo;
    private Date fecha;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAliasImg() {
        return aliasImg;
    }

    public void setAliasImg(String aliasImg) {
        this.aliasImg = aliasImg;
    }

    public String getDescComponente() {
        return descComponente;
    }

    public void setDescComponente(String descComponente) {
        this.descComponente = descComponente;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
