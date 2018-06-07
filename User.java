import java.util.*;
import java.io.*;

public class User {

	private String username;
	
	private String password;
	
	private String[] favorites = new String[5];
	
	private String[] recents = new String[5];
	
	private int[] genres;
	
	// create another constructor for exsisting users
	public User(String user,String pass,int[]genre,String[] favs, String[] watched)
	{
		username = user;
		password = pass;
		genres = genre;
		favorites = favs;
		recents = watched;
	}
	
	public String toString()
	{
		return username+" password:["+password+"] favorites:" + Arrays.toString(favorites)+" recents:" + Arrays.toString(recents)+" genre:"+Arrays.toString(genres);
	}
	
	public String getUserName()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String[] retrieveFavorites()
	{
		String[] returnArr;
		
		int idx=0;	
		
		if(favorites[0].contains("null")) return favorites;
		
		for(int i = 0;i<favorites.length;i++)
			if(!favorites[i].contains("null"))idx++;
			
		returnArr=new String[idx];
		
		idx=0;
		
		for(int i=0;i<favorites.length;i++)
			if(!favorites[i].contains("null")) returnArr[idx++] = favorites[i];
		
		return returnArr;
	}
	
	public String[] retrieveRecents()
	{
		String[] returnArr;
		
		int idx=0;	
		
		if(recents[0].contains("null")) return recents;
		
		for(int i = 0;i<recents.length;i++)
			if(!recents[i].contains("null")) idx++;
			
		returnArr=new String[idx];
		
		idx=0;
		
		for(int i=0;i<recents.length;i++)
			if(!recents[i].contains("null"))returnArr[idx++] = recents[i];
		
		
		return returnArr;
	}
	
	public int[] retrieveGenres()
	{
		return genres;
	}
	
	public void addMovieToDb(String movie,String section) throws FileNotFoundException
	{
		int idx = 0;
		if(section.equals("favorites"))
		{
			for(int i = 0;i<favorites.length;i++)
				if(favorites[i].contains("null")) { favorites[i] = movie; updateDb(section); return; }
			
			for(int i = favorites.length-1;i>0;i--)
				favorites[i] = favorites[i-1];
			
			favorites[0] = movie;
		}else {
			for(int i = 0;i<recents.length;i++)
				if(recents[i].contains("null")) { recents[i] = movie; updateDb(section); return; }
			
			for(int i = recents.length-1;i>0;i--)
				recents[i] = recents[i-1];
			
			recents[0] = movie;
		}
		
		updateDb(section);		
	}
	
	public void updateDb(String section) throws FileNotFoundException{
		int userIdx = Program.findUserInTxt(username);
		
		int lineToBeEdited = Program.findLineInTxt(section,userIdx);
		
		String file = "src/practice.txt";
		
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
