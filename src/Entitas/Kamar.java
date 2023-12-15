/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitas;

/**
 *
 * @author aryad
 */
public class Kamar {
    private int id_kamar;
    private long harga;
    private String jenis_kamar, status_kamar;

    public Kamar(int id_kamar, long harga, String jenis_kamar, String status_kamar) {
        this.id_kamar = id_kamar;
        this.harga = harga;
        this.jenis_kamar = jenis_kamar;
        this.status_kamar = status_kamar;
    }
    
    public Kamar(int id_kamar){
        this.id_kamar = id_kamar;
    }
    public int getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(int id_kamar) {
        this.id_kamar = id_kamar;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    public String getJenis_kamar() {
        return jenis_kamar;
    }

    public void setJenis_kamar(String jenis_kamar) {
        this.jenis_kamar = jenis_kamar;
    }

    public String getStatus_kamar() {
        return status_kamar;
    }

    public void setStatus_kamar(String status_kamar) {
        this.status_kamar = status_kamar;
    }
    
    
}
