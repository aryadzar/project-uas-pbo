package Controller;

import AlertHelper.AlertHelper;
import static Controller.HomeController.pass;
import static Controller.HomeController.user;
import DBHelper.DBHelper;
import Entitas.Pegawai;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Entitas.Kamar;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

public class RoomCheckController implements Initializable{

    @FXML
    private AnchorPane Home;
    
    @FXML
    private Button btnInformasi;
    
    @FXML
    private Button btnUbahDataKamar;

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
    private Button btnUserInfo;

    @FXML
    private TableColumn<Kamar, Long> colHarga;

    @FXML
    private TableColumn<Kamar, String> colJenisKamar;

    @FXML
    private TableColumn<Kamar, Integer> colNoKamar;

    @FXML
    private TableColumn<Kamar, String> colStatusKamar;

    @FXML
    private TableView<Kamar> tblHotel;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        showKamar();
    }
    
    @FXML
    void gotoUbahData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/ubahDataKamar.fxml"));
        Parent root  =  loader.load();
        
        Stage stage = (Stage) btnUbahDataKamar.getScene().getWindow();
        stage.setScene(new Scene(root));
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
    void gotoRoom(ActionEvent event) {

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
    
    public ObservableList<Kamar> getDataKamar(){
        ObservableList<Kamar> kamar = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `kamar`";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Kamar temp;
            while(rs.next()){
                temp = new Kamar(rs.getInt("id_kamar"), rs.getLong("harga"), rs.getString("jenis_kamar"),
                rs.getString("status_kamar"));
                kamar.add(temp);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return kamar;
    }
    
    public void showKamar(){
        ObservableList<Kamar> list_kamar = getDataKamar();
        colNoKamar.setCellValueFactory(new PropertyValueFactory<>("id_kamar"));
        colJenisKamar.setCellValueFactory(new PropertyValueFactory<>("jenis_kamar"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("Harga"));
        colStatusKamar.setCellValueFactory(new PropertyValueFactory<>("status_kamar"));
        
        tblHotel.setItems(list_kamar);
        
    }
}
