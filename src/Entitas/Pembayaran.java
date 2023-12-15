/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitas;

/**
 *
 * @author aryad
 */
public class Pembayaran {
    private long id_pembayaran;
    private String metode_pembayaran;

    public Pembayaran(long id_pembayaran, String metode_pembayaran) {
        this.id_pembayaran = id_pembayaran;
        this.metode_pembayaran = metode_pembayaran;
    }

    public long getId_pembayaran() {
        return id_pembayaran;
    }

    public void setId_pembayaran(long id_pembayaran) {
        this.id_pembayaran = id_pembayaran;
    }

    public String getMetode_pembayaran() {
        return metode_pembayaran;
    }

    public void setMetode_pembayaran(String metode_pembayaran) {
        this.metode_pembayaran = metode_pembayaran;
    }
    
}
