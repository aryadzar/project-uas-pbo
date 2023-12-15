/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitas;

/**
 *
 * @author aryad
 */
public class Layanan {
    private int id_layanan;
    private String deskripsi_layanan;
    private long harga;
    private String kontak_layanan;
    private String status_layanan;
    private String ulasan;

    public Layanan(int id_layanan, String deskripsi_layanan, long harga, String kontak_layanan, String status_layanan, String ulasan) {
        this.id_layanan = id_layanan;
        this.deskripsi_layanan = deskripsi_layanan;
        this.harga = harga;
        this.kontak_layanan = kontak_layanan;
        this.status_layanan = status_layanan;
        this.ulasan = ulasan;
    }

    public Layanan(String deskripsi_layanan) {
        this.deskripsi_layanan = deskripsi_layanan;
    }

    public int getId_layanan() {
        return id_layanan;
    }

    public void setId_layanan(int id_layanan) {
        this.id_layanan = id_layanan;
    }

    public String getDeskripsi_layanan() {
        return deskripsi_layanan;
    }

    public void setDeskripsi_layanan(String deskripsi_layanan) {
        this.deskripsi_layanan = deskripsi_layanan;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    public String getKontak_layanan() {
        return kontak_layanan;
    }

    public void setKontak_layanan(String kontak_layanan) {
        this.kontak_layanan = kontak_layanan;
    }

    public String getStatus_layanan() {
        return status_layanan;
    }

    public void setStatus_layanan(String status_layanan) {
        this.status_layanan = status_layanan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
    

}
