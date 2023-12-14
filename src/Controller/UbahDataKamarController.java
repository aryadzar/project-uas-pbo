package Controller;

import AlertHelper.AlertHelper;
import DBHelper.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UbahDataKamarController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnKembali;
    
    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<String> choiceJenis;

    @FXML
    private TextField lblHarga;

    @FXML
    private TextField lblNoKamar;

    @FXML
    private ChoiceBox<String> choiceStatus;

    @FXML
    private Button btnInsert;
    
    private String[] Jenis = {"Single Bed", "Double Bed", "King Size", "Super King Size", "Extra Bed", "Baby Cot"};
    
    private String[] Status = {"Tersedia", "Sudah dipesan"}; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        choiceJenis.getItems().addAll(Jenis);
        choiceStatus.getItems().addAll(Status);
    }
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @FXML
    void buttonAction(ActionEvent event) {
        
      if(isInteger(lblNoKamar.getText()) && isInteger(lblHarga.getText())){
        if(event.getSource() == btnInsert){
            if(AlertHelper.showConfirmationAlert("Mengubah Data", "Anda yakin ingin menambah data")){
                insert();
            }
          }else if(event.getSource() == btnUpdate){
            if(AlertHelper.showConfirmationAlert("Mengubah Data", "Anda yakin ingin memperbarui data")){
                update();
            }
              
           }
        }else{
          AlertHelper.showAlert(Alert.AlertType.ERROR, "Kesalahan Pengisian Data", "Silahkan Masukan data dengan tipe angka !");
      }
        
      if(isInteger(lblNoKamar.getText())){
          if(event.getSource() == btnDelete){
             if(AlertHelper.showConfirmationAlert("Mengubah Data", "Anda yakin ingin Menghapus data")){
                 delete();
             }
         }         
      }

    }
    @FXML
    void gotoPegawaiHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/roomCheck.fxml"));
        Parent root  =  loader.load();
        
        Stage stage = (Stage) btnKembali.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    
    private void insert(){
        String query = "INSERT INTO kamar  VALUES (?,?,?,?)";
        int no_kamar = Integer.parseInt(lblNoKamar.getText());
        String jenis_kamar = choiceJenis.getValue();
        long harga = Long.parseLong(lblHarga.getText());
        String status = choiceStatus.getValue();
        try(Connection conn = DBHelper.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)){
            ps.setInt(1, no_kamar);
            ps.setString(2, jenis_kamar);
            ps.setLong(3, harga);
            ps.setString(4, status);
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        lblNoKamar.clear();
        lblHarga.clear();
    }
    
    private void update(){
        String query = "UPDATE `kamar` SET `jenis_kamar`=?,`harga`=?,`status_kamar`=? WHERE id_kamar = ? ";
        int no_kamar = Integer.parseInt(lblNoKamar.getText());
        String jenis_kamar = choiceJenis.getValue();
        long harga = Long.parseLong(lblHarga.getText());
        String status = choiceStatus.getValue();
        try(Connection conn = DBHelper.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)){
            ps.setString(1, jenis_kamar);
            ps.setLong(2, harga);
            ps.setString(3, status);
            ps.setLong(4, no_kamar);
            
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        lblNoKamar.clear();
        lblHarga.clear();
        
    }
    
    private void delete(){
        String query = "DELETE FROM `kamar` WHERE id_kamar = ?";
        int no_kamar = Integer.parseInt(lblNoKamar.getText());
        try(Connection conn = DBHelper.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)){
            ps.setInt(1, no_kamar);
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        lblNoKamar.clear();
        lblHarga.clear();
    }
    
}
