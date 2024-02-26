import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PokemonRunner2 {
    public static void main(String[] args) {
        String csvFile = "player_stats.csv";
        String line;
        String csvSplitBy = ",";

        Map<String, List<String[]>> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] values = line.split(csvSplitBy);
                // System.out.print(Arrays.toString(values));

                // store the line in the map, using the second element as the key
                if (values.length > 1) {
                    String key = values[11];
                    String key2 = values[1];
                    String key3 = values[7];
                    // System.out.println(key);
                    // key = key.replaceAll("\\p{Punct}", "");
                    // System.out.println(key);
                    if (!map.containsKey(key)) {
                        map.put(key, new ArrayList<>());
                    }
                    if (!map.containsKey(key2)) {
                        map.put(key2, new ArrayList<>());
                    }
                    if (!map.containsKey(key3)) {
                        map.put(key3, new ArrayList<>());
                    }
                    map.get(key).add(values);
                    map.get(key2).add(values);
                    map.get(key3).add(values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ask the user for a key in a loop
        Scanner scanner = new Scanner(System.in);
        // while (true) {
        System.out.println("Enter the number of goals - (or Control-C to exit):");
        String userKey = scanner.nextLine();

        // print the values for the user's key
        if (map.containsKey(userKey)) {
            for (String[] value : map.get(userKey)) {
                System.out.println((value[0]));
            }
        } else {
            System.out.println("No values for key: " + userKey);
        }

        System.out.println("Enter the country - (or Control-C to exit):");
        String userKey2 = scanner.nextLine();
        int citizens = 0;

        if (map.containsKey(userKey2)) {
            for (String[] value2 : map.get(userKey2)) {
                citizens++;
            }

        } else {
            System.out.println("No values for key: " + userKey);
        }
        System.out.println("There are " + citizens + " players from that country.");

        int minGames = Integer.MAX_VALUE;
        int maxGames = Integer.MIN_VALUE;

    }
}