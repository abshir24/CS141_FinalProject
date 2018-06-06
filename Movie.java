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
		return title+" ["+genre+"] rating = "+rating;
	}
	
	public String retrieveTitle()
	{
		return title;
	}
	
	public String retrieveGenre()
	{
		return genre;
	}
	
}
