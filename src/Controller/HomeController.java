package Controller;

import DBHelper.DBHelper;

import AlertHelper.AlertHelper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    private Hyperlink btnBuatAkun;
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;
    
    
    public static String user;
    public static String pass;
    


    @FXML
    public void gantiScene(ActionEvent event) throws IOException {
        user = tfUsername.getText();
        pass = pfPassword.getText();
        
        if(validateLogin(user,pass,"pegawai")){
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Login berhasil", "Login Pegawai Telah Berhasil");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Pegawai.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
        }else if(validateLogin(user,pass,"pelanggan")){
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Login Berhasil", "Login Username : " + user + " berhasil");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Pelanggan.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Login Gagal", "Silahkan check username dan password lebih teliti");
            pfPassword.clear();
        }
            
    }
    
    @FXML
    void keBuatAkun(ActionEvent event) throws IOException {
        AlertHelper.showAlert(Alert.AlertType.WARNING, "Konfirmasi", " Anda akan dialihkan ke arah pembuatan akun");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/BuatAkun.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnBuatAkun.getScene().getWindow();
        stage.setScene(new Scene(root));   
    }
    
    public static boolean validateLogin(String username, String password, String entitas){
        String query;
        if(entitas.equals("pelanggan")){
            query = "SELECT * FROM `pelanggan` WHERE username = ?  AND password_pelanggan = ? ";
        }else{
            query = "SELECT * FROM `pegawai` WHERE username = ?  AND password_pegawai = ? ";
        }
        
            try (Connection connection = DBHelper.getConnection()) {
              try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                  preparedStatement.setString(1, username);
                  preparedStatement.setString(2, password);
                  try (ResultSet resultSet = preparedStatement.executeQuery()) {
                      return resultSet.next(); 
                  }
              }
          } catch (SQLException e) {
              e.printStackTrace();
              return false;
          } 
        
           
    }
}




