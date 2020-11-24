import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PuzzleHunt extends NewListener {
    public void onMessageReceived(MessageReceivedEvent event){
        super.onMessageReceived(event);
        if(event.getAuthor().isBot())return;
        String guildString = "";
        try {
            guildString = event.getGuild().getId();
        }catch(Exception e){
            System.out.println("Received DM");
        }
    }


}
