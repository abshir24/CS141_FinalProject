import java.util.*;
import java.io.*;
public class Program {
	
	public static User[] allUsers;
	
	public static void main(String[] args)throws FileNotFoundException
	{
		File file = new File("src/practice.txt");
		
		Scanner fileScanner = new Scanner(file);
		
		allUsers = new User[countUsersInDB(fileScanner)+1];
		
		System.out.println(allUsers.length);
		
		fileScanner = new Scanner(file);
		
		allUsers = retrieveUsers(fileScanner,allUsers);
		
		fileScanner = new Scanner(file);
		
		Scanner userinput = new Scanner(System.in);
		
		System.out.println("Do you already have an account with us?");
		
		String answer = userinput.next().toLowerCase();
		
		if(answer.charAt(0) == 'y') {
			String username = promptUserNameLogin(userinput,0);
			
			if(username.contains("Error")) System.out.println(username);
			
			else {
				
				String password = promptPasswordLogin(userinput,username);
				
				if(password.contains("Error")) System.out.println(password);
				
				else {
					System.out.println("Login was successful!");
					User loggedInUser = retrieveUserFromDb(username);
				}
				
			}
			
		}
		
		else {
			int[]genres; String[] favs = new String[5]; String[] recents = new String[5];
			
			System.out.println("Lets create an account for you");
			
			String username = promptUserName(userinput);
			
			String password = promptPassword(userinput);
			
			genres = promptGenre(userinput);
			
			String[] params = new String[] {username,password,Arrays.toString(genres),Arrays.toString(favs),Arrays.toString(recents)};
			
			createUserInDb(file,params);
		
			User loggedInUser = new User(username,password,genres,favs,recents);	
			
			allUsers[allUsers.length-1] = loggedInUser;
			
			System.out.println("Program over");
			
		}
		
	}
	
