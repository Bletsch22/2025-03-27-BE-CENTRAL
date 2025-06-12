package week04;

public class ProjectWeek04 {

	public static void main(String[] args) {
		System.out.println(); // Spacing
		//1a ====================================================================================================================================
		
		// Create an array of int called ages that contains the following values: 3, 9, 23, 64, 2, 8, 28, 93.
		 
		int[] ages = { 3, 9, 23, 64, 2, 8, 28, 93};
				
		int result = ages[ages.length - 1] - ages[0] ; // subtract the first index{3} with the last{93} 93 - 3
		System.out.println("1a) Result: " + result); // print the result 90
		
		
		System.out.println(); // Spacing
		//1b ====================================================================================================================================
		
		//Repeat steps from 1a for 1b
		  
		// Create a new array of int called ages2 with 9 elements.

		int[] ages2 = {14, 50, 35, 19, 33, 22, 61, 77, 20, 44};
		
		
		// Show that using the index values for the elements is dynamic (works for arrays of different lengths).
		
		int result2 = ages2[ages2.length - 1] - ages2[0]; // subtracting first index of ages (44) with last index of ages 14 
		System.out.println("1b) Result2: " + result2);    // result variable is printed (30) 
		
		
		System.out.println(); // Spacing
		//1c ====================================================================================================================================
		
		// Use a loop to iterate through the array and calculate the average age. Print the result to the console.
		
		int sum = 0; // set a counter to start at 0
		
		for (int i = 0; i < ages.length; i++) { 	// run through the length of the ages array. 
			sum += ages[i];						    //Use sum to add the elements in each index together
			}
		
		int averageAge = sum / ages.length; // set a variable to the sum / number of indexes in ages2
		
		System.out.println("1c) average age: " + averageAge);
		
		
		System.out.println(); // Spacing
		System.out.println("============================="); // Spacing
		//2a====================================================================================================================================
		
		// loop through and calculate the average number of letters in the names array.  
		
		String[] names = {"Sam", "Tommy", "Time", "Sally", "Buck", "Bob"}; // if just printed names, it will give an ID stamp.
		
		System.out.println("Memory ID: " + names); // for console readability 
		
		System.out.println();//spacing
		
		int numLetters = 0; // start a counter for number of letters to start from 0.
		
		for (String name: names) { // loop through the names array 
			numLetters += name.length();// for every index in each name the numLetters counter counts 1.
		}
		System.out.println("2a) number of letters in names: " + numLetters);
		//2b====================================================================================================================================
		
		//Use a loop to iterate through the array again and concatenate all the names together, 
		//separated by spaces, and print the result to the console.
		
		String concNames = ""; // assign a blank String to reference outside of the for loop below
		
		for (int i = 0; i < names.length; i++) {  			// loop through names array
			 concNames = concNames.concat(names[i]+ ", ");	// looping through and adding names together with a comma between.
		}													//String.join()  String concName = String.join(", " + names)
		System.out.println(); // Spacing
		
		System.out.println("2b) concatenated names: " + concNames); 
		
		System.out.println(); // Spacing
		System.out.println("============================="); // Spacing
		//3====================================================================================================================================
		
		// Accessing the last element in any array. two ways 
		               // 0 1 2 3 4 5 6 7 8 9 indexes
		int[] elements = {1,2,3,4,5,6,7,8,9,10}; // count to the last element. or the easier way is to write elements[elements.length-1
		
		System.out.println("3) Shortcut to the end of the array: " + elements[elements.length-1]); // Prints 10
		System.out.println("3) Counting to the last element: " + elements[9]); // Prints 10
		
	
		System.out.println("=========================== ");
		System.out.println();
		//4====================================================================================================================================
		
		// accessing the first element
		System.out.println("4) first element of elements: " + elements[0]); // elements[0] is the first index in the array. //Prints 1
		
		
		System.out.println();// Spacing
		//5====================================================================================================================================
		
		// Create a new array of int called nameLengths and loop through the names array to add the length of each name to the name.

		// String[] names = {"Sam", "Tommy", "Time", "Sally", "Buck", "Bob"}
		
		System.out.print("5) ");
		
		int[] nameLengths = new int[names.length]; // make the nameLengths array the same size as the names array.
		
		for (int i = 0; i< names.length; i++) { 		// for every index, i adds 1
			nameLengths[i] = names[i].length(); 		// setting the names count per index to namesLengths per index
			System.out.print(+ nameLengths[i] + ", ");
		}
		
		System.out.println();//Spacing
		System.out.println();//Spacing
		//6 ====================================================================================================================================
	
		// write a loop to iterate through nameLengths array and calculate the sum of all the elements in the array.
		int numberOfElements = 0;
		for (int e : nameLengths) { // e for elements
			numberOfElements += e;		// loop through nameLengths for every time int e is found numberOfElements is counted
		}
		
		System.out.println("6) sum: " + numberOfElements);
		
		
		System.out.println(); // Spacing
		//7 ====================================================================================================================================
		
		// Write a method that takes a String word and an int and return the word concatenated how ever many times it is called
		System.out.print("7) ");
		
		System.out.println(wordSentence("Hello", 3)); // this is calling a method
		
		
		System.out.println();
		//8 ====================================================================================================================================
		
		// Write a method that takes two Strings firstName and lastName. Return a full name as a string with space between the two
		
		System.out.print("8) ");
		
		System.out.println(fullName("Ellie", "Blue"));
		
		
		System.out.println();
		//9 ====================================================================================================================================
		
		// Write a method that takes an array of int and returns true if the sum of all the ints in the array is greater than 100
		System.out.print("9) ");
		
		int[] digits = {10,20,30,50};
		
		System.out.println(oneHundredOrMore(digits)); // true
		
		
		System.out.println();//Spacing
		//10 ====================================================================================================================================
		
		// Write a method that takes an array of double and returns the average of all the elements in the array
		
		System.out.print("10) ");
		
		double[] randomNumbers1 = {11.1, 12.2, 13.3, 14.4, 15.5};
		System.out.println("average: " + gettingAverage(randomNumbers1));
		
		
		System.out.println();//Spacing
		//11 ====================================================================================================================================
		
		// Write a method that takes two arrays of double and returns true if the average of the elements in the first array -
		//    is greater than the average of the elements in the second array.
		
		System.out.println("=========================== ");
		System.out.print("11) ");
		
		double[] randomNumbers2 = {10.1,10.2,10.3,10.4,10.5};
		
		System.out.println("RandomNumbers1 :" + gettingAverage(randomNumbers1)); // calling a method
		
		System.out.print("11) ");
		System.out.println("RandomNumbers2 :" + gettingAverage(randomNumbers2)); //calling a method 
		
		System.out.print("11) ");
		System.out.println(biggerAverage(randomNumbers1,randomNumbers2)); // calling true or false from biggerAverage method
		
		System.out.println("=========================== "); 
		System.out.println();//Spacing
		//12 ====================================================================================================================================
		
		//Write a method called willBuyDrink that takes a boolean isHotOutside, 
		//and a double moneyInPocket, and returns true if it is hot outside 
		//and if moneyInPocket is greater than 10.50.
		
		double money = 10.50;
		
		boolean hotOutSide = false; 
		
		System.out.println("12) "+ willBuyDrink(hotOutSide, money));
		System.out.println();
		//13 ====================================================================================================================================
		// Create a method of your own that solves a problem. In comments, write what the method does and why you created it.
		
		// Build a weight converter that takes kilograms to pounds or pounds to kg.
		
		
		
		String lbs = "lbs";
		String kg = "kg";
		
		// weightConverter(unit to convert to, weight measurement)
		
		
		weightConverter(lbs,15); // converts to pounds
		
		weightConverter(kg, 15); // converts to kg
		
		
	}
											                      // Methods
	
