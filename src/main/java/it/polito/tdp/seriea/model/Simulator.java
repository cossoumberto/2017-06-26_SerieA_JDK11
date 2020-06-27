package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Simulator {
	
	//PARAMETRI
	private Integer P = 10;
	private List<Match> partite;
 	
	//STATO SIMULATORE //OUTPUT
	private  Map<String, SituazioneSquadra> squadre; 
	
	//CODA EVENTI
	private Queue<Match> queue;
	
	public void init(List<String> squadre, List<Match> partite) {
		this.partite = partite;
		this.squadre = new HashMap<>();
		for(String s : squadre)
			this.squadre.put(s, new SituazioneSquadra(s));
		queue = new PriorityQueue<>(partite);
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Match m = queue.poll();
			processEvent(m);
		}
	}

	private void processEvent(Match m) {
		SituazioneSquadra casa = squadre.get(m.getHomeTeam());
		SituazioneSquadra trasferta = squadre.get(m.getAwayTeam());
		Double tifosiCasa = casa.getTifosi().doubleValue();
		Double tifosiTrasferta = trasferta.getTifosi().doubleValue();
		if(tifosiCasa < tifosiTrasferta) {
			if(Math.random() > (tifosiCasa/tifosiTrasferta))
				m.setFthg(m.getFthg()-1);
		} else if(tifosiCasa > tifosiTrasferta) {
			if(Math.random() > (tifosiTrasferta/tifosiCasa))
				m.setFtag(m.getFtag()-1);
		}
		if(m.getFthg()>m.getFtag()) {
			Double perc = (P.doubleValue()*(m.getFthg()-m.getFtag()))/100.0;
			casa.setPunti(casa.getPunti()+3);
			casa.setTifosi((int) (casa.getTifosi() * (1+perc)));
			trasferta.setTifosi((int) (trasferta.getTifosi() * (1-perc)));
		} else if (m.getFthg()<m.getFtag()) {
			Double perc = (P.doubleValue()*(m.getFtag()-m.getFthg()))/100.0;
			trasferta.setPunti(trasferta.getPunti()+3);
			casa.setTifosi((int) (casa.getTifosi() * (1-perc)));
			trasferta.setTifosi((int) (trasferta.getTifosi() * (1+perc)));
		} else {
			casa.setPunti(casa.getPunti()+1);
			trasferta.setPunti(trasferta.getPunti()+1);
		}
	}
	
	public List<SituazioneSquadra> classifica(){
		List<SituazioneSquadra> list = new ArrayList<>(squadre.values());
		Collections.sort(list);
		return list;
	}
}
