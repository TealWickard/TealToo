public class Main {
    public static void main(String[] args) throws LoginException, IOException, ClassNotFoundException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(Constants.TOKEN);
        builder.addEventListeners();
        builder.build();
    }
}
