package is.ru.stringcalculator;

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
 	    int total = 0;
        for(String number : numbers){
		    total += toInt(number);
		}
		return total;
    }

    private static String checkForStar(String delim){
    	if(delim.contains("*")) {
    		String tmp = delim;
    		delim = "\\";
    		delim += tmp;

    		return delim;
    	}

    	return delim;
    }
    //Before Refactoring
    /*
    else if(text.startsWith("//")) {
			String delim = String.valueOf(text.charAt(2));
			text = text.substring(text.indexOf("\n") + 1);

			if(text.contains("*")) {
				String tmp = delim;
				delim = "\\";
				delim += tmp;
			}

			return sum(splitNumbers(delim, text));
		}*/

	//After Refactoring
		/*
    else if(text.startsWith("//")) {
			String delim = String.valueOf(text.charAt(2));
			text = text.substring(text.indexOf("\n") + 1);

			delim = checkForStar(delim);

			return sum(splitNumbers(delim, text));
		}*/


}