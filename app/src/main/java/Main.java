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
import java.util.*;

public class Main {

    public static Connection DB_CONNECTION;
    private static final List<String> CARDS = new ArrayList<>();
    private static final Map<Integer, List<String>> FILES = new HashMap<>();

    public static void main(String[] args) throws ConnectionBuildException, IOException {

        // building the connection
        DB_CONNECTION = new ConnectionBuilder()
                .setIp("192.168.2.91")
                .setPort(15896)
                .setDatabase("windsor")
                .setUser("root")
                .setPassword("amBWZSCnZfGLe9U8ZKCMDQEXrxRCCT")
                .build();

        //scrapeCardsOnWindsorSite();
        //downloadFileAndLoadIntoDatabase();
    }

    private static void scrapeCardsOnWindsorSite() throws IOException {
        // connect to the opendata site to scrape it
        Document doc = Jsoup.connect("https://opendata.citywindsor.ca/").get();

        System.out.printf("Page: %s\n", doc.title());

        // select all the cards and print their titles
        for (Element card : doc.select("#opendata-container div div div")) {
            String title = card.getElementsByClass("card-title").text();

            if (title.isEmpty()) {
                List<String> files = new ArrayList<>();
                for (Element file : card.select(".card-footer i")) {
                    String t = file.attr("title");

                    if (t.toLowerCase().contains("show details"))
                        continue;

                    files.add(t);
                }
                FILES.put(CARDS.size() - 1, files);
            } else {
                CARDS.add(title);
            }
        }

        //Handle user input.

        // print all the options
        for (int i = 0; i < CARDS.size(); i++) {
            System.out.printf("%d => %s\n", i, CARDS.get(i));
        }

        // take user input
        System.out.println("===============================");
        System.out.print("Please pick one options above: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        System.out.print("\n\n\n");

        List<String> files = FILES.get(choice);
        for (int i = 0; i < files.size(); i++) {
            System.out.printf("%d => %s\n", i, files.get(i));
        }

        // take user input
        System.out.println("===============================");
        System.out.print("Please pick one options above: ");
        choice = scanner.nextInt();
        downloadFile(files.get(choice));
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

        // Retrieve data from the data source.
        loadData.retrieveData("https://opendata.citywindsor.ca/Uploads/Schools.csv","C:\\Users\\BukayoDan\\COMP3220-Elaboration-Phase\\src\\main\\resources\\Schools.csv");
        // read retrieved data and insert data into schools table
        new loadData();
    }

    private static void downloadFile(String name) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL("https://opendata.citywindsor.ca/Uploads/" + name).openStream());
             FileOutputStream fileOS = new FileOutputStream("src/main/resources/" + name)) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
