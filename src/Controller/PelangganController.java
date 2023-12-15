package Controller;

import AlertHelper.AlertHelper;
import Entitas.Pelanggan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import DBHelper.DBHelper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class PelangganController {

    @FXML
    private Button btnExit;

    @FXML
    private Button btnFasilitas;
    
    @FXML
    private Button userInfo;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnPembayaran;
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
    void gotoHome(ActionEvent event) {

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
    @FXML
    void gotoPembayaran(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Pembayaran Pelanggan.fxml"));
        Parent root  =  loader.load();

        Stage stage = (Stage) btnPembayaran.getScene().getWindow();
        stage.setScene(new Scene(root));
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