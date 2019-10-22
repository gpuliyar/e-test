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
        try {
            Document document = Jsoup.connect(urlStr).get();
            Elements rows = document.select("table").get(1)
                    .select("tbody").get(0)
                    .select("tr");
            List<StockEntry> stocks = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

            for (int cntrIndex = 0; cntrIndex < rows.size(); cntrIndex++) {
                StockEntry stockEntry = new StockEntry();
                Element row = rows.get(cntrIndex);
                Elements columns = row.select("td");
                stockEntry.setDate(simpleDateFormat.parse(columns.get(0).text()));
                stockEntry.setValue(new Float(columns.get(1).text().substring(1)));
                stocks.add(stockEntry);
            }
            return stocks;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static  void main(String[] args) {
        Stock stock = new Stock();
        stock.scrape("http://localhost:8989/stocks.html");
    }
}
