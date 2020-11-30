import java.io.*;
import java.net.URL;

public class loadData
{
    public static void retrieveData(String url, String doc){
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOS = new FileOutputStream(doc))
        {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1)
            {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
