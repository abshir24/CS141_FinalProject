//This user class handles all of the functionality for the user that's currently logged in.

import java.util.*;
import java.io.*;

public class User {

	private String username;
	
	private String password;
	
	private String[] favorites = new String[5];
	
	private String[] recents = new String[5];
	
	private int[] genres;
	
	int count = 0;
	
	public User(String user,String pass,int[]genre,String[] favs, String[] watched)
	{
		username = user;
		password = pass;
		genres = genre;
		favorites = favs;
		recents = watched;
	}
	
	
//	Turns the user data into a string
	
	public String toString()
	{
		return username+" password:["+password+"] favorites:" + Arrays.toString(favorites)+" recents:" + Arrays.toString(recents)+" genre:"+Arrays.toString(genres);
	}
	
	
	//Retrieves username for specific user
	
	public String getUserName()
	{
		return username;
	}
	
	//Retrieves password for specific user
	public String getPassword()
	{
		return password;
	}
	
	//Retrieves favorite movies for specific user
	public String[] retrieveFavorites()
	{
		return favorites;
	}
	
	//Retrieves recently watched movies for specific user
	public String[] retrieveRecents()
	{
		return recents;
	}
	
	//Retrieves favorite genres for specific user
	public int[] retrieveGenres()
	{
		return genres;
	}
	
	//adds movie favorites and watched arrays
	public void addMovieToDb(String movie,String section)
	{
		int idx = 0;
		
		movie = Home.removeFrontSpaces(movie);
		
		
		if(section.equals("favorites"))
		{
			for(int i = 0;i<favorites.length;i++)
				if(favorites[i]!=null && favorites[i].contains(movie))return;
			
			for(int i = 0;i<favorites.length;i++)
				if(favorites[i]==null || favorites[i].contains("null")) { favorites[i] = movie; updateDb(section); return; }
		
			for(int i = 1;i<recents.length;i++)
				favorites[i-1] = favorites[i];
			
			favorites[favorites.length-1] = movie;
			
		}else {
			for(int i = 0;i<recents.length;i++)
				if(recents[i] != null && recents[i].contains(movie)) return;
			
			for(int i = 0;i<recents.length;i++)
				if( recents[i]==null || recents[i].contains("null")) { recents[i] = movie; updateDb(section); return; }
			
			for(int i = 1;i<recents.length;i++) 
				recents[i-1] = recents[i];
			
			recents[recents.length-1] = movie;
			
		}
		
		updateDb(section);		
	}
	
	//Updates the user database textfile using the ChangeLine file class
	
	public void updateDb(String section) {
		int userIdx = Home.findUserInTxt(username);
		
		int lineToBeEdited = Home.findLineInTxt(section,userIdx);
		
		String file = "src/USERDB.txt";
		
		if(section.equals("favorites")) {
			String newLineContent = "favorites " + Arrays.toString(favorites);
			
			ChangeLineInFile changeFile = new ChangeLineInFile();
			
			changeFile.changeALineInATextFile(file, newLineContent,lineToBeEdited);
		}else{
			String newLineContent = "watched " + Arrays.toString(recents);
			
			ChangeLineInFile changeFile = new ChangeLineInFile();
			
			changeFile.changeALineInATextFile(file, newLineContent,lineToBeEdited);
		}
		
	}
	
	
}
