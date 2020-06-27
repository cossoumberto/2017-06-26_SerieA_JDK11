package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.SituazioneSquadra;
import it.polito.tdp.seriea.model.TeamPeso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno A --> switchare al branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> boxSquadra;

    @FXML
    private ChoiceBox<Integer> boxStagione;

    @FXML
    private Button btnCalcolaConnessioniSquadra;

    @FXML
    private Button btnSimulaTifosi;

    @FXML
    private Button btnAnalizzaSquadre;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaSquadre(ActionEvent event) {
    	model.creaGrafo();
    	boxSquadra.getItems().clear();
    	boxSquadra.getItems().addAll(model.getSquadre());
    	txtResult.setText("Grafo creato con " + model.getSquadre().size() + " vertici e " + model.getNumEdge() + " archi");
    }

    @FXML
    void doCalcolaConnessioniSquadra(ActionEvent event) {
    	if(boxSquadra.getValue()!=null) {
    		txtResult.setText(boxSquadra.getValue() + " --> Connessioni: ");
    		for(TeamPeso t : model.getConnessi(boxSquadra.getValue()))
    			txtResult.appendText("\n" + t);
    	} else 
    		txtResult.setText("Seleziona una squadra");
    }

    @FXML
    void doSimulaTifosi(ActionEvent event) {
    	if(boxStagione.getValue()!=null) {
    		if(model.getNumEdge()!=null) {
    			txtResult.setText("Classifica: ");
    			for(SituazioneSquadra s : model.simulazione(boxStagione.getValue()))
    				txtResult.appendText("\n" + s);
    		} else 
    			txtResult.setText("Crea il grafo");
    	} else
    		txtResult.setText("Seleziona una stagione");
    }

    @FXML
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxStagione != null : "fx:id=\"boxStagione\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaTifosi != null : "fx:id=\"btnSimulaTifosi\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaSquadre != null : "fx:id=\"btnAnalizzaSquadre\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxStagione.getItems().addAll(model.getStagioni());
	}
}