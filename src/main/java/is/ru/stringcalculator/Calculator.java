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
			String delimToReturn = "";
			if(delim.charAt(2) == '[') {
				String temp = "";
				delimToReturn = delim.substring(2, delim.indexOf('\n'));
				for(int i = 0; i < delimToReturn.length(); i++) {
					if(delimToReturn.charAt(i) == '[') {

						for(i = ++i; i < delimToReturn.length(); i++) {
							if(delimToReturn.charAt(i) != ']'){
							temp += delimToReturn.charAt(i);
							} else {
								temp += "|";
								break;
							}
						}
					}
				}
				delimToReturn = temp.substring(0, temp.length()-1);

				return delimToReturn;
			}

			return String.valueOf(delim.charAt(2));
		}
	      
	    private static int sum(String[] numbers) {
	    	Vector<String> negatives = new Vector<String>();
	 	    int total = 0;
	        for(String number : numbers) {
	        	int temp = toInt(number);
	        	if(temp < 0) {
	        		negatives.add(number);
	        	} else {
	        		if(temp > 1000) {
	        		temp = 0;
	        		}

	        		total += temp;
	        	}
			}
			    String error = negativeError(negatives);
				if(error.length() > 0){
				throw new RuntimeException(error);
				}

			return total;
	    }

	    private static String checkRegEx(String delim) {
	    	String tmp = "";
	    	if(delim.contains("|")) {
	    		for(int i = 0; i < delim.length(); i++) {
	    			if(delim.charAt(i) != '|'){
	    				tmp += "\\";
	    				tmp += delim.charAt(i);
	    			} else {
	    				tmp += delim.charAt(i);
	    			}
	    			
	    		}
	    	}
	    	else{
	    		tmp = ("\\Q" + delim + "\\E");
	    	}
	    		
	    		return tmp;
	    }
	}