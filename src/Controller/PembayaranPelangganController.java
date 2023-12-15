/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import AlertHelper.AlertHelper;
import DBHelper.DBHelper;
import Entitas.Pelanggan;
import Entitas.Pembayaran;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aryad
 */
public class PembayaranPelangganController implements Initializable {
    @FXML
    private Button btnBayar;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnFasilitas;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPembayaran;

    @FXML
    private ChoiceBox<Long> choicePembayaran;


    @FXML
    private TableColumn<Pembayaran, String> colMetode;



    @FXML
    private TableColumn<Pembayaran, Integer> colNoPembayaran;






    @FXML
    private TableView<Pembayaran> tblPembayaran;


    @FXML
    private Button userInfo;
    
    ArrayList<Long> no_pembayaran = new ArrayList<>();
    ArrayList<Integer> no_reservasi = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPembayaran();
//        showDataReservasi();
        choicePembayaran.getItems().addAll(no_pembayaran);
    }
    
    @FXML
    void gotoBayar(ActionEvent event) {
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Anda telah membayar", "Struk pembayaran telah dikirim ke nomor telepon Anda, Terimakasih!");
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
    void gotoFasilitas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Fasilitas Pelanggan.fxml"));
        Parent root  =  loader.load();

        Stage stage = (Stage) btnFasilitas.getScene().getWindow();
        stage.setScene(new Scene(root)); 
    }

    @FXML
    void gotoHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Pelanggan.fxml"));
        Parent root  =  loader.load();

        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(new Scene(root));  
    }

    @FXML
    void gotoPembayaran(ActionEvent event) {

    }

    @FXML
    void gotoUserInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Customer Info.fxml"));
        Parent root  =  loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.setTitle("Biodata Anda");
        popupStage.getIcons().add(new Image("/Image/Logo.png",20, 25, true,true));
        Pelanggan pg = getPelanggan(HomeController.user,HomeController.pass);
        CustomerInfoController pi = loader.getController();
        pi.showBiodata(pg);
        popupStage.show();
    }
    
    private ObservableList<Pembayaran> getDataPembayaran(){
        ObservableList<Pembayaran> pembayaran = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `pembayaran`";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Pembayaran temp;
            while(rs.next()){
                temp = new Pembayaran(rs.getLong("id_pembayaran"), rs.getString("metode_pembayaran"));
                pembayaran.add(temp);
                no_pembayaran.add(temp.getId_pembayaran());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return pembayaran;
    }
    
    private void showPembayaran(){
        ObservableList<Pembayaran> list_pembayaran = getDataPembayaran();
        
        colNoPembayaran.setCellValueFactory(new PropertyValueFactory<>("id_pembayaran"));
        colMetode.setCellValueFactory(new PropertyValueFactory<>("metode_pembayaran"));
        tblPembayaran.setItems(list_pembayaran);
        
    }
    

    
    private Pelanggan getPelanggan(String User, String Pass){
        Pelanggan pelanggan = null;
        String query = "SELECT * FROM `pelanggan` WHERE username = ? AND password_pelanggan = ?";
        try (Connection connection = DBHelper.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, User);
            preparedStatement.setString(2, Pass);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pelanggan = new Pelanggan(rs.getString("no_ktp"),rs.getString("no_telpon") 
                            ,rs.getString("username") , rs.getString("password_pelanggan") ,
                            rs.getString("nama_pelanggan"), rs.getString("alamat") );
                    
                    pelanggan.setId_pelanggan(rs.getLong("id_pelanggan"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelanggan;
    }
    

    
}
