package prac.main;

import prac.pojo.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter 1 for check Number of matches played per year of all the years in IPL ==>");
        System.out.println("Enter 2 for check Number of matches won of all teams over all the years of IPL. ==>");
        System.out.println("Enter 3 for check For the year 2016 get the extra runs conceded per team. ==>");
        System.out.println("Enter 4 for check For the year 2015 get the top economical bowlers. ==>");
        System.out.println("Enter 5 for find the players who hit most number of 4's in 2016 by team ==>");

        int query=input.nextInt();
        switch (query) {
            case 1 -> System.out.println(MatchesRelatedQuery.getNumberOfMatchesPlayedPerYear());
            case 2 -> System.out.println(MatchesRelatedQuery.getNumberOfMatchesOwnAllTeamsPerYear());
            case 3 -> System.out.println(DeliveryRelatedQuery.getListOfTeamsConcededExtraRun(2016));
            case 4 -> {
                List<Pair> list = (DeliveryRelatedQuery.getEconomicalBowlers(2015));
                for (Pair p : list) System.out.print("( " + p.bowlerName + " => " + p.economy + " ) ");
            }
            case 5 -> {
                Map<String, Map<String,Integer>> result = DeliveryRelatedQuery.getPlayersMakeHighest4sInYear(2016);
                Map<String,Map<String,Integer>> filterResult = new HashMap<>();
                for(String singleItem : result.keySet()){
                    String player = "";
                    int numberOf4 = 0;
                    for(String singlePlayer:result.get(singleItem).keySet()){
                        if(numberOf4 < result.get(singleItem).get(singlePlayer)){
                            player = singlePlayer;
                            numberOf4 = result.get(singleItem).get(singlePlayer);
                        }
                    }
                    Map<String,Integer> playerWith4Count = new HashMap<>();
                    playerWith4Count.put(player,numberOf4);
                    filterResult.put(singleItem,playerWith4Count);
                }
                for(String team : filterResult.keySet()){
                    System.out.println(team);
                    System.out.println(filterResult.get(team));
                }
//                for(String singleTeam : result.keySet()){
//                    System.out.println(singleTeam);
//                    System.out.println(result.get(singleTeam));
//                }


            }
            case 6 -> System.out.println(MatchesRelatedQuery.getVenueSHOwnBattingFirst());
            default -> System.out.println("There is no query available!!!");
        }

    }
}