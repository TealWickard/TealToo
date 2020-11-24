import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.io.*;
import java.util.ArrayList;

public class Wiki extends NewListener {
    private static ArrayList<String> entries;

    public Wiki() throws IOException, ClassNotFoundException {
        super();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("entries.txt"));
        in.close();
        entries = new ArrayList<String>();
        entries = (ArrayList<String>) in.readObject();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if(event.getAuthor().isBot())return;
        if(!event.getGuild().getId().equals("695237765746524220")) return;
        if(message.length() > 0 && message.charAt(0) == '/'){
            String[] args = message.substring(1).toLowerCase().split(" ");
            if(args.length == 0) {
                event.getChannel().sendMessage("Uhhh").queue();
            }
            int indexStart;
            int indexEnd;
            String name;
            switch(args[0]){
                case "add":
                case "a":
                    // For the time being, going to require both arguments to be encased in quotation marks
                    // TODO Allow quotation marks within the definition
                    name = "";
                    String definition = "";
                    indexStart = 0;
                    indexEnd = 0;
                    int check4 = 0;
                    for(int i = 2; i < caseMessage.length(); i++){
                        if(caseMessage.charAt(i) == '"'){
                            check4++;
                            switch(check4){
                                case 1:
                                case 3:
                                    indexStart = i;
                                    break;
                                case 2:
                                    indexEnd = i;
                                    name = caseMessage.substring(indexStart + 1, indexEnd);
                                case 4:
                                    indexEnd = i;
                                    definition = caseMessage.substring(indexStart + 1, indexEnd);
                            }
                        }
                    }
                    System.out.println("Adding: " + name + " with definition: " + definition);
                    if(check4 != 4 || name.length() > 255){
                        event.getChannel().sendMessage("Uhhh").queue();
                        break;
                    }

                    if(name.contains("\\") || name.contains("/") || name.contains("?") || name.contains("%") || name.contains("*") || name.contains(":") || name.contains("|") || name.contains("<") || name.contains(">") || name.contains(".")){
                        event.getChannel().sendMessage("Illegal Character in the wiki name. If this is a big issue, ping Teal and they'll maybe fix it idk").queue();
                        break;
                    }

                    if(entries.contains(name.toLowerCase())){
                        Entry currentEntry = null;
                        try {
                            currentEntry = findEntry(name.toLowerCase());
                            currentEntry.addDefinition(definition);
                            saveEntry(currentEntry);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Entry newEntry = new Entry(name, definition);
                        try {
                            saveEntry(newEntry);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "r":
                case "read":
                    //TODO Make this into its own method or smth
                    indexStart = caseMessage.indexOf(' ') + 1;
                    if(caseMessage.charAt(indexStart) == '"') indexStart++;
                    indexEnd = caseMessage.length();
                    if(caseMessage.charAt(indexEnd - 1) == '"') indexEnd--;
                    if(indexEnd - indexStart < 0){
                        event.getChannel().sendMessage("Uhhh").queue();
                        break;
                    }
                    name = caseMessage.substring(indexStart, indexEnd);
                    if(entries.contains(name.toLowerCase())){
                        Entry current = null;
                        try {
                            current = findEntry(name);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        event.getChannel().sendMessage(current.getDefinition()).queue();
                    } else {
                        event.getChannel().sendMessage("Could not find an entry with the name: " + name).queue();
                    }
                    break;
                case "l":
                case "list":
                    indexStart = caseMessage.indexOf(' ') + 1;
                    if(caseMessage.charAt(indexStart) == '"') indexStart++;
                    indexEnd = caseMessage.length();
                    if(caseMessage.charAt(indexEnd - 1) == '"') indexEnd--;
                    if(indexEnd - indexStart < 0){
                        event.getChannel().sendMessage("Uhhh").queue();
                        break;
                    }
                    name = caseMessage.substring(indexStart, indexEnd);
                    if(entries.contains(name.toLowerCase())){
                        Entry current = null;
                        try {
                            current = findEntry(name);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        event.getChannel().sendMessage(current.listAll()).queue();
                    } else {
                        event.getChannel().sendMessage("Could not find an entry with the name: " + name).queue();
                    }
                default:
                    break;
            }
        }
    }

    public Entry findEntry(String name) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("wikiEntries//" + name + ".txt"));
        Entry toReturn = (Entry) input.readObject();
        input.close();
        return toReturn;
    }

    public void saveEntry(Entry editedEntry) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("wikiEntries//" + editedEntry.getName().toLowerCase() + ".txt"));
        output.writeObject(editedEntry);
        if(!entries.contains(editedEntry.getName().toLowerCase())){
            entries.add(editedEntry.getName().toLowerCase());
            ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("entries.txt"));
            output2.writeObject(entries);
            output2.close();
        }
        output.close();
    }
}
