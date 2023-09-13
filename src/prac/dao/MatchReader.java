package prac.dao;


import prac.con.Connector;
import prac.pojo.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class MatchReader {
    public static final int ID = 1;
    public static final int SEASON=2;
    public static final int CITY=3;
    public static final int DATE=4;
    public static final int TEAM1=5;
    public static final int TEAM2=6;
    public static final int TOSS_WINNER=7;
    public static final int TOSS_DECISION=8;
    public static final int RESULT=9;
    public static final int DL_APPLIED=10;
    public static final int WINNER=11;
    public static final int WIN_BY_RUNS=12;
    public static final int WIN_BY_WICKETS=13;
    public static final int PLAYER_OF_MATCH=14;
    public static final int VENUE=15;
    public static final int UMPIRE1=16;
    public static final int UMPIRE2=17;
    public static final int UMPIRE3=18;

    public List<Match> getMatchData(){
        List<Match> matchData = new ArrayList<>();
        try{
            Connection con = Connector.getConnection();
            String query = "select * from match";
            Statement stmt = con.createStatement();
            ResultSet deliverySet = stmt.executeQuery(query);

            while(deliverySet.next()){

                Match match = new Match();
                match.setId(deliverySet.getString(ID));
                match.setSeason(deliverySet.getString(SEASON));
                match.setCity(deliverySet.getString(CITY));
                match.setDate(deliverySet.getString(DATE));
                match.setPlayerOfMatch(deliverySet.getString(PLAYER_OF_MATCH));
                match.setResult(deliverySet.getString(RESULT));
                match.setTeam1(deliverySet.getString(TEAM1));
                match.setTeam2(deliverySet.getString(TEAM2));
                match.setTossWinner(deliverySet.getString(TOSS_WINNER));
                match.setTossDicision(deliverySet.getString(TOSS_DECISION));
                match.setDlApplied(deliverySet.getString(DL_APPLIED));
                match.setWinner(deliverySet.getString(WINNER));
                match.setWinByRuns(deliverySet.getString(WIN_BY_RUNS));
                match.setWinByWickets(deliverySet.getString(WIN_BY_WICKETS));
                match.setPlayerOfMatch(deliverySet.getString(PLAYER_OF_MATCH));
                match.setVenue(deliverySet.getString(VENUE));
                if(deliverySet.getString(UMPIRE1) != null)match.setUmpire1(deliverySet.getString(UMPIRE1));
                else match.setUmpire1("None");
                if(deliverySet.getString(UMPIRE2) != null)match.setUmpire2(deliverySet.getString(UMPIRE2));
                else match.setUmpire2("None");
                if(deliverySet.getString(UMPIRE3) != null)match.setUmpire3(deliverySet.getString(UMPIRE3));
                else match.setUmpire3("None");
                matchData.add(match);
            }
        }catch (Exception e){
            System.out.println("There are some exception "+e);
        }

        return matchData;
    }
}

