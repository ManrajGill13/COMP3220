import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        try (InputStream inputStream = Main.class.getResourceAsStream("Schools.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            new Data(reader);
        }
    }
}
