package trytry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HtmlMatcher {
	
	public String searchKeyword;
	public String url;
	public String content;
	public ArrayList<String>namelist=new ArrayList<String>();
	public ArrayList<String>urllist=new ArrayList<String>();
	
	private int httpOK = HttpURLConnection.HTTP_OK;

	
	public HtmlMatcher(String searchKeyword, String kind){
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
			this.searchKeyword = searchKeyword;
		}
		
		System.out.println(this.searchKeyword);
		this.url ="http://www.google.com/search?q="+this.searchKeyword+"&oe=utf8&num=15";
	}
	
	private String fetchContent() throws IOException{
		
		String retVal = "";
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;
		while((line=bufReader.readLine())!=null){
			retVal += line;
		}
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException{
		
		if(content==null){
			content= fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();		
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".ZINbbc");
			
		for(Element li : lis){
			try {
				if(li.select("a").first().attr("href").equals("UTF-8")) {
					continue;
				}
				String title = li.select(".BNeawe").get(0).text();
				String citeUrl = li.select("a").first().attr("href");

				if(citeUrl.substring(0,4).equals("http")) {
					citeUrl = citeUrl.substring(0,citeUrl.indexOf("&"));
				}else {
					citeUrl = citeUrl.substring(citeUrl.indexOf("=")+1,citeUrl.indexOf("&sa=U&ved"));
				}
				if (!citeUrl.contains("youtube")){
					
					java.net.URL urll=new java.net.URL(citeUrl);
					java.net.HttpURLConnection uc = (java.net.HttpURLConnection) urll.openConnection();
					uc.connect();
					int status = uc.getResponseCode();
					if(status!= httpOK) {
						continue;
					}
					
					namelist.add(title);
					urllist.add(citeUrl);
					retVal.put(title, citeUrl);
				}
			
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	public HashMap<String, String> getrelated() throws IOException{
		
		if(content==null){
			content= fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();		
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".X7NTVe");
		for(Element li : lis){
			try 
			{
				String title = li.select(".BNeawe").get(0).text();
				String citeUrl = li.select("a").get(0).attr("href");
				citeUrl = "https://www.google.com.tw/"+citeUrl;
				retVal.put(title, citeUrl);
				

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
}
