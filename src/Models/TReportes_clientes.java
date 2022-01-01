/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Stiven Arboleda
 */
public class TReportes_clientes extends TClientes {

    private int IdRegistro;
    private double DeudaActual;
    private double Deuda;
    private double Mensual;
    private double Cambio;
    private String FechaDeuda;
    private double UltimoPago;
    private String FechaPago;
    private String Ticket;
    private String FechaLimite;
    private int IdCliente;

    public TReportes_clientes() {
    }

    public int getIdRegistro() {
        return IdRegistro;
    }

    public void setIdRegistro(int IdRegistro) {
        this.IdRegistro = IdRegistro;
    }

    public double getDeudaActual() {
        return DeudaActual;
    }

    public void setDeudaActual(double DeudaActual) {
        this.DeudaActual = DeudaActual;
    }

    public double getDeuda() {
        return Deuda;
    }

    public void setDeuda(double Deuda) {
        this.Deuda = Deuda;
    }

    public double getMensual() {
        return Mensual;
    }

    public void setMensual(double Mensual) {
        this.Mensual = Mensual;
    }
    
    @Override
    public double getCambio() {
        return Cambio;
    }
    
    @Override
    public void setCambio(double Cambio) {
        this.Cambio = Cambio;
    }

    public String getFechaDeuda() {
        return FechaDeuda;
    }

    public void setFechaDeuda(String FechaDeuda) {
        this.FechaDeuda = FechaDeuda;
    }

    public double getUltimoPago() {
        return UltimoPago;
    }

    public void setUltimoPago(double UltimoPago) {
        this.UltimoPago = UltimoPago;
    }

    public String getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(String FechaPago) {
        this.FechaPago = FechaPago;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String Ticket) {
        this.Ticket = Ticket;
    }

    public String getFechaLimite() {
        return FechaLimite;
    }

    public void setFechaLimite(String FechaLimite) {
        this.FechaLimite = FechaLimite;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    

}
