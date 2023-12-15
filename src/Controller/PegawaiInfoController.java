package Controller;

import Entitas.Pegawai;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PegawaiInfoController {

    @FXML
    private AnchorPane Home;

    @FXML
    private Label soutGaji;

    @FXML
    private Label soutIdPegawai;

    @FXML
    private Label soutJabatan;

    @FXML
    private Label soutNamaPegawai;

    @FXML
    private Label soutStatus;

    
    void showInfo(Pegawai pegawai){
        soutGaji.setText(String.format("%d", pegawai.getGaji()));
        soutIdPegawai.setText(pegawai.getId_pegawai());
        soutJabatan.setText(pegawai.getJabatan());
        soutNamaPegawai.setText(pegawai.getNama_pegawai());
        soutStatus.setText(pegawai.getStatus_pegawai());
    }
}
