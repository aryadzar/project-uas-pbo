package Controller;

import AlertHelper.AlertHelper;
import DBHelper.DBHelper;
import Entitas.Kamar;
import Entitas.Layanan;
import Entitas.Pelanggan;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FasilitasPelangganController implements Initializable {

    @FXML
    private Button btnExit;

    @FXML
    private Button btnFasilitas;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnKonfirmasi;

    @FXML
    private ChoiceBox<Integer> choiceFasilitas;

    @FXML
    private ChoiceBox<Integer> choiceKamar;

    @FXML
    private TableColumn<Kamar, Integer> colHarga;

    @FXML
    private TableColumn<Layanan, Integer> colHargaLayanan;

    @FXML
    private TableColumn<Kamar, String> colJenisKamar;

    @FXML
    private TableColumn<Layanan, String> colLayanan;

    @FXML
    private TableColumn<Kamar, Integer> colNoKamar;

    @FXML
    private TableColumn<Layanan, Integer> colNoLayanan;

    @FXML
    private TableColumn<Kamar, String> colStatus;
    
    @FXML
    private DatePicker dateCheckIn;

    @FXML
    private TextField tfJumlahTamu;
    
    @FXML
    private DatePicker dateCheckOut;
    
    @FXML
    private DatePicker dateTglReservasi;
    
    @FXML
    private TableView<Kamar> tblKamar;

    @FXML
    private Button userInfo;
    @FXML
    private TableView<Layanan> tblFasilitas;
    
    ArrayList<Integer> no_kamar = new ArrayList<>();
    ArrayList<Integer> no_layanan = new ArrayList<>();
    long id_pelanggan = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        showKamar();
        showLayanan();
        choiceKamar.getItems().addAll(no_kamar);
        choiceFasilitas.getItems().addAll(no_layanan);
    }
    @FXML
    void pilihan(ActionEvent event) {
        if(isInteger(tfJumlahTamu.getText())){
            if(AlertHelper.showConfirmationAlert("Konfirmasi", "Anda yakin mau reservasi ? ")){
                insertReservasi();
            }
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Kesalahan Tipe Data", "Harus Bilangan Berupa Angka");
        }
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
    void gotoFasilitas(ActionEvent event) {
        
    }

    @FXML
    void gotoHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLCol/Pelanggan.fxml"));
        Parent root  =  loader.load();

        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(new Scene(root));  
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
    

    private Date convertToDate(LocalDate localDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = localDate.toString();

        try {
            return (Date) sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
                    id_pelanggan = pelanggan.getId_pelanggan();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelanggan;
        
        
    }
    private void insertReservasi(){
        String query = "INSERT INTO `reservasi`( `id_pelanggan`, `id_kamar`, `id_layanan`, `tanggal_reservasi`, `tanggal_checkin`, `tanggal_checkout`, `jumlah_tamu`, `status_pemesanan`) "
                + "VALUES (?,?,?,?,?,?,?,?) ";
        Pelanggan  pg = getPelanggan(HomeController.user,HomeController.pass);
        long Id_pelanggan = pg.getId_pelanggan();
        int no_kamar = choiceKamar.getValue();
        int id_layanan = choiceFasilitas.getValue();
        Date tgl_reservasi = convertToDate(dateTglReservasi.getValue());
        Date tgl_checkin = convertToDate(dateCheckIn.getValue());
        Date tgl_checkout = convertToDate(dateCheckOut.getValue());
        int jumlah_tamu = Integer.parseInt(tfJumlahTamu.getText());
        
        String status_pemesanan = "Sedang Diproses";
        try(Connection conn = DBHelper.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)){
            ps.setLong(1, Id_pelanggan);
            ps.setInt(2, no_kamar);
            ps.setInt(3, id_layanan);
            ps.setDate(4, new java.sql.Date(tgl_reservasi.getTime()));
            ps.setDate(5, new java.sql.Date(tgl_checkin.getTime()));
            ps.setDate(6, new java.sql.Date(tgl_checkout.getTime()));
            ps.setInt(7, jumlah_tamu);
            ps.setString(8, status_pemesanan);
            ps.executeUpdate();
            
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Berhasil Melakukan Reservasi");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
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
                
                if(!temp.getStatus_kamar().equals("Sudah dipesan")){
                    kamar.add(temp);
                    no_kamar.add(temp.getId_kamar());
                }
                
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
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status_kamar"));
        
        tblKamar.setItems(list_kamar);
        
    }
    public ObservableList<Layanan> getDataLayanan(){
        ObservableList<Layanan> layanan = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `layanan`;";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Layanan temp;
            while(rs.next()){
                temp = new Layanan(rs.getInt("id_layanan"), rs.getString("deskripsi_layanan"),rs.getLong("harga_layanan")
                        ,rs.getString("kontak_layanan") ,rs.getString("status_layanan") , rs.getString("ulasan"));
                
                
                layanan.add(temp);
                no_layanan.add(temp.getId_layanan());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return layanan;
    }
    
    public void showLayanan(){
        ObservableList<Layanan> list_layanan = getDataLayanan();
        colNoLayanan.setCellValueFactory(new PropertyValueFactory<>("id_layanan"));
        colLayanan.setCellValueFactory(new PropertyValueFactory<>("deskripsi_layanan"));
        colHargaLayanan.setCellValueFactory(new PropertyValueFactory<>("harga"));
        
        
        tblFasilitas.setItems(list_layanan);
    }
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
