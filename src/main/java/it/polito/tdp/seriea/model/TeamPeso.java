package it.polito.tdp.seriea.model;

public class TeamPeso implements Comparable<TeamPeso>{

	private String team;
	private Integer peso;
	
	public TeamPeso(String team, Integer peso) {
		super();
		this.team = team;
		this.peso = peso;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return team + " " + peso;
	}

	@Override
	public int compareTo(TeamPeso o) {
		return -(this.peso-o.peso);
	}
	
	
	
}
