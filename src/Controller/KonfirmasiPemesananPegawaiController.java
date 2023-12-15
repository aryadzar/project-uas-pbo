/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import static Controller.HomeController.pass;
import static Controller.HomeController.user;
import DBHelper.DBHelper;
import Entitas.Kamar;
import Entitas.Layanan;
import Entitas.Pegawai;
import Entitas.Pelanggan;
import AlertHelper.AlertHelper;
import Entitas.Reservasi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aryad
 */
public class KonfirmasiPemesananPegawaiController implements Initializable {

    @FXML
    private AnchorPane Home;

    @FXML
    private TableView<Reservasi> Reservasi;
    @FXML
    private Button btnInformasi;
    @FXML
    private Button btnCheck;

    @FXML
    private Button btnConfirmation;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnKonfirmasi;

    @FXML
    private Button btnUserInfo;

    @FXML
    private ChoiceBox<Integer> choiceNoReservasi;

    @FXML
    private ChoiceBox<String> choiceStatusPemesana;

    @FXML
    private TableColumn<Reservasi, Integer> clmNoReservasi;

    @FXML
    private TableColumn<Reservasi, Integer> colJumlahTamu;

    @FXML
    private TableColumn<Reservasi, String> colLayananTambahan;

    @FXML
    private TableColumn<Reservasi, String> colNamaPelanggan;

    @FXML
    private TableColumn<Reservasi, Integer> colNomorKamar;

    @FXML
    private TableColumn<Reservasi, String> colStatusPemesanan;

    @FXML
    private TableColumn<Reservasi, Date> colTanggalCheckIn;

    @FXML
    private TableColumn<Reservasi, Date> colTanggalCheckOut;

    @FXML
    private TableColumn<Reservasi, Date> colTanggalReservasi;
    
    List<Integer> noReservasi = new ArrayList<>();
    String status_reservasi[] = {"Sedang Diproses", "Telah Dikonfirmasi"};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showTable();
         choiceNoReservasi.getItems().addAll(noReservasi);
         choiceStatusPemesana.getItems().addAll(status_reservasi);
    }    
    @FXML
    void gotoConfirm(ActionEvent event) {

    }

    @FXML
    void gotoExit(ActionEvent event) throws IOException {
        if(AlertHelper.showConfirmationAlert("Logout", "Anda yakin mau logout ? ")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Home.fxml"));
            Parent root  =  loader.load();

            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.setScene(new Scene(root));           
        }
    }

    @FXML
    void gotoInformasi(ActionEvent event) {

    }

    @FXML
    void gotoHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Pegawai.fxml"));
        Parent root  =  loader.load();
        
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void gotoRoom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/roomCheck.fxml"));
        Parent root  =  loader.load();
        
        Stage stage = (Stage) btnCheck.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void gotoUserInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/pegawaiInfo.fxml"));
        Parent root  =  loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.setTitle("Biodata Anda");
        popupStage.getIcons().add(new Image("/Image/Logo.png",20, 25, true,true));
        Pegawai pg = showData(user,pass);
        PegawaiInfoController pi = loader.getController();
        pi.showInfo(pg);
        popupStage.show();
    }

    @FXML
    void voidUbahData(ActionEvent event) {
        if(AlertHelper.showConfirmationAlert("Mengubah Data", "Anda yakin mau mengubah data ? ")){
            update();
            showTable();
        }
    }
    
    private void updateKamarStatus(int id_kamar, String new_status){
        Connection conn = DBHelper.getConnection();
        
        String query = "UPDATE kamar SET status_kamar = ? WHERE id_kamar = ? ";
        try (PreparedStatement updateStatement = conn.prepareStatement(query)) {
            updateStatement.setString(1, new_status);
            updateStatement.setInt(2, id_kamar);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void update(){
        String query = "UPDATE `reservasi` SET `status_pemesanan`= ? WHERE `id_reservasi` = ?";
        int id_reservasi = choiceNoReservasi.getValue() ;
        String status_pemesanan = choiceStatusPemesana.getValue();
        try(Connection conn = DBHelper.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)){
            ps.setString(1, status_pemesanan);
            ps.setInt(2, id_reservasi);
            ps.executeUpdate();
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Anda berhasil Mengubah Data");
        }catch(SQLException ex){
            ex.printStackTrace();
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Gagal", "Gagal mengonfirmasi pemesanan");
        }
    }
    private Pegawai showData(String User, String pass){
        Pegawai pg = null;
        String query = "SELECT * FROM `pegawai` WHERE username = ? AND password_pegawai = ?";
        try (Connection connection = DBHelper.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, User);
            preparedStatement.setString(2, pass);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pg = new Pegawai(
                        rs.getString("id_pegawai"),
                        rs.getString("nama_pegawai"),
                        rs.getString("no_ktp"),
                        rs.getString("username"),
                        rs.getString("password_pegawai"),
                        rs.getString("jabatan"),
                        rs.getString("status_pegawai"),
                        rs.getLong("gaji")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pg;
    }
    public ObservableList<Reservasi> getDataReservasi(){
        ObservableList<Reservasi> reservasi = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT reservasi.id_reservasi, pelanggan.nama_pelanggan, kamar.id_kamar, reservasi.tanggal_reservasi ,tanggal_checkin, tanggal_checkout, jumlah_tamu, layanan.deskripsi_layanan , status_pemesanan FROM reservasi, pelanggan, kamar, layanan WHERE pelanggan.id_pelanggan = reservasi.id_pelanggan AND kamar.id_kamar = reservasi.id_kamar AND layanan.id_layanan = reservasi.id_layanan;";
        
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Reservasi temp;
            while(rs.next()){
                temp = new Reservasi(rs.getInt("id_reservasi"), rs.getString("nama_pelanggan"),
                        rs.getInt("id_kamar"),
                                    rs.getDate("tanggal_reservasi"), rs.getDate("tanggal_checkin")
                                    , rs.getDate("tanggal_checkout"), rs.getInt("jumlah_tamu"),rs.getString("deskripsi_layanan")
                                    , rs.getString("status_pemesanan"));
                reservasi.add(temp);
                noReservasi.add(rs.getInt("id_reservasi"));
                
                if(temp.getStatus_pemesanan().equals("Telah Dikonfirmasi")){
                    updateKamarStatus(temp.getId_kamar(), "Sudah dipesan");
                }
                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        
        return reservasi;
    }
    
    private void showTable(){
        ObservableList<Reservasi> list = getDataReservasi();

        clmNoReservasi.setCellValueFactory(new PropertyValueFactory<>("id_reservasi"));
        colJumlahTamu.setCellValueFactory(new PropertyValueFactory<>("jumlah_tamu"));
        colLayananTambahan.setCellValueFactory(new PropertyValueFactory<>("deskripsi_layanan"));
        colNamaPelanggan.setCellValueFactory(new PropertyValueFactory<>("nama_pelanggan"));
        colNomorKamar.setCellValueFactory(new PropertyValueFactory<>("id_kamar"));
        colStatusPemesanan.setCellValueFactory(new PropertyValueFactory<>("status_pemesanan") );
        colTanggalCheckIn.setCellValueFactory(new PropertyValueFactory<>("tanggal_checkin"));
        colTanggalCheckOut.setCellValueFactory(new PropertyValueFactory<>("tanggal_checkout"));
        colTanggalReservasi.setCellValueFactory(new PropertyValueFactory<>("tanggal_reservasi"));
        Reservasi.setItems(list);
    }
}
