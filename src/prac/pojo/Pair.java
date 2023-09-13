package prac.pojo;

public class Pair implements Comparable<Pair>{
    public String bowlerName;
    public double economy;
    public Pair(String bowlerName,double economy){
        this.bowlerName=bowlerName;
        this.economy=economy;
    }


    @Override
    public int compareTo(Pair o) {
        return Double.compare(this.economy ,o.economy);
    }
}
