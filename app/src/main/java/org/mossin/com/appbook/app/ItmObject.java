package org.mossin.com.appbook.app;



public class ItmObject {

 private String titulo;
 private int icono;

    public ItmObject(String titulo, int icono){
        this.titulo = titulo;
        this.icono = icono;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getTitulo (){
       return titulo;
    }
    public void setIcono(int icono){
       this.icono = icono;
    }
    public int getIcono(){
        return icono;
    }

}
