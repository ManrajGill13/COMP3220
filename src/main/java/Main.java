import connection.Connection;
import connection.ConnectionBuilder;
import connection.exception.ConnectionBuildException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static spark.Spark.get;

public class Main {

    public static Connection DB_CONNECTION;

    public static void main(String[] args) throws IOException, ConnectionBuildException {

        // building the connection
        DB_CONNECTION = new ConnectionBuilder()
                .setIp("192.168.2.91")
                .setPort(15896)
                .setDatabase("windsor")
                .setUser("root")
                .setPassword("amBWZSCnZfGLe9U8ZKCMDQEXrxRCCT")
                .build();

        get("/hello", (req, res) -> "Hello World");

        //scrapeCardsOnWindsorSite();
        //downloadFileAndLoadIntoDatabase();

        // closing the connection to database
        DB_CONNECTION.close();
    }

    private static void scrapeCardsOnWindsorSite() throws IOException {
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
    }

    private static void downloadFileAndLoadIntoDatabase() {
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
