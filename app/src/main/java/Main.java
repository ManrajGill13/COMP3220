import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Main
{
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

       // Retrieve data from the data source.
       loadData.retrieveData("https://opendata.citywindsor.ca/Uploads/Schools.csv","C:\\Users\\BukayoDan\\COMP3220-Elaboration-Phase\\src\\main\\resources\\Schools.csv");
        // read retrieved data and insert data into schools table
        new loadData();
    }
}