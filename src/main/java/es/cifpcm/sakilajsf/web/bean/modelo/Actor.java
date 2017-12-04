package es.cifpcm.sakilajsf.web.bean.modelo;

import java.util.Date;


public class Actor {
    private int id;
    private String nombre;
    private String apellidos;
    private Date fecha;

    public Actor() {
    }
    public Actor(int id, String nombre, String apellidos, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha = fecha;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
