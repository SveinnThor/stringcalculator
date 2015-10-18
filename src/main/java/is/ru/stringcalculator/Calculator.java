package is.ru.stringcalculator;

import java.util.Vector;

public class Calculator {

	public static int add(String text) {
		if(text.startsWith("//")) {
			String delim = deliminator(text);
			text = text.substring(text.indexOf("\n") + 1);

			delim = checkRegEx(delim);

			return sum(splitNumbers(delim, text));
		}

		if(text.equals("")){
			return 0;
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

	private static String negativeError(Vector vec) {
		String error = "";
		if(vec.size() > 0) {
			error = "Negatives not allowed: ";
			error += vec.elementAt(0);
			vec.remove(0);
			
			for(int i = 0; i < vec.size(); i++) {
			error += ",";
			error += vec.elementAt(i);
			}
			return error;
		}

		return error;
	}

	private static String deliminator(String delim) {
		String tmp;
		if(delim.charAt(2) == '[') {
			return delim.substring(3, delim.indexOf("]"));
		}

		return String.valueOf(delim.charAt(2));
	}
      
    private static int sum(String[] numbers) {
    	Vector<String> negatives = new Vector<String>();
 	    int total = 0;
        for(String number : numbers) {
        	int tmp = toInt(number);
        	if(tmp < 0) {
        		negatives.add(number);
        	} else {
        		if(tmp > 1000) {
        		tmp = 0;
        		}

        		total += tmp;
        	}
		}
		    String error = negativeError(negatives);
			if(error.length() > 0){
			throw new RuntimeException(error);
			}

		return total;
    }

    private static String checkRegEx(String delim) {
    		String tmp = ("\\Q" + delim + "\\E");
    		return tmp;
    }
}