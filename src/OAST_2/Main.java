package OAST_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("OAST_2.fxml"));
        primaryStage.setTitle("OAST_2");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();
//        final FileChooser  fileChooser = new FileChooser();
//        File file = fileChooser.showOpenDialog(primaryStage);
//        if(file != null){
//            Reader reader = new Reader(file);
//            BruteForce bruteForce = new BruteForce(reader, 100);
////            Evolution evolution = new Evolution(reader, 1000,0.05, 1000, 10, 10, 100, 3000, 60);
//        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
