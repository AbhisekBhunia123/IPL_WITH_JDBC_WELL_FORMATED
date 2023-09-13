package prac.main;

import prac.dao.DeliveryReader;
import prac.pojo.Delivery;
import prac.pojo.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DeliveryRelatedQuery{


    public static Map<String,Integer> getListOfTeamsConcededExtraRun(int year){
        Map<String,Integer> teamsConcededExtraRun = new HashMap<>();
        List<Integer> allMatches  = MatchesRelatedQuery.getListOfAllMatchesInYear(year);
        DeliveryReader reader = new DeliveryReader();
        List<Delivery> deliveryData = reader.getDeliveryData();

        for(Delivery delivery : deliveryData){
            if(!delivery.getMatchId().equals("match_id") && allMatches.contains(Integer.parseInt(delivery.getMatchId()))){
                teamsConcededExtraRun.put(delivery.getBattingTeam(),teamsConcededExtraRun.getOrDefault(delivery.getBattingTeam(),0)+Integer.parseInt(delivery.getExtraRuns()));
            }
        }

        return teamsConcededExtraRun;
    }

    public static List<Pair> getEconomicalBowlers(int year) {
        List<Pair> economicalBowlersInYear = new ArrayList<>();
        List<Integer> allMatches  = MatchesRelatedQuery.getListOfAllMatchesInYear(year);
        DeliveryReader reader = new DeliveryReader();
        List<Delivery> deliveryData = reader.getDeliveryData();
        Map<String,Integer> allBowlerGaveRun = new HashMap<>();
        Map<String,Integer> allBowlerTotalBalls = new HashMap<>();

        for(Delivery delivery : deliveryData){
            if(!delivery.getMatchId().equals("match_id") && allMatches.contains(Integer.parseInt(delivery.getMatchId()))){
                if(Integer.parseInt(delivery.getByeRuns()) == 0 && Integer.parseInt(delivery.getLegbyeRuns()) == 0) allBowlerGaveRun.put(delivery.getBowler(),allBowlerGaveRun.getOrDefault(delivery.getBowler(),0)+Integer.parseInt(delivery.getTotalRuns()));
                if(Integer.parseInt(delivery.getWideRuns()) == 0 && Integer.parseInt(delivery.getNoballRuns()) == 0)allBowlerTotalBalls.put(delivery.getBowler(),allBowlerTotalBalls.getOrDefault(delivery.getBowler(),0)+1);
            }

        }

        for (String player : allBowlerGaveRun.keySet()) {
            if((allBowlerTotalBalls.get(player) / 6d) > 0d ) economicalBowlersInYear.add(new Pair(player,(double) (allBowlerGaveRun.get(player) / (allBowlerTotalBalls.get(player) / 6d))));
        }

        Collections.sort(economicalBowlersInYear);
        return economicalBowlersInYear;
    }

    public static Map<String,Map<String,Integer>> getPlayersMakeHighest4sInYear(int year){
        Map<String,Map<String,Integer>> playersMakeHeighest4s = new HashMap<>();
        List<Integer> allMatches  = MatchesRelatedQuery.getListOfAllMatchesInYear(year);
        DeliveryReader reader = new DeliveryReader();
        List<Delivery> deliveryData = reader.getDeliveryData();
        for(Delivery delivery : deliveryData){
            if(!delivery.getMatchId().equals("match_id") && allMatches.contains(Integer.parseInt(delivery.getMatchId()))){
                if(delivery.getBatsmanRuns().equals("4")){
                    if(!playersMakeHeighest4s.containsKey(delivery.getBattingTeam())){
                        Map<String,Integer> perBatsMan = new HashMap<>();
                        perBatsMan.put(delivery.getBatsman(),perBatsMan.getOrDefault(delivery.getBatsman(),0)+1);
                        playersMakeHeighest4s.put(delivery.getBattingTeam(),perBatsMan);
                    }else{
                        Map<String,Integer> perBatsMan = playersMakeHeighest4s.get(delivery.getBattingTeam());
                        perBatsMan.put(delivery.getBatsman(),perBatsMan.getOrDefault(delivery.getBatsman(),0)+1);
                        playersMakeHeighest4s.put(delivery.getBattingTeam(),perBatsMan);
                    }
                }
            }
        }

        return playersMakeHeighest4s;
    }


}

