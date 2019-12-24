
import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("Please enter your height and weight: "); 
		String text = buf.readLine(); 
		String[] data = text.split(" ");
		double res = getBMI(data);
		String dia = getDiagnosis(res);
		System.out.println(dia + " BMI: " + res); 
               
	}
	
	public static double getBMI(String[] data) {
		String part1 = data[0];
		String part2 = data[1];
	    double h = Double.parseDouble(part1);
	    double hm=h/100;
	    double w = Double.parseDouble(part2);
	    double bmi=w/(hm*hm);
		// calculate the bmi
		return bmi;
	}
	
	public static String getDiagnosis(double bmi) {
		String commentbmi=" ";
		if (bmi>=30) {
			commentbmi="You are not in shape.Actually, you are not even close.";
		}else if ( 30 > bmi && bmi >= 26) {
			commentbmi="To be honest, you are not in shape.";
		}else if (26 > bmi && bmi >= 20) {
			commentbmi="You are in shape.";
		}else if (bmi <20){
			commentbmi="You are under shape.";
		}
		
		// give comments depending on bmi
        return commentbmi;
	}
}
