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
		System.out.println("Name "+username );
		System.out.println("Password "+password );
		System.out.println("Favorites "+Arrays.toString(favorites) );
		System.out.println("Password "+Arrays.toString(recents) );
		System.out.println("Password "+Arrays.toString(genres) );
		return " ";
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
