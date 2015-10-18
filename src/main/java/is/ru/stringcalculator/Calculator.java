package is.ru.stringcalculator;

import java.util.Vector;

public class Calculator {

	public static int add(String text) {
		if(text.equals("")){
			return 0;
		}
		else if(text.startsWith("//")) {
			String delim = String.valueOf(text.charAt(2));
			text = text.substring(text.indexOf("\n") + 1);

			delim = checkForStar(delim);

			return sum(splitNumbers(delim, text));
		}
		else if(text.contains(",") || text.contains("\n")) {
			String delim = ",|\n";
			return sum(splitNumbers(delim, text));
		}
		else
			return toInt(text);
	}

	private static int toInt(String number) {
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String delim, String numbers) {
	    return numbers.split(delim);
	}
      
    private static int sum(String[] numbers) {
    	Vector<String> negatives = new Vector<String>();
 	    int total = 0;
        for(String number : numbers) {
        	int tmp = toInt(number);
        	if(tmp < 0) {
        		negatives.add(number);
        	} else {
        		total += tmp;
        	}
		}
		    if(negatives.size() > 0) {
			String error = "Negatives not allowed: ";
			error += negatives.elementAt(0);
			negatives.remove(0);
			
			for(int i = 0; i < negatives.size(); i++) {
			error += ",";
			error += negatives.elementAt(i);
			}

			throw new RuntimeException(error);
			}
		return total;
    }

    private static String checkForStar(String delim) {
    	if(delim.contains("*")) {
    		String tmp = delim;
    		delim = "\\";
    		delim += tmp;

    		return delim;
    	}

    	return delim;
    }
}