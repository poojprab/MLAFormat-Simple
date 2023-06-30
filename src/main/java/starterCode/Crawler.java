package starterCode;

import java.util.ArrayList;

public interface Crawler {

    void crawl(int level, String url, ArrayList<String> urlVisited);
}
