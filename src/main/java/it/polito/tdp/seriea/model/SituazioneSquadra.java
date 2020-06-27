package it.polito.tdp.seriea.model;

public class SituazioneSquadra implements Comparable<SituazioneSquadra>{
	
	private String squadra;
	private Integer tifosi;
	private Integer punti;
	
	public SituazioneSquadra(String squadra) {
		super();
		this.squadra = squadra;
		this.tifosi = 1000;
		this.punti = 0;
	}

	public String getSquadra() {
		return squadra;
	}

	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}

	public Integer getTifosi() {
		return tifosi;
	}

	public void setTifosi(Integer tifosi) {
		this.tifosi = tifosi;
	}

	public Integer getPunti() {
		return punti;
	}

	public void setPunti(Integer punti) {
		this.punti = punti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((squadra == null) ? 0 : squadra.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SituazioneSquadra other = (SituazioneSquadra) obj;
		if (squadra == null) {
			if (other.squadra != null)
				return false;
		} else if (!squadra.equals(other.squadra))
			return false;
		return true;
	}

	@Override
	public int compareTo(SituazioneSquadra o) {
		return o.punti-this.punti;
	}

	@Override
	public String toString() {
		return squadra + ", tifosi=" + tifosi + ", punti=" + punti;
	}
	
	
}
