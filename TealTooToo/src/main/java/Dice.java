import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Dice extends NewListener {
    ArrayList<String> allowedChannels;
    boolean showAverage;
    double average;
    double total;
    public Dice(){
        allowedChannels = new ArrayList<>();
        allowedChannels.add("656205919587401762");
        allowedChannels.add("654126457408454676");
        allowedChannels.add("740998440305033237");
        allowedChannels.add("740999872840466503");
        allowedChannels.add("741000124301443144");
        allowedChannels.add("741000210146131998");
        allowedChannels.add("741020810608640001");
        allowedChannels.add("741017681607786507");
        allowedChannels.add("742478397183361074");
        allowedChannels.add("743555116212158545");
        allowedChannels.add("757313338358235196");
        showAverage = false;
        average = 0;
        total = 0;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        try{
            if(!allowedChannels.contains(event.getChannel().getId())) return;
        } catch(IllegalStateException e){

        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Shit's Fucked");
            return;
        }
        super.onMessageReceived(event);
        if(event.getAuthor().isBot())return;
        String toReturn = "Rolls: ";
        System.out.println(message);
        // Shortcut to roll 1d20
        if(message.equals("r")){
            toReturn += roll(20);
            event.getChannel().sendMessage(toReturn).queue();
            return;
        }

        if(message.length() > 3 && message.substring(0,3).equals("/r ")){
            message = message.replaceAll("\\s","");
            if(message.contains("#")) message = message.substring(0, message.indexOf('#'));
            String[] args = message.substring(2).split(Pattern.quote("+"));
            for(String arg : args){
                String result = handle(arg);
                if(result != null){
                    toReturn += result;
                }else{
                    event.getChannel().sendMessage("Error on: " + arg).queue();
                    return;
                }
            }
            if(toReturn.length() > 2){
                toReturn = toReturn.substring(0, toReturn.length() - 2) + "= " + total;
                if(showAverage){
                    toReturn += ". Average was " + average + ".";
                }
            }
            event.getChannel().sendMessage(toReturn).queue();
            total = 0;
            average = 0;
        }
        if(message.equals("/toggleaverages")){
            if(showAverage){
                event.getChannel().sendMessage("I will no longer show averages").queue();
            }else{
                event.getChannel().sendMessage("I will begin showing averages").queue();
            }
            showAverage = !showAverage;
        }
    }

    public int roll(int sides){
        return (int)Math.ceil(Math.random()*sides);
    }

    public String handle(String arg){
        if(arg.equals("ad")){
            int roll1 = roll(20);
            int roll2 = roll(20);
            if(roll1 < roll2){
                int buff = roll1;
                roll1 = roll2;
                roll2 = buff;
            }
            total += roll1;
            average += 13.825;
            return "(**" + roll1 + "** ~~" + roll2 + "~~) + ";
        }
        if(arg.equals("dis")){
            int roll1 = roll(20);
            int roll2 = roll(20);
            if(roll1 > roll2){
                int buff = roll1;
                roll1 = roll2;
                roll2 = buff;
            }
            total += roll2;
            average += 7.175;
            return "(**" + roll1 + "** ~~" + roll2 + "~~) + ";
        }
        String[] nums = arg.split(Pattern.quote("d"));
        if(nums.length == 2){
            String toReturn = "";
            try{
                int num1 = Integer.parseInt(nums[0]);
                int num2 = Integer.parseInt(nums[1]);
                for(int i = 0; i < num1; i++){
                    int roll = roll(num2);
                    average += ((double)num2 + 1)/2;
                    total += roll;
                    toReturn += "**" + roll + "** + ";
                }
                return toReturn;
            }catch (Exception e){

            }
        }
        try{
            int toAdd = Integer.parseInt(arg);
            total += toAdd;
            average += toAdd;
            return toAdd + " + ";
        }catch (Exception e){
            return null;
        }
        //return null;
    }
}
