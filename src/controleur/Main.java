package controleur;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;

public class Main extends Application {
    public static Parent root;
    public static Parent rootcreationNiveau;
    public static Parent rootListeNiveau;
    public static Stage creationNiveauStage = new Stage();
    public static Stage ListeNiveauStage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //Création écran Menu
            root = FXMLLoader.load(getClass().getResource("../fxml/Menu.fxml"));
            primaryStage.setTitle("PizzaFinibus : Menu");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            //Création écran Liste Niveau
            rootListeNiveau = FXMLLoader.load(getClass().getResource("../fxml/MenuListeNiveau.fxml"));
            ListeNiveauStage.setTitle("PizzaFinibus : Liste Niveaux");
            ListeNiveauStage.setScene(new Scene(rootListeNiveau));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
