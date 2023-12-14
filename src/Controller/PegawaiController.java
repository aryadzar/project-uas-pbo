package Controller;

import AlertHelper.AlertHelper;
import DBHelper.DBHelper;
import Entitas.Pegawai;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controller.HomeController.user;
import static Controller.HomeController.pass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class PegawaiController implements Initializable {

    @FXML
    private AnchorPane Home;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnConfirmation;

    @FXML
    private Button btnExit;
    @FXML
    private Button btnInformasi;


    @FXML
    private Button btnHome;
    @FXML
    private ImageView fotoPegawai;
    @FXML
    private Button btnUserInfo;

    
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
    @FXML
    void gotoConfirm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Konfirmasi pemesanan pegawai.fxml"));
        Parent root  =  loader.load();

        Stage stage = (Stage) btnConfirmation.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void gotoExit(ActionEvent event) throws IOException {
//        AlertHelper.showAlert(Alert.AlertType.WARNING, "Keluar Akun", "Anda Akan Keluar Akun");
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
    void gotoHome(ActionEvent event) {

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
}
