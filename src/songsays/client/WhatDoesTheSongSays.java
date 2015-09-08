package songsays.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;




@SuppressWarnings("deprecation")
public class WhatDoesTheSongSays implements EntryPoint {
	
      private VerticalPanel resultPanel = new VerticalPanel();
      private PieChart pieChart;
      private Result res;
	  private Label url = new Label();
	  private Label entre = new Label();
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private VerticalPanel dwnldPanel = new VerticalPanel();
	  Hyperlink hyp = new Hyperlink();
	  
	  Hyperlink dwnldJar = new Hyperlink("Download JAR", "Download JAR");
	  private Button analyzeButton ;
	  private TextArea inputSongs = new TextArea();
	  private String [] songs;
	  private Label msg= new Label();
	  private AnalyzeServiceAsync analyzeSvc;
	  private PieChart chart;
	  private TextArea result = new TextArea();
	  // Use the implementation of Auth intended to be used in the GWT client app.
	  
	  /**
	   * Entry point method.
	   */
	  
	
	  @SuppressWarnings("deprecation")
	public void onModuleLoad() {
		
	//		System.out.println("I enter");
		    entre.setText("Type in your favorite songs titles here, one per line... the more the better");
		    mainPanel.add(entre);
		    mainPanel.add(inputSongs);
		    

		   analyzeButton = new Button("Analyze !", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        songs= inputSongs.getText().split("\n");
		        analyzeSvc = GWT.create(AnalyzeService.class);
		        analyzeSvc.analyzeSongs(songs, new AsyncCallback<Result>(){
					   public void onSuccess(Result r)
					   {
						   res = r;  
						   
					        
							 msg.setText(res.message);
							 mainPanel.add(msg);
							 result.setVisibleLines(15);
							 result.setHeight("500");
							 result.setWidth("500");
							 
							 
							 result.setReadOnly(true);
							    result.setText("Happy:" + (int)res.sentimentValues[0]+"%\n"+
							     "Sad:" + (int)res.sentimentValues[1]+"% \n"+
							     "Anger:" + (int)res.sentimentValues[2] +"% \n"+
							     "Fear:" + (int)res.sentimentValues[3] +"% \n"+
							     "Disgust:" + (int)res.sentimentValues[4] +"% \n"+
							     "Surprise:" + (int)res.sentimentValues[5]+"% \n \n"+
							     "Positivity:"+ (int)res.posSentiment+"% \n\n"+
							     "Negativity:"+ (int)res.negSentiment+"% \n\n");
							     
							     
							      
							   					     				    		
							    		
							    	
							    
							    mainPanel.add(result);
							    
							   
					   }
					   
					   public void onFailure(Throwable caught)
					   {
						   res = null;
					   }
					   	
					   
				   });
		       
		     
		    	   //generatePi(res);
		       
		        
		      }
		    });

    	    mainPanel.add(analyzeButton);
		
		    url.setText("Looks like a mammoth task !? Download the jar below to get the titles from your music folder!");
	       dwnldJar.addClickListener(new ClickListener() {

				@Override
				public void onClick(Widget sender) {
					com.google.gwt.user.client.Window.open("https://drive.google.com/open?id=0BybdH-Y3lZ6zX1U3MTluM21EakE","_blank",null);
					
				}
					
			});

		    
		    
		    dwnldPanel.add(url);
		    dwnldPanel.add(dwnldJar);
		    mainPanel.add(dwnldPanel);
		
		    RootPanel.get("playList").add(mainPanel);
		    
		    
		  
	  
	  }
	  
	  private Options createOptions() {
		    Options options = Options.create();
		    options.setWidth(400);
		    options.setHeight(240);
		    options.set3D(true);
		    options.setTitle("What type of songs I listen to?");
		    return options;
		  }

	  
	  private AbstractDataTable drawPieChart(double [] sentVals) {
	          // Prepare the data
	          DataTable dataTable = DataTable.create();
	          dataTable.addColumn(ColumnType.STRING, "Emotion");
	          dataTable.addColumn(ColumnType.NUMBER, "Percentage");
	          dataTable.addRows(4);
	          dataTable.setValue(0, 0, "Happy");
	          dataTable.setValue(1, 0, "Sad");
	          dataTable.setValue(2, 0, "Anger");
	          dataTable.setValue(3, 0, "Fear");
	          dataTable.setValue(4, 0, "Disgust");
	          dataTable.setValue(5, 0, "Surprise");
	          
	          dataTable.setValue(0, 1, sentVals[0]);
	          dataTable.setValue(1, 1, sentVals[1]);
	          dataTable.setValue(2, 1, sentVals[2]);
	          dataTable.setValue(3, 1, sentVals[3]);
	          dataTable.setValue(4, 1, sentVals[4]);
	          dataTable.setValue(5, 1, sentVals[5]);
	          // Draw the chart
	          return dataTable;
	  }

	  
	}


