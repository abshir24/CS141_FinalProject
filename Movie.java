import java.util.*;
import java.io.*;

public class Movie {
	
	private int rating;
	private String genre;
	private String title;
	
	public Movie(String title,String genre,int rating)
	{
		this.title = title;
		this.genre = genre;
		this.rating = rating;
	}
	
	public String toString()
	{
		System.out.printf("Title: %s \n", title);
		
		System.out.printf("Title: %d \n", rating);
		
		System.out.printf("Title: %s \n", genre);
		
		return " ";
	}
	
}
