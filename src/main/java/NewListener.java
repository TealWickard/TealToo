import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NewListener extends ListenerAdapter {
    String caseMessage;
    String message;
    String memberId;
    String channelId;
    String guildId;

    public NewListener(){
        super();
        caseMessage = null;
        message = null;
        memberId = null;
        channelId = null;
        guildId = null;
    }

    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("received message: " + event.getMessage().getContentRaw());
        try {
            caseMessage = event.getMessage().getContentRaw();
        }catch(Exception e) {
            System.out.println("Couldn't determine message");
        }
        try {
            message = caseMessage.toLowerCase();
        }catch(Exception e) {
            System.out.println("Couldn't determine the caseMessage");
        }
        try {
            memberId = event.getMember().getId();
        }catch(Exception e) {
            System.out.println("Couldn't determine member");
        }
        try {
            channelId = event.getChannel().getId();
        }catch(Exception e) {
            System.out.println("Couldn't determine channel");
        }
        try {
            guildId = event.getGuild().getId();
        }catch(Exception e) {
            System.out.println("Couldn't determine guild");
        }
        if(event.getAuthor().isBot())return;
    }
}
