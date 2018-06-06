import java.util.*;
import java.io.*;

public class User {

	private String username;
	private String password;
	private String[] favorites = new String[5];
	private String[] recents = new String[5];
	private int[] genres;
	
	public static int idx =0;
	
	
	// create another constructor for exsisting users
	public User(String user,String pass,int[]genre,String[] favs, String[] watched)
	{
		username = user;
		password = pass;
		genres = genre;
		favorites = favs;
		recents = watched;
	}
	
	public static int returnIdx()
	{
		return idx;
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
		
		if(favorites[0].equals("null")) return favorites;
		
		for(int i = 0;i<favorites.length;i++)
			if(!favorites[i].equals("null"))idx++ ;
			
		returnArr=new String[idx];
		
		idx=0;
		
		for(int i=0;i<favorites.length;i++)
			if(!favorites[i].equals("null"))returnArr[idx++] = favorites[i];
		
		return returnArr;
	}
	
	public String[] retrieveRecents()
	{
		String[] returnArr;
		
		int idx=0;	
		
		if(recents[0].equals("null")) return recents;
		
		for(int i = 0;i<recents.length;i++)
			if(!recents[i].equals("null")) idx++;
			
		returnArr=new String[idx];
		
		idx=0;
		
		for(int i=0;i<recents.length;i++)
			if(!recents[i].equals("null"))returnArr[idx++] = recents[i];
		
		
		return returnArr;
	}
	
	public int[] retrieveGenres()
	{
		return genres;
	}
}
