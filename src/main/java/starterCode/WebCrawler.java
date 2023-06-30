package starterCode;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class WebCrawler implements Crawler{

    @Override
    public void crawl(int level, String url, ArrayList<String> urlVisited) {
        if (level < 3) {
            Document doc = request(url, urlVisited);

            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String nextLink = link.absUrl("href");
                    if (!urlVisited.contains(nextLink)) {
                        crawl(level + 1, nextLink, urlVisited);
                    }
                }
            }
        }
    }

    private Document request(String url, ArrayList<String> visited) {
        try {
            Connection connection = Jsoup.connect(url);
            Document doc = connection.get();

            if (connection.response().statusCode() == 200) {
                visited.add(url);
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
