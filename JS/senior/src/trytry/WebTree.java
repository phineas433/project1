package trytry;

import java.io.IOException;
import java.util.ArrayList;

public class WebTree {
	public WebNode root;

	public WebTree(WebPage rootPage) {
		this.root = new WebNode(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword> keywords) throws Exception {
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws Exception {
		startNode.addChild();
		startNode.setNodeScore(keywords);
	}
}

