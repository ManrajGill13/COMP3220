import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        try (InputStream inputStream = Main.class.getResourceAsStream("Schools.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String row;
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(",");
                System.out.println(Arrays.toString(data));
            }
            
        }
    }
}
