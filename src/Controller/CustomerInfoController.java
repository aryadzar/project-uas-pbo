package Controller;

import Entitas.Pelanggan;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CustomerInfoController {

    @FXML
    private Label lblAlamat;

    @FXML
    private Label lblIdPelanggan;

    @FXML
    private Label lblNama;

    @FXML
    private Label lblNoKtp;

    @FXML
    private Label lblTelepon;
    
    public void showBiodata(Pelanggan pg){
        lblAlamat.setText(pg.getAlamat());
        lblIdPelanggan.setText(String.format("%d", pg.getId_pelanggan()));
        lblNama.setText(pg.getNama_pelanggan());
        lblNoKtp.setText(pg.getNo_ktp());
        lblTelepon.setText(pg.getNo_telepon());

    }
}
