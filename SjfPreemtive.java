package Osproject;






import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;


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


public class SjfPreemtive extends Application{
Stage stage;
Scene scene;
int count=1;
int size=0;
int proc[]=new int[20];
int b_time[]=new int[20];
int A_time[]=new int[20];
int []wt = new int[20];
int []tat = new int[20];
int [] chartarray;
TableView<Process> table= new TableView<Process>();
Button ganntchart;

SjfPreemtive(){
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
	stage.setTitle("SHORTEST JOB FIRS SCHEDULING (Preemptive)");
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
		
		Text text = new Text("SHORTEST JOB FIRST");
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
		Text burst = new Text("Burst_Time");
		TextField binput = new TextField();
		Button gchart = new Button("GainChart");
		
		HBox h1 = new HBox(pid,pinput,burst,binput,gchart);
		h1.setSpacing(5);
		 HBox g = new HBox();
		
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
				proc[size]=count;
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
				pinput.clear();
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
				int ct[] = new int[20]; // ct means complete time
				ganntchart=new Button();
				g.getChildren().add(ganntchart);
				int f[] = new int[20];  // f means it is flag it checks process is completed or not
				int k1[]= new int[20];   // it is also stores brust time
			    int color[]= new int [20];//for gannt chart
			    int clrsize=0;
				int i, st=0, tot=0;
				double awt = 0,att=0;	
				int nop=size;
				
				for (int i1 = 0; i1 < nop; i1++) {
					p[i1]=i1;
					bt[i1]=b_time[i1];
					time[i1]=A_time[i1];
				}
				
			 for (i=0;i<nop;i++)
			    {
			    	
			    	k1[i]= bt[i];
			    	f[i]= 0;
			    }
			    int boxsize= 45;
			    for (int j = 0; j < nop; j++) {
					color[j]=p[j];
					
				}
			    while(true){
			    	int min1=99,c=nop;
			    	if (tot==nop)
			    		break;
			    	
			    	for ( i=0;i<nop;i++)
			    	{
			    		if ((time[i]<=st) && (f[i]==0) && (bt[i]<min1))
			    		{	
			    			min1=bt[i];
			    			c=i;
			    		}
			    	}
			    	//////gant chart 
			   	if(color[clrsize] == c) {
			   		if(c == 0) {
			   			ganntchart.setText("p0");
			   		}
			   		ganntchart.setPrefWidth(boxsize+50);
			   		
			   	    }
			   	else
			    	{
			    	System.out.println(color[clrsize]+" color");
			    	ganntchart=new Button();
			    	ganntchart.setText("p"+c);
		    		ganntchart.setTextFill(Color.BLACK);
		    		g.getChildren().add(ganntchart);
			    	clrsize++;
			    	}
			    	
			    	if (c==nop)
			    		st++;
			    	else
			    	{   bt[c]--;
			    		st++;
			    		if (bt[c]==0)
			    		{
			    			ct[c]= st;
			    			f[c]=1;
			    			tot++;
			    		}
			    	}
			    }
			    
			    
			    for(i=0;i<nop;i++)
			    {
			    	tat[i] = ct[i] - time[i];
			    	wt[i] = tat[i] - k1[i];
			    	awt+= wt[i];
			    	att+= tat[i];
			    }
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
				
				for (int i1 = 0; i1 < size; i1++) {
					list.addAll(new Process(count+=1,time[i1],k1[i1],wt[i1],tat[i1]));
				}
				
				table.setItems(list);
				table.setPrefSize(200, 200);
				table.getColumns().add(0,num);
				table.getColumns().add(1, firstcol);
				table.getColumns().add(2,secondcol);
				table.getColumns().add(3,waitcol);
				table.getColumns().add(4,tatcol);
				
			
				for (int i1 = 0; i1 < p.length; i1++) {
					proc[i1]=p[i1];
				}
				
				String aw=Double.toString(awt/nop);
				String at=Double.toString(att/nop);
				
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
	
				
				g.setAlignment(Pos.CENTER);
				scene = new Scene(g);
					stage.setScene(scene);
					stage.show();
				
			}
		});
		
		
	//set buttons	
	VBox vb = new VBox(h1,add,start);
	vb.setSpacing(5);
	Group g1 = new Group();
	g1.getChildren().add(table);
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

