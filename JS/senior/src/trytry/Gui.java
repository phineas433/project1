package trytry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Gui
 */
@WebServlet("/Gui")
public class Gui extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gui() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("search.jsp").forward(request, response);
			return;
		}
		String input = request.getParameter("keyword");
		String[] input_sp = input.split(" ");

		String searchKeyword = input_sp[0];
		String button = request.getParameter("kind");
		
		ArrayList<Keyword> keywords = new ArrayList<>();
		
		keywords.add(new Keyword(input_sp[0],20,button));
		if(button!="") {
			keywords.add(new Keyword(button,5,button));
			if(button=="sports") {
				keywords.add(new Keyword("運動",5,button));
			}else if(button =="dating") {
				keywords.add(new Keyword("交友",5,button));
			} else if(button=="games") {
				keywords.add(new Keyword("娛樂",5,button));
			}else if(button =="music") {
				keywords.add(new Keyword("音樂",5,button));
			} 
		}
		keywords.add(new Keyword("senior",5,button));
		keywords.add(new Keyword("老年人",5,button));

				
		if (!input.isEmpty()) {
			for (int i=1; i<input_sp.length;i++) {
				searchKeyword  = searchKeyword + "+" + input_sp[i];
				keywords.add(new Keyword(input_sp[i],20,button));
			}
		}
		
		HtmlMatcher google = new HtmlMatcher(searchKeyword,button);
		google.query();
		HashMap<String, String> related = google.getrelated();
		
		Sort sort=new Sort();
		
		for(int i=0;i<google.namelist.size();i++) {
			WebPage web = new WebPage(google.urllist.get(i),google.namelist.get(i));
			WebTree tree = new WebTree(web);
			try {
				tree.setPostOrderScore(keywords);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(tree.root.children);
			System.out.println(tree.root.nodeScore);
			sort.add(new WebList(google.namelist.get(i),google.urllist.get(i),tree.root.nodeScore));			
		}
		sort.sort();
		LinkedHashMap<String,String>query1 = sort.output();

		String[][] s = new String[query1.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(Entry<String, String> entry : query1.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    s[num][0] = key;
		    s[num][1] = value;
		    num++;
		}
		

		String[][] r = new String[related.size()][2];
		request.setAttribute("related",r);
		
		int num1 = 0;
		for(Entry<String, String> entry : related.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    r[num1][0] = key;
		    r[num1][1] = value;
		    num1++;
		}
		
		request.getRequestDispatcher("next.jsp")
		 .forward(request, response); 	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
