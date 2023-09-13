package prac.main;

import prac.dao.MatchReader;
import prac.pojo.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchesRelatedQuery{


    public static Map<Integer,Integer> getNumberOfMatchesPlayedPerYear(){
        Map<Integer,Integer> matchesPlayedPerYear = new HashMap<>();

        MatchReader reader = new MatchReader();
        List<Match> matchData = reader.getMatchData();

        for(Match match : matchData){

            if(!match.getId().equals("id")) {
                //get all the matches played by a team over all the years
                int season = Integer.parseInt(match.getSeason());
                matchesPlayedPerYear.put(season, matchesPlayedPerYear.getOrDefault(season, 0) + 1);
            }
        }

        return matchesPlayedPerYear;
    }

    public static Map<String,Integer> getNumberOfMatchesOwnAllTeamsPerYear(){
        Map<String,Integer> matchesOwnAllTeamsPerYear = new HashMap<>();

        MatchReader reader = new MatchReader();
        List<Match> matchData = reader.getMatchData();
        for(Match match : matchData){
            if(!match.getId().equals("id")){
                String winner = (match.getWinner() != null) ? match.getWinner() : "None";
                matchesOwnAllTeamsPerYear.put(winner,matchesOwnAllTeamsPerYear.getOrDefault(winner,0)+1);
            }
        }

        return matchesOwnAllTeamsPerYear;
    }

    public static List<Integer> getListOfAllMatchesInYear(int year){
        List<Integer> allMatchesInYear = new ArrayList<>();

        MatchReader reader = new MatchReader();
        List<Match> matchData = reader.getMatchData();
        for(Match match : matchData){
            if(!match.getId().equals("id")){

                int season = Integer.parseInt(match.getSeason());

                if(season == year){
                    allMatchesInYear.add(Integer.parseInt(match.getId()));

                }
            }
        }

        return allMatchesInYear;
    }

    public static List<String> getVenueSHOwnBattingFirst(){
        String venue ="";
        MatchReader reader = new MatchReader();
        HashMap<String,Integer> venues = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();
        List<Match> matchData = reader.getMatchData();
        for(Match match : matchData){
            if(!match.getWinByWickets().equals("0")){
                venues.put(match.getWinner(),venues.getOrDefault(match.getWinner(),0)+1);
            }
        }
        for(String ven:venues.keySet()){
            if(venues.get(ven) > 3){
                result.add(ven);
            }
        }
        return result;
    }

}


