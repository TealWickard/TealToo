import java.io.Serializable;
import java.util.ArrayList;

public class Entry implements Serializable {
    private String name;
    private ArrayList<String> definitions;

    public Entry(String name) {
        this(name, "");
    }


    public Entry(String name, String def) {
        definitions = new ArrayList<String>();
        definitions.add(def);
        this.name = name;
    }

    public void addDefinition(String toAdd) {
        definitions.add(toAdd);
    }

    public String getDefinition() {
        return this.definitions.get(definitions.size() - 1);
    }

    public String getName() {
        return this.name;
    }

    public String getOld(int index) throws IndexOutOfBoundsException {
        try {
            return definitions.get(index);
        } catch (IndexOutOfBoundsException e) {
            return "Index " + index + " does not exist.";
        }
    }

    public String listAll() {
        String toReturn = "";
        for (int i = 0; i < definitions.size(); i++) {
            toReturn += i + ": " + definitions.get(i) + "\n";
        }
        return toReturn;
    }
}