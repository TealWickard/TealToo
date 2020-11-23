import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("Test/data.txt"));
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> currentPlayers = new ArrayList<>();
        HashMap<String, Player> names = new HashMap<>();
        while(scan.hasNextLine()){
            String curr = scan.nextLine();
            String[] argNames = curr.substring(3).split(",");
            for(int i = 0; i < argNames.length; i++){
                argNames[i] = argNames[i].toLowerCase();
            }
            switch(curr.charAt(0)){
                case 'A':
                    for(String name : argNames){
                        if(!names.containsKey(name)){
                            Player newPlayer = new Player(name, 3);
                            players.add(newPlayer);
                            names.put(name, newPlayer);
                        }
                        currentPlayers.add(name);
                    }
                break;
                case 'R':
                    for(String name : argNames){
                        currentPlayers.remove(name);
                    }
                break;
                case 'W':
                case 'L':
                    if(currentPlayers.size() != 10) {
                        System.out.println("Not enough players, shit's fucked. This is on line: " + curr);
                        break;
                    }
                    double crewELO = 0;
                    double impELO = 0;
                    for(String name : currentPlayers){
                        if(argNames[0].equals(name) || argNames[1].equals(name)){
                            impELO += names.get(name).getImpELO();
                        } else {
                            crewELO += names.get(name).getCrewELO();
                        }
                    }
                    crewELO /= 8;
                    impELO /= 2;
                    for(String name : currentPlayers){
                        if(argNames[0].equals(name) || argNames[1].equals(name)){
                            double elo = names.get(name).getImpELO();
                            double expectedValue = 1 / (1 + Math.pow(10, (crewELO - elo) / 400));
                            if(curr.charAt(0) == 'W'){
                                names.get(name).setImpELO(elo + 32 * (1 - expectedValue));
                            } else {
                                names.get(name).setImpELO(elo - 32 * (expectedValue));
                            }
                        } else {
                            double elo = names.get(name).getCrewELO();
                            double expectedValue = 1 / (1 + Math.pow(10, (impELO - elo) / 400));
                            if(curr.charAt(0) == 'W'){
                                names.get(name).setCrewELO(elo + 32 * (1 - expectedValue));
                            } else {
                                names.get(name).setCrewELO(elo - 32 * (expectedValue));
                            }
                        }
                    }
                break;
            }
        }
        for(String name : names.keySet()){
            System.out.println(name + ": " + (names.get(name).getCrewELO() + names.get(name).getImpELO()) / 2);
        }
    }
}