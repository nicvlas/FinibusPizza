package FinibusPizza;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent; 
import javafx.event.EventHandler; 


public class PateFen extends Application{
	private long start;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("P�trissage");
        Label label = new Label("P�trissage en attente");

        // on fait deux boutons qui vont servir
        Button button1 = new Button("P�trir");
        Button button2 = new Button("Stop");

        button1.setStyle("-fx-margin-left:20px;");
        button1.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
            	 start = System.currentTimeMillis();//on prend l'heure systeme
               	 System.out.println(start);
               	 button1.setDisable(true);//on bloque le bouton
            	 label.setText("P�trissage en cours !");
            }
         }));
        
        button2.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
            	long end = System.currentTimeMillis(); //on prend l'heure syst�me de fin de t�che
               	System.out.println(end);
               	long elapsedTime = (end - start)/1000; //diff�rence
               	button2.setDisable(true);//bloquage bouton
               	
               	if(elapsedTime < 5 || elapsedTime > 5) {
               		System.out.println(elapsedTime + " pas assez");
               	}
               	else {
                   	System.out.println(elapsedTime + " seconde(s) de p�trissage effectu�e(s)"); //secondes de p�trissage
               	}
               	//pop erreur si elapsedTime < ou > � tempsPetrissage
               	//reload la scene
            }
         }));


        HBox hbox = new HBox(label, button1, button2);

        Scene scene = new Scene(hbox, 400, 100);
        primaryStage.setScene(scene);
        primaryStage.show();


	}

}
