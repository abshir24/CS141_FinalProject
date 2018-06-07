//This movie class is where a movie object lives

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
	
	//returns the movie data in string format
	public String toString()
	{
		return title+" ["+genre+"] rating = "+rating;
	}
	
	//retrieves the title for a movie
	public String retrieveTitle()
	{
		return title;
	}
	
	//retrieves the genre for a movie
	public String retrieveGenre()
	{
		return genre;
	}
	
}
