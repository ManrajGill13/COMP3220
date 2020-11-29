import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException {

        // connect to the opendata site to scrape it
        Document doc = Jsoup.connect("https://opendata.citywindsor.ca/").get();

        System.out.printf("Page: %s\n", doc.title());

        // select all the cards and print their titles
        Elements cards = doc.select("#opendata-container div div div");
        for (Element card : cards) {

            // print out the card title
            System.out.print(card.getElementsByClass("card-title").text());

            // get all the files in the footer
            Elements files = card.select(".card-footer i");
            for (Element file : files) {

                String title = file.attr("title");

                if (title.toLowerCase().contains("show details")) {
                    continue;
                }

                System.out.printf("\t -> %s\n", file.attr("title"));

                // TODO: download each file -> https://opendata.citywindsor.ca/Uploads/${file.attr("title")}
            }
            System.out.println();
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
