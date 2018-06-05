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
}
