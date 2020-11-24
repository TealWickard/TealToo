import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int points;
    private String id;
    public Player(String name, String id){
        this.name = name;
        this.points = 25;
    }

    public void awardPoints(int quantity){
        points += quantity;
    }

    public String getName(){
        return name;
    }

    public int getPoints(){
        return points;
    }
}
