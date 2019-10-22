import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    public List<StockEntry> scrape(String urlStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<StockEntry> stocks = new ArrayList<>();

        try {
            Document document = Jsoup.connect(urlStr).get();
            Elements rows = document.select("table").get(1).select("tr");

            for (Element row : rows) {
                Elements columns = row.select("td");
                stocks.add(new StockEntry(
                        simpleDateFormat.parse(columns.get(0).text()),
                        new Float(columns.get(1).text().substring(1))));
            }
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

        return stocks;
    }

    public static void main(String[] args) {
        Stock stock = new Stock();
        stock.scrape("http://localhost:8989/stocks.html");
    }
}
