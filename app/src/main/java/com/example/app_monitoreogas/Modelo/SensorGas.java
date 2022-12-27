package com.example.app_monitoreogas.Modelo;

import java.io.Serializable;

public class SensorGas implements Serializable {

    private String idGasDetectado;
    private String gasDetectado;
    private String dia;
    private String hora;
    private String duracion;

    public SensorGas() {
    }

    public SensorGas(String idGasDetectado, String gasDetectado, String dia, String hora, String duracion) {
        this.idGasDetectado = idGasDetectado;
        this.gasDetectado = gasDetectado;
        this.dia = dia;
        this.hora = hora;
        this.duracion = duracion;
    }

    public String getIdGasDetectado() {
        return idGasDetectado;
    }

    public void setIdGasDetectado(String idGasDetectado) {
        this.idGasDetectado = idGasDetectado;
    }

    public String getGasDetectado() {
        return gasDetectado;
    }

    public void setGasDetectado(String gasDetectado) {
        this.gasDetectado = gasDetectado;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

}