import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("Test/data.txt"));
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> currentPlayers = new ArrayList<>();
        TreeMap<String, Player> names = new TreeMap<>();
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
                    if(argNames[0].equals("all")){
                        currentPlayers = new ArrayList<String>();
                    } else {
                        for(String name : argNames){
                            currentPlayers.remove(name);
                        }
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
                        Player current = names.get(name);
                        if(argNames[0].equals(name) || argNames[1].equals(name)){
                            double expectedValue = 1 / (1 + Math.pow(10, (crewELO - impELO) / 400));
                            if(curr.charAt(0) == 'W'){
                                current.addImpWin();
                                current.setImpELO(current.getImpELO() + 32 * (1 - expectedValue));
                            } else {
                                current.addImpLoss();
                                current.setImpELO(current.getImpELO() - 32 * (expectedValue));
                            }
                        } else {
                            double expectedValue = 1 / (1 + Math.pow(10, (impELO - crewELO) / 400));
                            if(curr.charAt(0) == 'L'){
                                current.addCrewWin();
                                current.setCrewELO(current.getCrewELO() + 32 * (1 - expectedValue));
                            } else {
                                current.addCrewLoss();
                                current.setCrewELO(current.getCrewELO() - 32 * (expectedValue));
                            }
                        }
                    }
                break;
            }
        }
        ArrayList<Player> sorted = new ArrayList<>();
        for(String name : names.keySet()){
            sorted.add(names.get(name));
        }
        Collections.sort(sorted);
        for(Player player : sorted){
            int crewELO = (int)Math.round(player.getCrewELO());
            int impELO = (int)Math.round(player.getImpELO());
            int ELO = (crewELO + impELO) / 2;
            String buffer = "";
            for(int i = 0; i < 9 - player.getName().length(); i++){
                buffer += " ";
            }
            System.out.printf("%s: %sELO: %d, crew: %d (%d-%d), imposter, %d (%d-%d)\n", player.getName(), buffer, ELO, crewELO, player.getCrewWins(), player.getCrewLosses(), impELO, player.getImpWins(), player.getImpLosses());
        }
    }
}