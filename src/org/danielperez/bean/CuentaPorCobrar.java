package org.danielperez.bean;

import java.time.Year;


public class CuentaPorCobrar {
    
    private int codigoCuentasPorCobrar;
    private String numeroFactura;
    private String anio;
    private int mes;
    private double valorNetoPago;
    private String estadoPago;
    private int codigoAdministracion;
    private int codigoCliente;
    private int codigoLocal;

    public CuentaPorCobrar() {
    }

    public CuentaPorCobrar(int codigoCuentasPorCobrar, String numeroFactura, String anio, int mes, double valorNetoPago, String estadoPago, int codigoAdministracion, int codigoCliente, int codigoLocal) {
        this.codigoCuentasPorCobrar = codigoCuentasPorCobrar;
        this.numeroFactura = numeroFactura;
        this.anio = anio;
        this.mes = mes;
        this.valorNetoPago = valorNetoPago;
        this.estadoPago = estadoPago;
        this.codigoAdministracion = codigoAdministracion;
        this.codigoCliente = codigoCliente;
        this.codigoLocal = codigoLocal;
    }

    public int getCodigoCuentasPorCobrar() {
        return codigoCuentasPorCobrar;
    }

    public void setCodigoCuentasPorCobrar(int codigoCuentasPorCobrar) {
        this.codigoCuentasPorCobrar = codigoCuentasPorCobrar;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getValorNetoPago() {
        return valorNetoPago;
    }

    public void setValorNetoPago(double valorNetoPago) {
        this.valorNetoPago = valorNetoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public int getCodigoAdministracion() {
        return codigoAdministracion;
    }

    public void setCodigoAdministracion(int codigoAdministracion) {
        this.codigoAdministracion = codigoAdministracion;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(int codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    

    
    
    
}
