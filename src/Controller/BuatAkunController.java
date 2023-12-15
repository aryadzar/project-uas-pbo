package Controller;

import AlertHelper.AlertHelper;
import DBHelper.DBHelper;
import Entitas.Pelanggan;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuatAkunController {

    @FXML
    private Button btnBuatAkun;
    
    @FXML
    private Button btnKembali;
    
    @FXML
    private TextField fieldAlamat;

    @FXML
    private PasswordField fieldConfirmPassword;

    @FXML
    private TextField fieldKtp;

    @FXML
    private TextField fieldNama;

    @FXML
    private TextField fieldNoTelepon;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldUsername;

    @FXML
    void gotoHalamanPengguna(ActionEvent event) throws IOException {
        if(!fieldUsername.getText().isEmpty() && !fieldPassword.getText().isEmpty() && !fieldNoTelepon.getText().isEmpty() && !fieldNama.getText().isEmpty() && !fieldKtp.getText().isEmpty()
               && !fieldConfirmPassword.getText().isEmpty() && !fieldAlamat.getText().isEmpty() ){
            
            if(fieldConfirmPassword.getText().equals(fieldPassword.getText())){
                if(isLong(fieldKtp.getText()) && isLong(fieldNoTelepon.getText()) ){
                    if(isString(fieldNama.getText())){
                        if(AlertHelper.showConfirmationAlert("Konfirmasi", "Anda yakin ingin membuat akun")){
                            signup(new Pelanggan(fieldKtp.getText(), fieldNoTelepon.getText(), fieldUsername.getText(), fieldPassword.getText(), fieldNama.getText(), fieldAlamat.getText()));
                            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Dialihkan", "Anda akan dialihkan ke halaman login");
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Home.fxml"));
                            Parent root  =  loader.load();

                            Stage stage = (Stage) btnBuatAkun.getScene().getWindow();
                            stage.setScene(new Scene(root));
                        }
                    }else{
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "Kesalahan Format", "Masukan format Nama dengan huruf");

                    }
                }else{
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Kesalahan Format", "Masukan format KTP dan Nomor Telpon bilangan");
                }
            }else{
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Kesalahan Confirm Password", "Silahkan cek confirm password lebih teliti");
            }
        
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Kesalahan Pengisian", "Tidak boleh ada yang kosong");
        }


    }
    @FXML
    void gotoHome(ActionEvent event) throws IOException {
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Dialihkan", "Anda akan dialihkan ke halaman login");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Home.fxml"));
        Parent root  =  loader.load();

        Stage stage = (Stage) btnBuatAkun.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    public boolean isUsernameAvailable(String username) {
        // Lakukan kueri ke database untuk memeriksa apakah nama pengguna sudah ada
        String query = "SELECT COUNT(*) FROM pelanggan WHERE username = ?";
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0; // Jika count == 0, berarti nama pengguna tersedia
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Jika terjadi kesalahan, mengembalikan false sebagai tindakan pencegahan
    }

    public void signup(Pelanggan pg) {
        if (isUsernameAvailable(pg.getUsername())) {
            // Simpan data pelanggan ke database karena nama pengguna tersedia
            String insertQuery = "INSERT INTO `pelanggan`(`no_ktp`, `username`, `password_pelanggan`, `nama_pelanggan`, `no_telpon`, `alamat`) "
                    + "VALUES (?,?,?,?,?,?)";
            try (Connection connection = DBHelper.getConnection();
                PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                ps.setString(1,pg.getNo_ktp());
                ps.setString(2, pg.getUsername());
                ps.setString(3, pg.getPassword_pelanggan());
                ps.setString(4, pg.getNama_pelanggan());
                ps.setString(5, pg.getNo_telepon());
                ps.setString(6, pg.getAlamat());
                ps.executeUpdate();
                // Tambahkan logika atau tindakan lain yang diperlukan setelah pendaftaran berhasil
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle kesalahan penyimpanan data ke database
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Gagal", "Gagal Membuat Akun");
            }
            
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Anda telah berhasil mem");
        } else {
            // Tampilkan pesan kesalahan bahwa nama pengguna sudah ada
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Username already exists. Please choose another username.");
        }
    }
    private boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isString(String str) {
        return str.matches("^[a-zA-Z ]*$");
    }

    
}
