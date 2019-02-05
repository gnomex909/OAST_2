package OAST_2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    @FXML
    Button button_load;
    @FXML
    Button button_brute;
    @FXML
    Button button_evo;
    @FXML
    Text text_load;
    @FXML
    Text text_progress_brute;
    @FXML
    Text text_progress_evo;
    @FXML
    TextField input_time_brute;
    @FXML
    TextField input_time_evo;
    @FXML
    TextField input_gen_evo;
    @FXML
    TextField input_seed_evo;
    @FXML
    TextField input_pair_evo;
    @FXML
    TextField input_child_evo;
    @FXML
    TextField input_mutation_evo;
    @FXML
    TextField input_first_gen_evo;





    private Reader reader;
    @FXML
    public void onEvo(){
        if(reader!=null){
            String text = input_time_evo.getText();
            int timeLimit = 9999;
            boolean correctInput = true;
            if(text.length()>0){
                timeLimit = Integer.parseInt(text);
            }
            int genLimit = 99999;
            text = input_gen_evo.getText();
            if(text.length()>0){
                genLimit = Integer.parseInt(text);
            }
            text = input_child_evo.getText();
            int childLimit = 0;
            if(text.length()>0){
                childLimit = Integer.parseInt(text);
            }else{
                correctInput = false;
            }
            text = input_mutation_evo.getText();
            double mutationChance = 0;
            if(text.length()>0){
                mutationChance = Double.parseDouble(text);
            }else{
                correctInput = false;
            }
            text = input_first_gen_evo.getText();
            int firstGen = 0;
            if(text.length()>0){
                firstGen = Integer.parseInt(text);
            }else{
                correctInput = false;
            }
            text = input_pair_evo.getText();
            int pairs = 0;
            if(text.length()>0){
                pairs = Integer.parseInt(text);
            }else{
                correctInput = false;
            }
            text = input_seed_evo.getText();
            long seed = 0;
            if(text.length()>0){
                seed = Long.parseLong(text);
            }else{
                correctInput = false;
            }

            if(correctInput){
                new Evolution(reader, seed,mutationChance,firstGen,pairs,childLimit, pairs*childLimit,genLimit,timeLimit);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Brak danych wejściowych");
                alert.setHeaderText(null);
                alert.setContentText("Wprowadź poprawne dane wejściowe");
                alert.showAndWait();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak pliku z danymi wejśćiowymi");
            alert.setHeaderText(null);
            alert.setContentText("Wczytaj najpierw plik z danymi wejściowymi");
            alert.showAndWait();
        }
    }
    @FXML
    public void onLoad(){
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(button_load.getScene().getWindow());
        if(file != null){
            reader = new Reader(file);
            text_load.setText("Wczytano plik: " + file.getName());
//            BruteForce bruteForce = new BruteForce(reader, 100);
//            Evolution evolution = new Evolution(reader, 1000,0.05, 1000, 10, 10, 100, 3000, 60);
        }
    }

    @FXML
    public void onBrute(){
        if(reader != null){
            text_progress_brute.setText("Algorytm rozpoczął działanie.");
            String time = input_time_brute.getText();
            int timeLimit = 9999;
            if(time.length()>0){
                timeLimit = Integer.parseInt(time);
            }
            new BruteForce(reader, timeLimit);
            text_progress_brute.setText("Zakończone działanie algorytmu.");

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak pliku z danymi wejśćiowymi");
            alert.setHeaderText(null);
            alert.setContentText("Wczytaj najpierw plik z danymi wejściowymi");
            alert.showAndWait();
        }
    }
}
