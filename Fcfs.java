package Osproject;






import java.util.Arrays;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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


public class Fcfs extends Application{
Stage stage;
Scene scene;
int count=1;
int size=0;
int process[]=new int[20];
int b_time[]=new int[20];
int A_time[]=new int[20];
int []wt = new int[20];
int []tat = new int[20];
int [] chartarray;
TableView<Process> table= new TableView<Process>();
Fcfs(){
	try {
		start(new Stage());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	stage=primaryStage;
	stage.setTitle("FIRST COME FIRST SERVE SCHEDULING");
	stage.setHeight(600);
	stage.setWidth(800);
	stage.setResizable(false);
	scheduling();
	stage.show();
	}



	
	private void scheduling() {
		
		Button add = new Button("Add Process");
		add.setPrefSize(100, 25);
		Button start = new Button("Start");
		start.setPrefSize(100, 25);
		Label ltat = new Label("Average Turn arround time");
		Label lwat= new Label("Average Waiting  Time");
		HBox ht = new HBox(ltat);
		HBox hw = new HBox(lwat);
		
		ObservableList<Process> list= FXCollections.observableArrayList();	
//////////header set/////////////////
		
		Text text = new Text("First Come First Serve");
		text.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.ITALIC,35));
		text.setFill(Color.DEEPPINK);
//////////////Columns create///////////////////
	  
		TableColumn<Process, Integer> num = new TableColumn<Process, Integer>("process_id");
		num.setMinWidth(100);
		num.setResizable(false);
	    TableColumn<Process, Integer> firstcol = new TableColumn<Process, Integer>("alrivat_time");
		firstcol.setResizable(false);
		firstcol.setMinWidth(100);
		TableColumn<Process, Integer> secondcol = new TableColumn<Process, Integer>("Process_burst_Time");
		secondcol.setPrefWidth(150);
		secondcol.setResizable(false);
		TableColumn<Process, Integer>waitcol = new TableColumn<Process, Integer>("Wait_Time");
		waitcol.setPrefWidth(150);
		TableColumn<Process, Integer>tatcol = new TableColumn<Process, Integer>("Turn_Around_Time");
		tatcol.setPrefWidth(150);
/////Observable list//////////////////
	
		firstcol.setCellValueFactory(new PropertyValueFactory<Process,Integer>("alrivat_time"));
		secondcol.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Process_burst_Time"));
		num.setCellValueFactory(new PropertyValueFactory<Process,Integer>("process_id"));
////////set column in table//////////////
		
		waitcol.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Wait_Time"));
		
		
		tatcol.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Turn_Around_Time"));
		
		
		table.setItems(list);
		table.setFixedCellSize(30);
		table.getColumns().add(0,num);
		table.getColumns().add(1, firstcol);
		table.getColumns().add(2,secondcol);
		table.getColumns().add(3,waitcol);
		table.getColumns().add(4,tatcol);
		
		table.setPrefSize(300,300);
///////////////////////input elements for process///////////////////////////////////////////////////////////////////
		
		Text pid = new Text("Arival_Time");
		TextField pinput = new TextField();
		pinput.setText("0");
		pinput.setEditable(false);
		Text burst = new Text("Burst_Time");
		TextField binput = new TextField();
		Button gchart = new Button("GainChart");
		
		HBox h1 = new HBox(pid,pinput,burst,binput,gchart);
		h1.setSpacing(5);

		
//////////////////add process////////////////////////////////////////////////////////////
		
