import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://opendata.citywindsor.ca/").get();

        System.out.printf("Page: %s\n", doc.title());

        // select all the cards and print their titles
        Elements cards = doc.select("#opendata-container div div div h4");
        for (Element cardTitle : cards) {
            System.out.println(cardTitle.text());
        }

        /*// read file from https://opendata.citywindsor.ca/Uploads/Schools.csv and save to schools.csv
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
        new LoadData("schools.csv");*/
    }
}
