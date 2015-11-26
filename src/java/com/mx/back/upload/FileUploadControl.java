/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.back.upload;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class FileUploadControl {
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful",   event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}