package trytry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class KeywordCounter {
	
	private String url;
	private String content;
	
	public KeywordCounter(String url) {
		this.url =url;
	}
	
	private String fetchContent() throws IOException {
		String retVal="";
		URL url = new URL(this.url);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		conn.connect();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = br.readLine()) != null) {
			retVal += line;
		}
		return retVal;
		}
	public int countKeyword(String keyword) throws IOException {
		if (content == null) {
			content = fetchContent();
		}

		content = content.toUpperCase();
		keyword = keyword.toUpperCase();

		int retVal = 0;
		while(true) {
		int num = content.indexOf(keyword);
		if (num >= 0) {
			content = content.substring(num + keyword.length());
			retVal++;
			}else break;
		}
		return retVal;
	}
}