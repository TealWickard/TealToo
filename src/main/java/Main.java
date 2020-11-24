import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException, ClassNotFoundException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(Constants.TOKEN);
        builder.addEventListeners(new Main(), new Dice(), new Misc(), new PuzzleHunt(), new Perversity());
        builder.build();
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("received message: " + event.getMessage().getContentRaw());
    }

}