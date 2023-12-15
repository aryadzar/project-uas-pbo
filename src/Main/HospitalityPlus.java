/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.StageStyle;

/**
 *
 * @author aryad
 */
public class HospitalityPlus extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLCol/Home.fxml"));
        String audioPath = "/Music/melody-of-nature-main(chosic.com).mp3"; 
        Media media = new Media(getClass().getResource(audioPath).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(mediaPlayer.getStartTime()));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/Image/Logo.png", 20, 25, true,true));
        stage.setTitle("HospitalityPlus - Powered By B6 ");
//        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
//        mediaPlayer.play();
//        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
