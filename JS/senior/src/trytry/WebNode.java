package trytry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebNode {
	public WebNode parent;
	public WebPage webPage;
	public ArrayList<WebNode> children;
	public double nodeScore;
	String url;
	String content;
	
	private int httpOK = HttpURLConnection.HTTP_OK;

	
	public WebNode(WebPage webPage) {
		this.webPage = webPage;
		this.children = new ArrayList<WebNode>();
		
	}
	
	public void setNodeScore(ArrayList<Keyword>keywords) throws IOException {
	    webPage.setScore(keywords);
	    this.nodeScore = webPage.score;
		for(WebNode child :children) {
			child.setNodeScore(keywords);
			System.out.println(child.nodeScore);
			this.nodeScore+=child.nodeScore*0.3;
		}
	}
	
	private String fetchContent() throws Exception {
		String retVal = "";
		URL url = new URL(this.url);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = br.readLine()) != null) {
			retVal += line;
		}
		return retVal;

	}

	public void addChild() throws Exception {
		this.url = this.webPage.url;
		this.content = fetchContent();

		Document document = Jsoup.parse(content);
		Elements lis = document.select("a[href]");
		ArrayList<String> urls = new ArrayList();

		for (Element li : lis) {
			try {
				if(li.attr("href").contains("https://")){
					String citeUrl = li.attr("href");
					String title = li.text();
					
					java.net.URL urll=new java.net.URL(citeUrl);
					java.net.HttpURLConnection uc = (java.net.HttpURLConnection)urll.openConnection();
					uc.setRequestProperty("User-agent", "Chrome/7.0.517.44");
					uc.connect();
					int status = uc.getResponseCode();
					if(status!= httpOK || citeUrl.indexOf(".PDF")!=-1 ||citeUrl.contains("http://www.google.com.tw/search?num=20") || citeUrl.contains("https://books.google.com.tw/books?id=")) {
						continue;
					}
					
					this.children.add(new WebNode(new WebPage(citeUrl, title)));
					
					
					if (children.size() == 3) {
						for(WebNode child:children) {
							child.parent=this;
						}
						break;
					}
				}
			} catch (Exception e) {
			}
		}
	}
}
