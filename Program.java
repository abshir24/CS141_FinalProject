import java.util.*;
import java.io.*;
public class Program {

	public static void main(String[] args)throws FileNotFoundException
	{
		File file = new File("src/practice.txt");
		
		Scanner fileScanner = new Scanner(file);
		
//		User[] allUsers = new User[countUsersInDB(fileScanner)];
//		
//		fileScanner = new Scanner(file);
//		
//		allUsers = retrieveUsers(fileScanner,allUsers);
//		
//		fileScanner = new Scanner(file);
		
		Scanner userinput = new Scanner(System.in);
		
		System.out.println("Do you already have an account with us?");
		
		String answer = userinput.next().toLowerCase();
		
		if(answer.charAt(0) == 'y') System.out.print("Cool"); 
		
		else {
			int[]genres; String[] favs = new String[5]; String[] recents = new String[5];
			
			System.out.println("Lets create an account for you");
			
			System.out.println("Type a username (no spaces)");
			
			answer = userinput.next().toLowerCase();
			
			String username = answer;
			
			System.out.println("Type a password (no spaces)");
			
			answer = userinput.next().toLowerCase();
			
			String password = answer;
			
			System.out.println("Please type choose your favorite genre's from below, if you would like to select all please type 'all', if not just choose by number(please place spaces between numbers)");
			
			System.out.println("1)Action 2)Romance 3)Horror 4)Childrens");
			
			String genre = userinput.next();
			
			if(genre.equals("all")) genres = new int[] {1,2,3,4};
			
			else {
				Scanner line = new Scanner(genre);
				
				int num = line.nextInt();
				
				int temp = num;
				
				int idx = 0;
				
				while(temp>0)
				{
					idx++;
					
					temp = temp/10;
				}
				
				genres = new int[idx];
				
				idx = 0;
				
				while(num>0) {
					genres[idx++] = num%10;
					
					num=num/10;
				}
				
				reverseArr(genres);
					
				
			}
	
		try{
			  FileWriter fstream = new FileWriter(file,true);
			  
			  BufferedWriter out = new BufferedWriter(fstream);
			  
			  out.write("\nusername "+username+"\n" + 
						"password "+password+"\n" + 
						"genres "+Arrays.toString(genres)+"\n" + 
						"favorites "+Arrays.toString(favs)+"\n" + 
						"watched "+Arrays.toString(recents));
			  out.close();
			  
		  }catch (Exception e){
			  
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
		
			User user = new User(username,password,genres,favs,recents);	
			
			System.out.println("Program over");
			
			
		}
		
	}
	
	public static void reverseArr(int[]arr)
	{
		int s = 0, e = arr.length-1,temp;
		
		while(s<e) {
			temp = arr[s];
			arr[s++] = arr[e];
			arr[e--] = temp ;
		}
		
	}
	
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
	
	public static int[] copyList(ArrayList<Integer>list)
	{
		int size = list.size(); 
		
		int[] returnArray=new int[size];
		
		for(int i = 0;i<size;i++)
			returnArray[i]=list.get(i);
		
		return returnArray;
	}
	
}

