package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {

	private SerieADAO dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private Simulator s; 
	
	public Model() {
		dao = new SerieADAO();
		grafo = null;
	}
	
	public List<Integer> getStagioni() {
		return dao.getStagioni();
	}
	
	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		for(Incontro i : dao.getIncontri()) {
			if(!grafo.containsEdge(i.getS1(), i.getS2()))
				Graphs.addEdgeWithVertices(grafo, i.getS1(), i.getS2(), i.getPeso());
			else {
				Double peso = grafo.getEdgeWeight(grafo.getEdge(i.getS1(), i.getS2()));
				grafo.setEdgeWeight(i.getS1(), i.getS2(), i.getPeso()+peso);
			}
		}
	}
	
	public List<String> getSquadre() {
		List<String> list = new ArrayList<>();
		list.addAll(this.grafo.vertexSet());
		Collections.sort(list);
		return list;
	}
	
	public Integer getNumEdge() {
		if(grafo!=null)
			return this.grafo.edgeSet().size();
		else return null;
	}
	
	public List<TeamPeso> getConnessi(String squadra){
		List<TeamPeso> list = new ArrayList<>();
		for(String s : Graphs.neighborListOf(grafo, squadra))
			list.add(new TeamPeso(s, (int)grafo.getEdgeWeight(grafo.getEdge(squadra, s))));
		Collections.sort(list);
		return list;
	}
	
	public List<SituazioneSquadra> simulazione (Integer stagione){
		s = new Simulator();
		s.init(this.getSquadre(), dao.getMatchStagione(stagione));
		s.run();
		return s.classifica();
	}
}
