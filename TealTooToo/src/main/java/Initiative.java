import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Initiative extends NewListener{
    boolean rollingInitiative;
    public Initiative(){
        super();
        rollingInitiative = false;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(true)return;
        super.onMessageReceived(event);
        String caseMessage = event.getMessage().getContentRaw();
        String message = caseMessage.toLowerCase();
        if (message.length() > 3 && message.substring(0, 3).equals("/i ")) {
            if (event.getAuthor().getId().equals("388921852694757388")) {
                if (message.contains("start") && !rollingInitiative) {
                    rollingInitiative = true;
                    event.getChannel().sendMessage("Everyone roll initiative! <:roll_iniative:655025956133666826>").queue();
                    return;
                }
            } else {
                if (rollingInitiative) {

                } else {
                    event.getChannel().sendMessage("We ain't rolling initiative right now, buddy.").queue();
                }
            }
        }
    }
}
