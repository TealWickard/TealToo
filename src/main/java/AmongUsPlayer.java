public class AmongUsPlayer implements Comparable<AmongUsPlayer>{
    String name;
    double crewELO;
    double impELO;
    int crewWins;
    int impWins;
    int crewLosses;
    int impLosses;
    int id;

    public AmongUsPlayer(String name, int id){
        this.name = name;
        this.crewELO = 800;
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
    
    public int getCrewWins() {
        return this.crewWins;
    }

    public void addCrewWin() {
        this.crewWins++;
    }

    public int getImpWins() {
        return this.impWins;
    }

    public void addImpWin() {
        this.impWins++;
    }

    public int getCrewLosses() {
        return this.crewLosses;
    }

    public void addCrewLoss() {
        this.crewLosses++;
    }

    public int getImpLosses() {
        return this.impLosses;
    }

    public void addImpLoss() {
        this.impLosses++;
    }

    @Override
    public int compareTo(AmongUsPlayer otherPlayer) {
        if(otherPlayer.getCrewELO() + otherPlayer.getImpELO() == crewELO + impELO){
            return 0;
        }
        if(otherPlayer.getCrewELO() + otherPlayer.getImpELO() > crewELO + impELO){
            return 1;
        }
        return -1;
    }
}