		add.setOnAction(new EventHandler<ActionEvent>() {

		
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				  String arrival_time=pinput.getText();
				  String btime=binput.getText();
				int atime=Integer.parseInt(arrival_time);
				int bstime=Integer.parseInt(btime);
				
				
				if(arrival_time.isEmpty()&&btime.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("enter process burst time and arrival time");
					alert.show();
				}
				else if(!(arrival_time.isEmpty())&&(!btime.isEmpty()))
				{
					
				list.addAll(new Process(count,atime,bstime,0,0));
				process[size]=count;
				b_time[size]=bstime;
				A_time[size]=atime;
			  
				count++;
				size++;
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("enter process burst time and arrival time properly ");
					alert.show();
				}
				
				binput.clear();
				
			}
		});
		
		
		start.setOnAction(new EventHandler<ActionEvent>() {

			
			public void handle(ActionEvent event) {
			int [] bt = new int[20];
				int []wt = new int[20];
				int []tat = new int[20];
				int []time=new int[20];
				int []p=new int[20];
				chartarray=new int [size];
				double awt = 0,att=0;	
				int nop=size;
				for (int i = 0; i < nop; i++) {
					p[i]=i;
					bt[i]=b_time[i];
					time[i]=A_time[i];
				}
				wt[0]=0;
                 tat[0]=bt[0];
				
				for (int i = 1; i < nop; i++) {

					
					wt[i] = wt[i-1] +bt[i-1];
					tat[i]=wt[i]+bt[i]; 
					awt += wt[i]; 
					att+=tat[i];

				}
				
				awt/=nop;
				att/=nop;

				
				/////table/////////////////////
				int count=0;
				table.getItems().clear();
				table = new TableView<Process>();
				
				TableColumn<Process, Integer> waitcol = new TableColumn<Process, Integer>("Wait_Time");
				waitcol.setPrefWidth(150);
				TableColumn<Process, Integer> tatcol = new TableColumn<Process, Integer>("Turn_Around_Time");
				tatcol.setPrefWidth(150);
		/////Observable list//////////////////
			
				
				waitcol.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Wait_Time"));
				tatcol.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Turn_Around_Time"));
				
				for (int i = 0; i < size; i++) {
					list.addAll(new Process(count+=1,time[i],bt[i],wt[i],tat[i]));
				}
				
				table.setItems(list);
				table.setPrefSize(200, 200);
				table.getColumns().add(0,num);
				table.getColumns().add(1, firstcol);
				table.getColumns().add(2,secondcol);
				table.getColumns().add(3,waitcol);
				table.getColumns().add(4,tatcol);
				
				for (int i = 0; i < chartarray.length; i++) {
					chartarray[i]=bt[i];
					
				}
				String aw=Double.toString(awt);
				String at=Double.toString(att);
				
				Text  ttat = new Text(":  "+at);
				Text  twat = new Text(":  "+aw);
				ht.getChildren().add(ttat);
				hw.getChildren().add(twat);
			}
		});
////////Ganntchart//////////////////////////////////////
		
		gchart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			
				 final NumberAxis xAxis = new NumberAxis();
			        final CategoryAxis yAxis = new CategoryAxis();
			        final StackedBarChart< Number,String> bc =
			                new StackedBarChart< Number,String>(xAxis, yAxis);
			        bc.setTitle("Gannt Chart");
			        xAxis.setLabel("Burst_time");  
			        xAxis.setTickLabelRotation(90);
			        yAxis.setLabel("Process");        
			 
			       
			   final String process = "Process";
		     
			yAxis.setCategories(FXCollections.<String>observableArrayList(
		             Arrays.asList(process)));
			for (int i = 0; i < chartarray.length; i++) {
				final XYChart.Series series = new XYChart.Series();
				series.setName("process"+i);       
		        series.getData().add(new XYChart.Data(chartarray[i], process));
		        bc.getData().addAll(series);
			}
			 HBox g = new HBox();
			g.getChildren().add(bc);
					scene = new Scene(g);
					stage.setScene(scene);
					stage.show();
				
			}
		});
		
		
	//set buttons	
	VBox vb = new VBox(h1,add,start);
	vb.setSpacing(5);
	Group g = new Group();
	g.getChildren().add(table);
	///parent root
    HBox h = new HBox(text);
	h.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
	h.setAlignment(Pos.CENTER);
	VBox v=new VBox(h,vb,table,ht,hw);
		scene =  new Scene(v);
		stage.setScene(scene);
        stage.show();
}
}

