/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Stiven Arboleda
 */
public class TClientes{
    
    //atributos con el MISMO NOMBRE de la tabla TClientes en el SQLServer
    private int ID;
    private String NumeroIdentidad;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String Email;
    private String Telefono;
    private String Fecha;
    private boolean Credito;
    private byte[] Imagen;
    

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] Imagen) {
        this.Imagen = Imagen;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public boolean isCredito() {
        return Credito;
    }

    public void setCredito(boolean Credito) {
        this.Credito = Credito;
    }
    
    public TClientes(){
        
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNumeroIdentidad() {
        return NumeroIdentidad;
    }

    public void setNumeroIdentidad(String NumeroIdentidad) {
        this.NumeroIdentidad = NumeroIdentidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

}
