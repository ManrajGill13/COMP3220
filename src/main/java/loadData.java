import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class loadData
{
    public loadData()
    {
        try (InputStream inputStream = Main.class.getResourceAsStream("Schools.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            new Data(reader);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
