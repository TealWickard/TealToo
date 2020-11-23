public class Player implements Comparable<Player>{
    String name;
    double crewELO;
    double impELO;
    int crewWins;
    int impWins;
    int crewLosses;
    int impLosses;
    int id;
    public Player(String name, int id){
        this.name = name;
        this.crewELO = 700;
        this.impELO = 800;
        this.crewWins = 0;
        this.impWins = 0;
        this.crewLosses = 0;
        this.impLosses = 0;
    }
    public double getCrewELO(){
        return crewELO;
    }
    public double getImpELO(){
        return impELO;
    }
    public String getName(){
        return name;
    }
    public void setCrewELO(double newELO){
        crewELO = newELO;
    }
    public void setImpELO(double newELO){
        impELO = newELO;
    }


    @Override
    public int compareTo(Player otherPlayer) {
        if(otherPlayer.getCrewELO() + otherPlayer.getImpELO() == crewELO + impELO){
            return 0;
        }
        if(otherPlayer.getCrewELO() + otherPlayer.getImpELO() > crewELO + impELO){
            return 1;
        }
        return -1;
    }
}