	//Writes user content to our database file
	public static void createUserInDb(File file, String[]fields)throws FileNotFoundException
	{
		try{
			  FileWriter fstream = new FileWriter(file,true);
			  
			  BufferedWriter out = new BufferedWriter(fstream);
			  
			  out.write("\nusername "+fields[0]+"\n" + 
						"password "+fields[1]+"\n" + 
						"genres "+fields[2]+"\n" + 
						"favorites "+fields[3]+"\n" + 
						"watched "+fields[4]);
			  out.close();
			  
		  }catch (Exception e){
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
	}
	
	//reverses array.
	public static void reverseArr(int[]arr)
	{
		int s = 0, e = arr.length-1,temp;
		
		while(s<e) {
			temp = arr[s];
			arr[s++] = arr[e];
			arr[e--] = temp ;
		}
		
	}
	
	//converts an integer into an array given 123 ==> {1,2,3}
	public static int[] numToArray(int num)
	{
		int temp = num,idx = 0;
		
		while(temp>0)
		{
			idx++;
			
			temp = temp/10;
		}
		
		int[]genres = new int[idx];
		
		idx = 0;
		
		while(num>0) {
			
			genres[idx++] = num%10;
			
			num=num/10;
		}
		
		return genres;
	}
	
	//retrieves all users from the db and stores them into an array.
	
	public static User[] retrieveUsers(Scanner file,User[] allUsers)
	{
		int user=0,idx=0;
		
		String username = "", password = "";
		
		String[]favorites = new String[5];
		
		String[]watched = new String[5];
		
		ArrayList<Integer> genreCopy = new ArrayList<Integer>();
		
		while(file.hasNextLine())
		{
	
			Scanner line = new Scanner(file.nextLine());
			
			if(!line.hasNext()) continue;
			
			String row = line.next();
			
			String arrayString = "";
			
			if(row.equals("username"))  username = line.next();
			
			else if(row.equals("password"))  password = line.next(); 
			
			else if(row.equals("genres")) {
				
				while(line.hasNext()) arrayString+=line.next(); 
				
				arrayString=removeBrackets(arrayString);
				
				genreCopy = stringToIntArray(arrayString,countNums(arrayString));
			
			}
			else if(row.equals("favorites")){
				
				while(line.hasNext()) arrayString+=line.next();
				
				arrayString = removeBrackets(arrayString);
				
				favorites = arrayString.split(",");
		
				
			}	
			else{
				
				while(line.hasNext()) arrayString+=line.next();
				
				arrayString = removeBrackets(arrayString);
				
				watched = arrayString.split(",");
				
				int[] genres = copyList(genreCopy);
				
				User newUser = new User(username,password,genres,favorites,watched);
				
				allUsers[idx++] = newUser;
				
				favorites = new String[5];
				
				watched = new String[5];
				
				genreCopy = new ArrayList<Integer>();
				
			}
			
		}
		
		return allUsers;
	}
	
	//counts the number of users in the DB each user has 5 fields associated with them
	public static int countUsersInDB(Scanner file)
	{
		int user=0, totalUsers = 0;
		
		while(file.hasNextLine())
		{
			user++;
			
			if(user == 5){
				
				totalUsers++;
				
				user = 0;
			};
			
			file.nextLine();
			
		}
		
		
		return totalUsers ;
		
	}
	
	//removes brackets from string to make the process of converting string to array much easier
	public static String removeBrackets(String str)
	{
		String returnStr = "";
		
//		If the input is an int array
		
		if(str.charAt(1) == '1' || str.charAt(1) == '2' || str.charAt(1) == '3' || str.charAt(1) == '4') {
			
			for(int i=0;i<str.length();i++)
				
				if(str.charAt(i)!='[' && str.charAt(i)!=']' )
					
					if(str.charAt(i)==',') returnStr+=" ";
			
					else returnStr+=str.charAt(i);
				
			
			return returnStr;
					
		}
		
		
//	if the input is a string array
		
		for(int i=0;i<str.length();i++)
			
			if(str.charAt(i)!='[' && str.charAt(i)!=']' )
				
				returnStr+=str.charAt(i);
		
		return returnStr;
				
	}

	//Counts the number of integers in a string.
	public static int countNums(String genreArrayString)
	{
		Scanner line = new Scanner(genreArrayString);
		
		int count = 0;
		
		while(line.hasNextInt())
		{
			count++;
			int num = line.nextInt();
		}
		
		return count;
		
	}

	//retrieves integers from a string and converts them to an integer array list.
	public static ArrayList<Integer> stringToIntArray(String genreArrayString,int idx)
	{
		Scanner line = new Scanner(genreArrayString);
		
		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		
		idx=0;
		
		while(line.hasNextInt())
		{	
			int num = line.nextInt();
			
			returnArray.add(num);
		}
		
		return returnArray;
	}
	
	//Copies the elements from array list into array.
	public static int[] copyList(ArrayList<Integer>list)
	{
		int size = list.size(); 
		
		int[] returnArray=new int[size];
		
		for(int i = 0;i<size;i++)
			returnArray[i]=list.get(i);
		
		return returnArray;
	}
	
	//Checks to see if user exists in DB
	public static boolean isExistingUser(String username)
	{
		for(int i = 0;i<allUsers.length-1;i++)
			if(allUsers[i].getUserName().equals(username)) 
				return true;
		
		return false;
	}
	
	//Retrieves logged in User object from the DB
	
	public static User retrieveUserFromDb(String username)
	{
		String[] dummy1 = new String[5];
		
		String[] dummy2 = new String[5];
		
		int[] dummy3 = new int[4];
		
		//Placeholder code will not work without it.	
		User user = new User("null","null",dummy3,dummy2,dummy1);
		
		for(int i = 0;i<allUsers.length-1;i++)
			if(allUsers[i].getUserName().equals(username)) 
				user = allUsers[i];
		
		return user;
	}
	
	
	//Validates password
	public static boolean validatePassword(String username,String password)
	{
		
		for(int i = 0;i<allUsers.length-1;i++)
			
			if(allUsers[i].getUserName().equals(username) && allUsers[i].getPassword().equals(password))
				return true;
				
		return false;
	}

	//Prompts user for name
	public static String promptUserName(Scanner input)
	{
		System.out.println("Type a username (no spaces)");
		
		String answer = input.next().toLowerCase();

		if(isExistingUser(answer))
		{
			System.out.println("User name is already taken!");
			
			//If the username is taken then the funciton will run again
			
			promptUserName(input);
		}
			
		return answer;
	}
	
	//Prompts user for name when logging in
	public static String promptUserNameLogin(Scanner input,int attempts)
	{
		//If the attempts to enter username exceeds 3 then the function will return an error
		
		if(attempts == 3) return "Error: You have exceeded the designated number of attempts.Please try again someother time";
			
		System.out.println("Type a username (no spaces)");
		
		String answer = input.next().toLowerCase();
		
		if(!isExistingUser(answer))
		{
			//If the user doesn't exsist then the function will run again
			
			System.out.println("Username doesn't exist try again");
			
			promptUserNameLogin(input,attempts++);

		}
		
		//returns username
		
		return answer;
	}
	
	//Prompts new user for password
	public static String promptPassword(Scanner input)
	{
		System.out.println("Type a password, (no spaces)");
		
		String password = input.next().toLowerCase();
		
		return password;
		
	}
	
	
	//Prompts existing user for password on login
	
	public static String promptPasswordLogin(Scanner input,String username)
	{
		System.out.println("Type a password, (no spaces)");
		
		String password = input.next().toLowerCase();
		
		if(!validatePassword(username,password)) {
			
			//If the password is not valid then the function will return an error message
			
			return "Error: Bad email password combination.";
		}
			
		return password;
		
	}
	
	//Prompts the user for their favorite genre. The genres are listed 1-4 in the data base.
	
	public static int[] promptGenre(Scanner input)
	{
		System.out.println("Please type choose your favorite genre's from below, if you would like to select all please type 'all', if not just choose by number(please place spaces between numbers)");
		
		System.out.println("1)Action 2)Romance 3)Horror 4)Childrens");
		
		String genre = input.next();
		
		int[]genres;
		
		//If the user selects all genres then the array will fill with numbers from 1-4
		if(genre.equals("all")) genres = new int[] {1,2,3,4};
		
		else {
			Scanner line = new Scanner(genre);
			
			int num = line.nextInt();
			
			genres = numToArray(num);
	
			//Sorting array after numToArray function to get numbers in order
			Arrays.sort(genres);
			
		}
		
		return genres;
	}
}