	// 7 //====================================================================================================================================
	
	public static String wordSentence(String word, int n) { // Taking two arguments and relaying them to the command in main			
		StringBuilder multiple = new StringBuilder(); 
		int count = 1; 		// Start count at 1. Java likes to start at 0.
		
		while(count <= n) {	// While count is less than the number n represents(3)			
			multiple.append(word); // word is repeated into the string
			count++;				// until count is greater than or equal to n
		}
		return multiple.toString();	//returns multiple to main where the function wordSentence is called.
	}
	
	// 8 //====================================================================================================================================
	
	public static String fullName(String firstName, String lastName) {
		StringBuilder first = new StringBuilder(); // first name, a space, and last name to a stringbuilder
		StringBuilder last = new StringBuilder();
		StringBuilder space = new StringBuilder();
		first.append(firstName); // appending is adding the "first" variable to the firstName argument. 
		space.append(" ");		// same with the " " and last name.
		last.append(lastName);
		
		return first.toString() + space.toString()+ last.toString();
	}
	
	// 9 //====================================================================================================================================
	
	public static boolean oneHundredOrMore(int[] numbers) { //this method will take any array with a integer and will produce a true or false value if the sum in the array is < 100
		
		int sum = 0; // set the total to 0
		for (int num : numbers) { // loop through the numbers in any array that the method is called for
			sum += num; // for every number, the sum is added
		}
		return sum > 100; // returns true or false if the sum in any called array > 100;
	}
	
	// 10 //====================================================================================================================================
	
	public static double gettingAverage(double[] numbers) {// finding the average of numbers in an array
		double sum = 0;  
		
		for (int i =0; i < numbers.length; i++) { // run through the length of any array.
			sum += numbers[i];					  // add number in the index to the sum 
		}
		double av = sum / numbers.length; // set the average to the sum divided by the length of the array.
		return av; 

	}
	
	// 11 //====================================================================================================================================
	
	public static boolean biggerAverage(double[] arrays1, double[] arrays2) { // getting the average of two arrays and compares
 		
 		double array1Av = gettingAverage(arrays1); // assigning array1 to the gettingAverage method 
 		double array2Av = gettingAverage(arrays2); // as well as array2
 		
 	return array1Av > array2Av; // if array1 has a higher average it will return true or false
	}
	
	// 12 //====================================================================================================================================
	
	public static boolean willBuyDrink(boolean isHotOutSide, double moneyInPocket) { // a simple true/false method that factors temp and money
		
		if(isHotOutSide == true && moneyInPocket >= 10.50 ) { // if it is hot outside AND we have money in pocket greater or equal to $10.50 
			return true; // return true 
		}else {			// if we do NOT meet the above conditions -
			return false; // we return false.
		}
	
	}
	// 13 //====================================================================================================================================

	public static void weightConverter(String unit, int weight) { // takes a unit and converts 
		double lbs = 2.2025;
		
		if (unit == "kg") { // if the unit detects a string variable kg 
			
			double convertedWeight = weight * lbs; // weight is multiplied to get a kg unit
			double rounded = Math.round(convertedWeight * 10000.0 ) / 1000.0; // it is then rounded to the nearest 1000dth
			System.out.println("13) "+ weight + " KGS" + " Weight in KG: "+ rounded);
			
		}else if(unit == "lbs") { // weight is divided to get the 
			
			double convertedWeight = weight / lbs; 
			double rounded = Math.round(convertedWeight * 1000.0 )/ 1000.00;
			System.out.println("13) "+ weight+ " KGS" + " Weight in LBS: "+ rounded);
			
		}else {
			System.out.println("13) Please put in a valid conversion");
		}
	}	
		
}
	
	
	
	
		
		
	

	
		
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


