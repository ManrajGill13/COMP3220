import java.io.*;
import java.net.URL;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        // read file from https://opendata.citywindsor.ca/Uploads/Schools.csv and save to schools.csv
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL("https://opendata.citywindsor.ca/Uploads/Schools.csv").openStream());
             FileOutputStream fileOS = new FileOutputStream("src/main/resources/schools.csv")) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read schools.csv and insert data into schools table
        new LoadData("schools.csv");
    }
}
