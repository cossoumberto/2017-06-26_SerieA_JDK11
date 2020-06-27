package it.polito.tdp.seriea.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.seriea.model.Incontro;
import it.polito.tdp.seriea.model.Match;

public class SerieADAO {
/*
	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}*/
	
	public List<Incontro> getIncontri(){
		String sql = "SELECT HomeTeam, AwayTeam, COUNT(match_id) AS c FROM matches GROUP BY HomeTeam, AwayTeam";
		List<Incontro> list = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Incontro incontro = new Incontro(res.getString("HomeTeam"), res.getString("AwayTeam"), res.getInt("c"));
				list.add(incontro);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> getStagioni(){
		String sql = "SELECT DISTINCT Season FROM matches ORDER BY Season";
		List<Integer> list = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next())
				list.add(res.getInt("Season"));
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Match> getMatchStagione (Integer stagione){
		String sql = "SELECT match_id, Season, `Div`, DATE, HomeTeam, AwayTeam, FTHG, FTAG, FTR FROM matches WHERE Season = ?";
		List<Match> list = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, stagione);
			ResultSet res = st.executeQuery();
			while (res.next())
				list.add(new Match(res.getInt("match_id"), res.getInt("Season"), res.getString("Div"), res.getDate("DATE").toLocalDate(), 
						res.getString("HomeTeam"), res.getString("AwayTeam"), res.getInt("FTHG"), res.getInt("FTAG"), res.getString("FTR")));
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

