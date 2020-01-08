package trytry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Sort {
	private ArrayList<WebList> lst;
	
	public Sort(){
		this.lst = new ArrayList<WebList>();
    }
	
	public void add(WebList weblist){
		lst.add(weblist);
		System.out.println("Done");
    }
	
	
	//quick sort
	public void sort(){
		quickSort(0, lst.size()-1);; 
	}
	
	
	private void quickSort(int leftbound, int rightbound){
		//implement quickSort algorithm
		if(leftbound >= rightbound) {
			return;
		}
		int x = rightbound;
		int a = leftbound;
		int b = rightbound-1;
		while(lst.get(a).score <= lst.get(x).score && a<b)a++;
		while(lst.get(b).score > lst.get(x).score && a<b)b--;
		swap(a,b);			
		swap(b,x);
        quickSort(leftbound,b-1);
        quickSort(b+1,rightbound);		
	}
	
	
	private void swap(int aIndex, int bIndex){
		WebList temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public LinkedHashMap<String, String> output(){
		LinkedHashMap<String, String> retVal = new LinkedHashMap<String, String>();	
		for(int i=lst.size()-1; i>-1;i--){
			WebList w = lst.get(i);
			retVal.put(w.name, w.url);
		}
		return retVal;
	}
}