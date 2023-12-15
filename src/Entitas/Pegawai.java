/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitas;

/**
 *
 * @author aryad
 */
public class Pegawai {
    private String id_pegawai, no_ktp, username, password_pegawai, jabatan, status_pegawai, nama_pegawai;
    private long gaji;



    public Pegawai(String id_pegawai,String nama_pegawai, String no_ktp, String username, String password_pegawai, String jabatan, String status_pegawai, long gaji) {
        this.id_pegawai = id_pegawai;
        this.nama_pegawai = nama_pegawai;
        this.no_ktp = no_ktp;
        this.username = username;
        this.password_pegawai = password_pegawai;
        this.jabatan = jabatan;
        this.status_pegawai = status_pegawai;
        this.gaji = gaji;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_pegawai() {
        return password_pegawai;
    }

    public void setPassword_pegawai(String password_pegawai) {
        this.password_pegawai = password_pegawai;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getStatus_pegawai() {
        return status_pegawai;
    }

    public void setStatus_pegawai(String status_pegawai) {
        this.status_pegawai = status_pegawai;
    }

    public long getGaji() {
        return gaji;
    }

    public void setGaji(long gaji) {
        this.gaji = gaji;
    }
    
    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }
}
