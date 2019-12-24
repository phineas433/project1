import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public double score;

	public WebPage(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public void setScore(ArrayList<Keyword> keywords) {
		this.score = 0;


			for (Keyword k : keywords) {
				if (url.length() > 50) {
					//this.score += counter.countKeyword(k.name) * k.weight * 8;
					if (url.contains("tour guide")) {
						this.score *= 0.5;
					} else if (url.contains("senior")) {
						this.score *= 0.8;
					}
				} else {
					//this.score += counter.countKeyword(k.name) * k.weight;
				}
			}
		
	}

	@Override
	public String toString() {
		return name + " [" + score + "]\n" + url + "\n";
	}
}
