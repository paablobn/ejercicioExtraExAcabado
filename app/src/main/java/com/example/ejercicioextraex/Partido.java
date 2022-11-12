package com.example.ejercicioextraex;

import java.io.Serializable;
import java.nio.file.SecureDirectoryStream;

public class Partido implements Serializable {
    private String nombreEquipo1;
    private String nombreEquipo2;
    private int goles1;
    private int goles2;
    private String resumen;

    public Partido() {
    }

    public Partido(String nombreEquipo1, String nombreEquipo2, int goles1, int goles2, String resumen) {
        this.nombreEquipo1 = nombreEquipo1;
        this.nombreEquipo2 = nombreEquipo2;
        this.goles1 = goles1;
        this.goles2 = goles2;
        this.resumen = resumen;
    }

    public String getNombreEquipo1() {
        return nombreEquipo1;
    }

    public void setNombreEquipo1(String nombreEquipo1) {
        this.nombreEquipo1 = nombreEquipo1;
    }

    public String getNombreEquipo2() {
        return nombreEquipo2;
    }

    public void setNombreEquipo2(String nombreEquipo2) {
        this.nombreEquipo2 = nombreEquipo2;
    }

    public int getGoles1() {
        return goles1;
    }

    public void setGoles1(int goles1) {
        this.goles1 = goles1;
    }

    public int getGoles2() {
        return goles2;
    }

    public void setGoles2(int goles2) {
        this.goles2 = goles2;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}
