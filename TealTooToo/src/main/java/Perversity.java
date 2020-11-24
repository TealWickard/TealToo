import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.ArrayList;

public class Perversity extends NewListener {
    ArrayList<Player> players;
    public Perversity() throws IOException, ClassNotFoundException {
        super();
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("perversity.txt"));
            players = (ArrayList<Player>) in.readObject();
            in.close();
        }catch (Exception e){
            players = new ArrayList<>();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if(event.getAuthor().isBot())return;
        if(!guildId.equals("740640805018009612")) return;
        String[] args = caseMessage.split(" ");

        if(args[0].equals("/give")){
            if(!memberId.equals("388921852694757388")){
                Misc.sass(event);
                return;
            }
            for(Player p : players){
                if(p.getName().toLowerCase().equals(args[1].toLowerCase())){
                    p.awardPoints(Integer.parseInt(args[2]));
                    event.getChannel().sendMessage((p.getName() + " was given " + Integer.parseInt(args[2]) + " points. They now have " + p.getPoints() + " points total.")).queue();
                    try {
                        save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(args[0].equals("/register")){
            if(!memberId.equals("388921852694757388")){
                Misc.sass(event);
                return;
            }
            players.add(new Player(args[1], args[2]));
            try {
                save();
                event.getChannel().sendMessage("Added new player: " + args[1]).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(args[0].equals("/bal")){
            try{
                for(Player p : players){
                    if(p.getName().toLowerCase().equals(args[1].toLowerCase())){
                        event.getChannel().sendMessage(p.getName() + " has " + p.getPoints() + " perversity points.").queue();
                        return;
                    }
                }
                event.getChannel().sendMessage("Could not find a player with the name: " + args[1]).queue();
            } catch (Exception e){
                event.getChannel().sendMessage("Command didn't work or something. Teal doesn't like doing proper error handling, ok?");
            }
        }
    }

    public void save() throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("perversity.txt"));
        output.writeObject(players);
        output.close();
    }
}
