package li.smueller.code.stackexchange;

import it.sauronsoftware.feed4j.FeedIOException;
import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.FeedXMLParseException;
import it.sauronsoftware.feed4j.UnsupportedFeedException;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedItem;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotQuestionDetector {

	private Logger logger = Logger.getLogger(HotQuestionDetector.class.getName());
	
	public void detect(String siteURL) {

		try {
			
			URL url = new URL("http://stackexchange.com/feeds/questions");
			
			Feed feed = FeedParser.parse(url);
			
			logger.log(Level.INFO, "\n** HOT TRAVEL QUESTIONS **\n");
			int items = feed.getItemCount();
			int counter = 1;
			int travelCounter = 0;
			for (int i = 0; i < items; i++) {
				FeedItem item = feed.getItem(i);
				
				String title = item.getTitle();
				String site = title.substring(title.lastIndexOf('-') + 2, title.length());
				
				if (site.equals(siteURL)) {
					logger.log(Level.INFO, "Title: " + item.getTitle());
					logger.log(Level.INFO, "Link: " + item.getLink());
					logger.log(Level.INFO, "Rank: " + counter + "\n");
					travelCounter++;
				}
				counter++;
			}
			logger.log(Level.INFO, "** HOT TRAVEL QUESTIONS **\n");
			logger.log(Level.INFO, travelCounter + " of " + (counter - 1) + " hot questions are from travel.stackexchange.com");
			
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (FeedIOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (FeedXMLParseException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (UnsupportedFeedException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

}