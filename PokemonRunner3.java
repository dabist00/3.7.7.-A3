import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PokemonRunner3 {
    public static void main(String[] args) {

        String csvFile = "player_stats.csv";
        String line;
        String csvSplitBy = ",";

        Map<String, List<String>> countryToPlayers = new HashMap<>();
        Map<Integer, List<String>> goalsToPlayers = new HashMap<>();

        boolean firstLine = true;
        int maxGoals = Integer.MIN_VALUE;
        int minGoals = Integer.MAX_VALUE;
        List<String> playerAppearances = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                if (firstLine) {

                    firstLine = false;
                    continue;
                }

                if (values.length > 1) {

                    String countryName = values[1];
                    String playerName = values[0];
                    String goalsString = values[11].replaceAll("\\p{Punct}", "");
                    String appearances = values[7];

                    try {
                        int goals = Integer.parseInt(goalsString);
                        if ((goals > maxGoals) && (countryName.equals("USA"))) {
                            maxGoals = goals;
                        } else if ((goals < minGoals) && (countryName.equals("USA"))) {
                            minGoals = goals;
                        }

                        if (!goalsToPlayers.containsKey(goals)) {
                            goalsToPlayers.put(goals, new ArrayList<>());
                        }
                        List<String> playersFromGoals = goalsToPlayers.get(goals);
                        playersFromGoals.add(playerName);

                        if (!countryToPlayers.containsKey(countryName)) {
                            countryToPlayers.put(countryName, new ArrayList<>());
                        }
                        List<String> playersFromCountry = countryToPlayers.get(countryName);
                        playersFromCountry.add(playerName);

                        if (countryName.equals("USA")) {
                            playerAppearances.add(playerName + "had " + appearances + " appearances.");
                        }

                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of goals - (or Control-C to exit):");
        Integer goalsKey = scanner.nextInt();

        if (goalsToPlayers.containsKey(goalsKey)) {
            for (String value : goalsToPlayers.get(goalsKey)) {
                System.out.println((value));
            }
        } else {
            System.out.println("No values for key: " + goalsKey);
        }

        System.out.println("Enter the country - (or Control-C to exit):");
        scanner.nextLine();
        String countryKey = scanner.nextLine();

        System.out.println("country Key is: " + countryKey);
        int citizens = 0;

        if (countryToPlayers.containsKey(countryKey)) {
            for (String value2 : countryToPlayers.get(countryKey)) {

                citizens++;
            }

        } else {
            System.out.println("No values for key: " + countryKey);
        }
        System.out.println("There are " + citizens + " players from that country.");

        System.out.println("Lowest Goals from the USA is: " + minGoals);
        System.out.println("Highest Goals from the USA is: " + maxGoals);
        for (String phrase : playerAppearances) {
            System.out.println(phrase);
        }

    }
}
