import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadData {
    public LoadData(String path) {
        try (InputStream inputStream = Main.class.getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            new Data(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
