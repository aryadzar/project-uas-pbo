/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitas;

/**
 *
 * @author aryad
 */
public class Pelanggan {
    private long id_pelanggan;
    private String username,password_pelanggan, nama_pelanggan,alamat, no_telepon, no_ktp;

    public Pelanggan(String no_ktp, String no_telepon, String username, String password_pelanggan, String nama_pelanggan, String alamat) {
        this.no_ktp = no_ktp;
        this.no_telepon = no_telepon;
        this.username = username;
        this.password_pelanggan = password_pelanggan;
        this.nama_pelanggan = nama_pelanggan;
        this.alamat = alamat;
    }


    public Pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }
    
    public long getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(long id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_pelanggan() {
        return password_pelanggan;
    }

    public void setPassword_pelanggan(String password_pelanggan) {
        this.password_pelanggan = password_pelanggan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
    
    
}
