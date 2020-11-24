import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Misc extends NewListener{
    public Misc(){
        super();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        try{
            if (message.equals("get in vc computer") && event.getChannel().getId().equals("743555116212158545"))
                event.getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
            if (message.equals("good bot")) event.getChannel().sendMessage("<:praiser:651881187610198025>").queue();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void sass(MessageReceivedEvent e){
        e.getChannel().sendMessage("Nice Try.").queue();
    }
}
