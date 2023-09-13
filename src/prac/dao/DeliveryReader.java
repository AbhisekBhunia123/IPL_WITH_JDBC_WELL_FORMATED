package prac.dao;

import prac.con.Connector;
import prac.pojo.Delivery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryReader {
    public static final int MATCH_ID = 1;
    public static final int INNING=2;
    public static final int BATTING_TEAM=3;
    public static final int BOWLING_TEAM=4;
    public static final int OVER=5;
    public static final int BALL=6;
    public static final int BATSMAN=7;
    public static final int NON_STRIKER=8;
    public static final int BOWLER=9;
    public static final int IS_SUPER_OVER=10;
    public static final int WIDE_RUNS=11;
    public static final int BYE_RUNS=12;
    public static final int LEGBYE_RUNS=13;
    public static final int NOBALL_RUNS=14;
    public static final int PENALTY_RUNS=15;
    public static final int BATSMAN_RUNS=16;
    public static final int EXTRA_RUNS=17;
    public static final int TOTAL_RUNS=18;
    public static final int PLAYER_DISMISSED=19;
    public static final int DISMISSAL_KIND=20;
    public static final int FIELDER=21;

    public List<Delivery> getDeliveryData(){
        List<Delivery> deliveryData = new ArrayList<>();
        try{
            Connection con = Connector.getConnection();
            String query = "select * from delivery";
            Statement stmt = con.createStatement();
            ResultSet matchSet = stmt.executeQuery(query);

            while(matchSet.next()){

                Delivery delivery = new Delivery();
                delivery.setMatchId(matchSet.getString(MATCH_ID));
                delivery.setInning(matchSet.getString(INNING));
                delivery.setBattingTeam(matchSet.getString(BATTING_TEAM));
                delivery.setBowlingTeam(matchSet.getString(BOWLING_TEAM));
                delivery.setOver(matchSet.getString(OVER));
                delivery.setBall(matchSet.getString(BALL));
                delivery.setBatsman(matchSet.getString(BATSMAN));
                delivery.setNonStriker(matchSet.getString(NON_STRIKER));
                delivery.setBowler(matchSet.getString(BOWLER));
                delivery.setIsSuperOver(matchSet.getString(IS_SUPER_OVER));
                delivery.setWideRuns(matchSet.getString(WIDE_RUNS));
                delivery.setByeRuns(matchSet.getString(BYE_RUNS));
                delivery.setLegbyeRuns(matchSet.getString(LEGBYE_RUNS));
                delivery.setNoballRuns(matchSet.getString(NOBALL_RUNS));
                delivery.setPenaltyRuns(matchSet.getString(PENALTY_RUNS));
                delivery.setBatsmanRuns(matchSet.getString(BATSMAN_RUNS));
                delivery.setExtraRuns(matchSet.getString(EXTRA_RUNS));
                delivery.setTotalRuns(matchSet.getString(TOTAL_RUNS));
                if(matchSet.getString(PLAYER_DISMISSED) != null)delivery.setPlayerDismissed(matchSet.getString(PLAYER_DISMISSED));
                else delivery.setPlayerDismissed("None");
                if(matchSet.getString(DISMISSAL_KIND) != null)delivery.setDismissalKind(matchSet.getString(DISMISSAL_KIND));
                else delivery.setDismissalKind("None");
                if(matchSet.getString(FIELDER) != null)delivery.setFielder(matchSet.getString(FIELDER));
                else delivery.setFielder("None");
                deliveryData.add(delivery);
            }
        }catch (Exception e){
            System.out.println("There are some exception ");
        }
        return deliveryData;
    }
}

