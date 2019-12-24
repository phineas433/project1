import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlMatcher {
	private String url;
	private String content;
	private String searchKeyword; 
	private String searchKeyword2; 
	public static ArrayList<WebPage> web; 
	public static String relatedKeyword = "[Related keywords]\n";

	public HtmlMatcher(String kind, String searchKeyword) throws IOException {
		this.searchKeyword2 = searchKeyword;
		web = new ArrayList<>();

		switch (kind) {
		case "sports":
			if (searchKeyword != "sports") {
				this.searchKeyword = searchKeyword + "+sports+senior";
			} else {
				this.searchKeyword = searchKeyword + "+senior";
			}
			break;
		case "dating":
			if (searchKeyword != "dating") {
				this.searchKeyword = searchKeyword + "+dating+senior";
			} else {
				this.searchKeyword = searchKeyword + "+senior";
			}
			break;
		case "games":
			if (searchKeyword != "games") {
				this.searchKeyword = searchKeyword + "+games+senior";
			} else {
				this.searchKeyword = searchKeyword + "+senior";
			}
			break;
		case "music":
			if (searchKeyword != "music") {
				this.searchKeyword = searchKeyword + "+music+senior";
			} else {
				this.searchKeyword = searchKeyword + "+senior";
			}
			break;
		default:
			if (searchKeyword != "senior") {
				this.searchKeyword = searchKeyword + "+entertainment+senior";
			} else {
				this.searchKeyword = searchKeyword + "+senior";
			}
			break;
		}
		query(); // 在Google的搜尋
		fetchRelatedKeyword(); // 查Google推薦相關關鍵字
	}