/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitas;

import java.sql.Date;

/**
 *
 * @author aryad
 */
public class Reservasi {
    private int id_reservasi;
    private String nama_pelanggan;
    private int id_kamar;
    private Date tanggal_reservasi;
    private Date tanggal_checkin;
    private Date tanggal_checkout;
    private int jumlah_tamu;
    private String deskripsi_layanan;
    private String status_pemesanan;

    public Reservasi(int id_reservasi, String nama_pelanggan, int id_kamar, Date tanggal_reservasi, Date tanggal_checkin, Date tanggal_checkout, int jumlah_tamu, String deskripsi_layanan, String status_pemesanan) {
        this.id_reservasi = id_reservasi;
        this.nama_pelanggan = nama_pelanggan;
        this.id_kamar = id_kamar;
        this.tanggal_reservasi = tanggal_reservasi;
        this.tanggal_checkin = tanggal_checkin;
        this.tanggal_checkout = tanggal_checkout;
        this.jumlah_tamu = jumlah_tamu;
        this.deskripsi_layanan = deskripsi_layanan;
        this.status_pemesanan = status_pemesanan;
    }

    public int getId_reservasi() {
        return id_reservasi;
    }

    public void setId_reservasi(int id_reservasi) {
        this.id_reservasi = id_reservasi;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public int getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(int id_kamar) {
        this.id_kamar = id_kamar;
    }

    public Date getTanggal_reservasi() {
        return tanggal_reservasi;
    }

    public void setTanggal_reservasi(Date tanggal_reservasi) {
        this.tanggal_reservasi = tanggal_reservasi;
    }

    public Date getTanggal_checkin() {
        return tanggal_checkin;
    }

    public void setTanggal_checkin(Date tanggal_checkin) {
        this.tanggal_checkin = tanggal_checkin;
    }

    public Date getTanggal_checkout() {
        return tanggal_checkout;
    }

    public void setTanggal_checkout(Date tanggal_checkout) {
        this.tanggal_checkout = tanggal_checkout;
    }

    public int getJumlah_tamu() {
        return jumlah_tamu;
    }

    public void setJumlah_tamu(int jumlah_tamu) {
        this.jumlah_tamu = jumlah_tamu;
    }

    public String getDeskripsi_layanan() {
        return deskripsi_layanan;
    }

    public void setDeskripsi_layanan(String deskripsi_layanan) {
        this.deskripsi_layanan = deskripsi_layanan;
    }

    public String getStatus_pemesanan() {
        return status_pemesanan;
    }

    public void setStatus_pemesanan(String status_pemesanan) {
        this.status_pemesanan = status_pemesanan;
    }



}
