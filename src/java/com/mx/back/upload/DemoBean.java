/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.back.upload;
import java.io.FileOutputStream;
    import java.io.IOException;  
import java.io.InputStream;
    import javax.faces.bean.ManagedBean;  
    import javax.faces.bean.SessionScoped;  
    import javax.servlet.http.Part;  
      
      
    /** 
     * 
     * @author ramki 
     */  
    @ManagedBean  
    @SessionScoped  
    public class DemoBean {  
      
        private Part file1;  
        private Part file2;  
      
        // getters and setters for file1 and file2  
      
        public String upload() throws IOException {  
             InputStream inputStream = file1.getInputStream();          
            FileOutputStream outputStream = new FileOutputStream(getFilename(file1));  
              
            byte[] buffer = new byte[4096];          
            int bytesRead = 0;  
            while(true) {                          
                bytesRead = inputStream.read(buffer);  
                if(bytesRead > 0) {  
                    outputStream.write(buffer, 0, bytesRead);  
                }else {  
                    break;  
                }                         
            }  
            outputStream.close();  
            inputStream.close();  
             
            return "success";  
        }  
      
        private static String getFilename(Part part) {  
            for (String cd : part.getHeader("content-disposition").split(";")) {  
                if (cd.trim().startsWith("filename")) {  
                    String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");  
                    return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
                }  
            }  
            return null;  
        }  
    }  