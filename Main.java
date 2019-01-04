package Osproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
Stage stage;
Scene scene;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		stage=primaryStage;
		stage.setTitle("Operating Sysytem Project");
		stage.setHeight(500);
		stage.setWidth(500);
		stage.setResizable(false);
		scheduling();
		stage.show();
		
	}

	private void scheduling() {
		// TODO Auto-generated method stub
		Text text = new Text("CPU Scheduling");
		text.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.ITALIC,50));
		text.setFill(Color.CORNFLOWERBLUE);
		ToggleGroup g= new ToggleGroup();
		RadioButton fcfs = new RadioButton("FCFS");
		fcfs.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
		fcfs.setToggleGroup(g);
		RadioButton sjf = new RadioButton("SJF");
		sjf.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
		sjf.setToggleGroup(g);
		RadioButton priority = new RadioButton("PRIORITY");
		priority.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
		priority.setToggleGroup(g);
		
		fcfs.setOnAction(new EventHandler<ActionEvent>() {
		
		
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RadioButton arivalfcfs = new RadioButton("Fcfs with Arival Time");
				arivalfcfs.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
				arivalfcfs.setToggleGroup(g);
				RadioButton nAfcfs = new RadioButton("Fcfs with 0 Arival Time");
				nAfcfs.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
				nAfcfs.setToggleGroup(g);
				 VBox g = new VBox();
					g.getChildren().addAll(arivalfcfs,nAfcfs);
					g.setAlignment(Pos.CENTER);	
					g.setSpacing(20);
					g.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
					scene = new Scene(g);
							stage.setScene(scene);
							stage.show();
				arivalfcfs.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									FcfsArivalTime obj = new FcfsArivalTime();
								stage.close();
								}
							});
      			nAfcfs.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									Fcfs a =new Fcfs();
									stage.close();
								}
							});	
			}
		});
		sjf.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RadioButton preemptsjf = new RadioButton("Preemptive SJF");
				preemptsjf.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
				preemptsjf.setToggleGroup(g);
				RadioButton sjfnonpreempt = new RadioButton("Non Preemptive SJF");
				sjfnonpreempt.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
				sjfnonpreempt.setToggleGroup(g);
				 VBox g = new VBox();
					g.getChildren().addAll(preemptsjf,sjfnonpreempt);
					g.setAlignment(Pos.CENTER);	
					g.setSpacing(20);
					g.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
					scene = new Scene(g);
							stage.setScene(scene);
							stage.show();
							preemptsjf.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									SjfPreemtive s = new SjfPreemtive();
									stage.close();
								}
							});
							sjfnonpreempt.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									 Sjf a = new  Sjf();
									 stage.close();
								}
							});
			}
		});
		priority.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RadioButton preemptpri = new RadioButton("Preemptive Priority");
				preemptpri.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
				preemptpri.setToggleGroup(g);
				RadioButton prinonpreempt = new RadioButton("Non Preemptive Priority");
				prinonpreempt.setFont(Font.font("Bell MT",FontWeight.BOLD,30));
				prinonpreempt.setToggleGroup(g);
				 VBox g = new VBox();
					g.getChildren().addAll(preemptpri,prinonpreempt);
					g.setAlignment(Pos.CENTER);	
					g.setSpacing(20);
					g.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
					scene = new Scene(g);
							stage.setScene(scene);
							stage.show();
							preemptpri.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									PriorityPreemtive pri = new PriorityPreemtive();
									stage.close();
								}
							});
							prinonpreempt.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									Priority nonpri = new Priority();
									stage.close();
								}
							});
			}
		});
		
		
		HBox h = new HBox(text);
		h.setAlignment(Pos.CENTER);
        VBox v = new VBox(h,fcfs,sjf,priority);
        v.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		v.setSpacing(20);
		v.setAlignment(Pos.CENTER);
        scene = new Scene(v);
        stage.setScene(scene);
        stage.show();
		
		
	}

}
