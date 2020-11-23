public class Player {
    String name;
    double crewELO;
    double impELO;
    int id;
    public Player(String name, int id){
        this.name = name;
        this.crewELO = 1000;
        this.impELO = 800;
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
}
