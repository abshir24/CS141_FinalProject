import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui{	
	private JLabel buttonLabel;
	private JButton activeButton;
	private JButton previouslyWatched;
	private JButton selectByGenre;
	private JButton favorites;
	private JButton RFU;
	private JButton RMN;
	private JButton Horror;
	private JButton Action;
	private JButton Childrens;
	private JButton Romance;
	private Button Back = new Button("Back to Home");
	private Button addToFavs = new Button("Add To Favorites");
	private Button GenreSelection = new Button("Back to Genre Selection");
	private User user;
	private Movie[] allMovies;
	private JButton[] movies;
	private JButton randomMovie;
	private String currentMovie;
	
	private int count = 0;

	public Gui()
	{	
		user = Program.retrieveUser();
		
		allMovies = Program.retrieveMovies();
	}
	
	public void ActivateHome()
	{
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.getContentPane().setBackground( Color.GREEN );
		
		ButtonHandler handler = new ButtonHandler();
		
		frame.setLayout(new FlowLayout());
		
		frame.getContentPane().setBackground( Color.GREEN );
		
		JLabel title = new JLabel("Welcome to Gator Streaming " + user.getUserName() +"!");
		
		title.setFont(new Font("Serif", Font.PLAIN, 75));
		
		frame.add(title);
		
		previouslyWatched = addButton("Previously Watched",350,frame);
		
		previouslyWatched.addActionListener(handler);
		
		selectByGenre = addButton("Select Movies By Genre",350,frame);
		
		selectByGenre.addActionListener(handler);
		
		favorites = addButton("Favorites",350,frame);
		
		favorites.addActionListener(handler);
		
		RFU = addButton("Recommended for you",350,frame);
		
		RFU.addActionListener(handler);
		
		RMN = addButton("Random Movie Night",350,frame);
		
		RMN.addActionListener(handler);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		makeFrameFullSize(frame);
		
		frame.setVisible(true);
		
		return;
	}
	
	public void ActivateWatchMovie(String movieTitle)
	{
		JFrame frame = new JFrame("Gator Streaming");
		
		currentMovie = movieTitle;
		
		Movie movie = findMovie(movieTitle);
		
		user.addMovieToDb(movieTitle,"watched");
		
		String fullTitle = "Now Watching: " + movie.retrieveTitle() + "( " + movie.retrieveGenre() +" )";
		
		ButtonHandler handler = new ButtonHandler();
		
		frame.setLayout(new FlowLayout());
		
		frame.getContentPane().setBackground( Color.BLACK );
		
		JLabel title = new JLabel(fullTitle);
		
		title.setFont(new Font("Serif", Font.PLAIN, 60));
		
		title.setForeground(Color.WHITE);
		
		frame.add(title);
		
		Back.setPreferredSize(new Dimension(800,100));
		
		Back.addActionListener(handler);
		
		addToFavs.setPreferredSize(new Dimension(800,100));
		
		addToFavs.addActionListener(handler);
		
		frame.add(Back);
		
		frame.add(addToFavs);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		makeFrameFullSize(frame);
		
		frame.setVisible(true);
		
		
	}
	
	public void ActivateGenreSelection()
	{
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.getContentPane().setBackground( Color.GREEN );
		
		ButtonHandler handler = new ButtonHandler();
		
		frame.setLayout(new FlowLayout());
		
		Icon action = new ImageIcon(getClass().getResource("action.jpg"));
		
		Icon horror = new ImageIcon(getClass().getResource("horror.jpg"));
		
		Icon romance = new ImageIcon(getClass().getResource("romance.jpg"));
		
		Icon childrens = new ImageIcon(getClass().getResource("childrens.jpg"));
		
		Action = addButton(action,"Action)",frame);
		
		Action.addActionListener(handler);
		
		Horror = addButton(horror,"Horror)",frame);
		
		Horror.addActionListener(handler);
		
		Romance = addButton(romance,"Romance)",frame);
		
		Romance.addActionListener(handler);
		
		Childrens = addButton(childrens,"Childrens)",frame);
		
		Childrens.addActionListener(handler);
		
		Back.setPreferredSize(new Dimension(150,150));
		
		Back.addActionListener(handler);
		
		frame.add(Back);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		makeFrameFullSize(frame);
		
		frame.setVisible(true);
		
		return;
	}
	
	public void ActivateFavorites()
	{
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.getContentPane().setBackground( Color.GREEN );
		
		ButtonHandler handler = new ButtonHandler();
		
		String[] favs = user.retrieveFavorites();
		
		frame.setLayout(new FlowLayout());
		
		JLabel caption;
		
		if(favs[0]==null|| favs[0].contains("null")){
			
			caption= new JLabel("You don't have any movies in your favorites list");
			
			Back.setPreferredSize(new Dimension(150,150));
			
			Back.addActionListener(handler);
			
			frame.add(Back);
			
			frame.add(caption);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			makeFrameFullSize(frame);
			
			frame.setVisible(true);
		}else{
			
			movies = createButtonArray(favs);
			
			JButton button;
			
			for(int i = 0;i<movies.length;i++) {
				
				button = movies[i];
				
				button.setPreferredSize(new Dimension(200,150));
				
				button.addActionListener(handler);
				
				frame.add(button);
			}
			
			Back.setPreferredSize(new Dimension(150,150));
			
			Back.addActionListener(handler);
			
			frame.add(Back);
			 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			makeFrameFullSize(frame);
			
			frame.setVisible(true);
			
			return;
		}
		
	}
	
	
	public void ActivatePreviouslyWatched()
	{
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.getContentPane().setBackground( Color.GREEN );
		
		ButtonHandler handler = new ButtonHandler();
		
		String[] recentlyWatched = user.retrieveRecents();
		
		frame.setLayout(new FlowLayout());
		
		JLabel caption;
		
		if(recentlyWatched[0]==null || recentlyWatched[0].contains("null")){
			
			caption= new JLabel("You haven't watched any movies yet");
			
			frame.add(caption);
			
			Back.setPreferredSize(new Dimension(150,150));
			
			Back.addActionListener(handler);
			
			frame.add(Back);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			makeFrameFullSize(frame);
			
			frame.setVisible(true);
		}else{
			
			movies = createButtonArray(recentlyWatched);
			
			JButton button;
			
			for(int i = 0;i<movies.length;i++) {
				
				button = movies[i];
				
				button.setPreferredSize(new Dimension(200,150));
				
				button.addActionListener(handler);
				
				frame.add(button);
			}
			
			Back.setPreferredSize(new Dimension(150,150));
			
			Back.addActionListener(handler);
			
			frame.add(Back);
			 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			makeFrameFullSize(frame);
			
			frame.setVisible(true);
			
			return;
		}
		
	}
	
	public void ActivateGenre(String genre) 
	{
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.getContentPane().setBackground( Color.GREEN );
		
		ButtonHandler handler = new ButtonHandler();
		
		frame.setLayout(new FlowLayout());
		
		String[] displayArray = new String[25];
		
		String movieGenre;
		
		int idx = 0;
		
		for(int i = 0;i<allMovies.length;i++)
		{
			movieGenre = allMovies[i].retrieveGenre();
			
			if(movieGenre.equals(genre)) displayArray[idx++] = allMovies[i].retrieveTitle();
		}
		
		
		movies = createButtonArray(displayArray);
		
		JButton button;
		
		for(int i = 0;i<movies.length;i++) {
			
			button = movies[i];
			
			button.setPreferredSize(new Dimension(200,150));
			
			button.addActionListener(handler);
			
			frame.add(button);
		}
		
		GenreSelection.setPreferredSize(new Dimension(200,150));
		
		GenreSelection.addActionListener(handler);
				
		frame.add(GenreSelection);
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		makeFrameFullSize(frame);
		
		frame.setVisible(true);
		
		return;
		
	}
	
	public void ActivateRFU() {
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.getContentPane().setBackground( Color.GREEN );
		
		ButtonHandler handler = new ButtonHandler();
		
		frame.setLayout(new FlowLayout());
		
		String[] genres = genresToString();
		
		String[] displayArray = new String[16];
		
		int startIdx;
		
		switch (genres.length) {
		
	        case 1:  
	        	startIdx = retrieveStartIdx(genres[0]);
	        	
	        	displayMovies(displayArray,0,genres[0],16,startIdx);
	        	
	        	break;
	        case 2:
	        	startIdx = retrieveStartIdx(genres[0]);
	       
	        	displayMovies(displayArray,0,genres[0],8,startIdx);
	        	
	        	startIdx = retrieveStartIdx(genres[1]);
	        	
	        	displayMovies(displayArray,8,genres[1],16,startIdx);
	        	
	        	break;
	                 
	        case 3: 
	        	startIdx = retrieveStartIdx(genres[0]);
	            
	        	displayMovies(displayArray,0,genres[0],5,startIdx);
	        	
	        	startIdx = retrieveStartIdx(genres[1]);
	        	
	        	displayMovies(displayArray,5,genres[1],10,startIdx);
	        	
	        	startIdx = retrieveStartIdx(genres[2]);
	        	
	        	displayMovies(displayArray,10,genres[2],16,startIdx);
	        	
	        	break;
	        		
	        default:
	        	startIdx = retrieveStartIdx(genres[0]);
	            
	        	displayMovies(displayArray,0,genres[0],4,startIdx);
	        	
	        	startIdx = retrieveStartIdx(genres[1]);
	        	
	        	displayMovies(displayArray,4,genres[1],8,startIdx);
	        	
	        	startIdx = retrieveStartIdx(genres[2]);
	        	
	        	displayMovies(displayArray,8,genres[2],12,startIdx);
	        	
	        	startIdx = retrieveStartIdx(genres[3]);
	        	
	        	displayMovies(displayArray,12,genres[3],16,startIdx);
	        	
	        	break;
				
		}
		
		movies = createButtonArray(displayArray);
		
		JButton button;
		
		for(int i = 0;i<movies.length;i++) {
			
			button = movies[i];
			
			button.setPreferredSize(new Dimension(200,150));
			
			button.addActionListener(handler);
			
			frame.add(button);
		}
		
		Back.setPreferredSize(new Dimension(150,150));
		
		Back.addActionListener(handler);
				
		frame.add(Back);
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		makeFrameFullSize(frame);
		
		frame.setVisible(true);
		
		return;
		
	}
	
	public void ActivateRMN() {
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.getContentPane().setBackground( Color.GREEN );
		
		ButtonHandler handler = new ButtonHandler();
		
		frame.setLayout(new FlowLayout());
		
		Random rand = new Random();
		
		String title = allMovies[rand.nextInt(100)+1].retrieveTitle();
		
		randomMovie= new JButton(title);
		
		randomMovie.setPreferredSize(new Dimension(300,300));
		
		randomMovie.addActionListener(handler);
		frame.add(randomMovie);
		
		Back.setPreferredSize(new Dimension(150,150));
		
		Back.addActionListener(handler);
				
		frame.add(Back);
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		makeFrameFullSize(frame);
		
		frame.setVisible(true);
		
		return;
		
	}
	
	

	public void displayMovies(String[] displayArray,int currentIdx,String genre,int endIdx,int startIdx)
	{
		while(currentIdx<endIdx)
		{
			displayArray[currentIdx++] = allMovies[startIdx++].retrieveTitle();
		}
		
	}
	
	public int retrieveStartIdx(String genre)
	{
		int startIdx;
		
		switch (genre) {
	        case "Action":  startIdx=0;
	                 return startIdx;
	                 
	        case "Romance":	startIdx = 24;
	        	return startIdx;
	                 
	        case "Horror": startIdx = 49;
	        	return startIdx;	
	        		
	        default:startIdx = 74;
	        	return startIdx;
		} 
	}
	
	public JButton[] createButtonArray(String[] input) {
		
		JButton[] Buttons;
		
		int count = 0;
		
        for(int i = 0; i < input.length; i++)
        	if(input[i]==null||input[i].contains("null")) count++;
        
        Buttons = new JButton[input.length-count];
        
        for(int i = 0; i < input.length-count; i++)
        	Buttons[i] = new JButton(input[i]);
        
        return Buttons;
    }
	
	public String[] genresToString()
	{
		int[] genres = user.retrieveGenres();
		
		String[] returnArr = new String[genres.length];
		
		for(int i = 0 ; i<genres.length;i++)
		{
			switch (genres[i]) {
			
            case 1:  returnArr[i] = "Action";
                     break;
                     
            case 2:	returnArr[i] = "Romance";
                    break;
                     
            case 3: returnArr[i] = "Horror";
            		break;	
            		
            default:returnArr[i] = "Childrens";
					break;	
			}  
		}
		
		return returnArr;
	}
	
	public Movie findMovie(String title)
	{
		for(int i = 0;i<allMovies.length;i++)
			if(allMovies[i].retrieveTitle().contains(title)) return allMovies[i];
		
		return allMovies[0];
	}
	
	public JButton addButton(Icon img,String imgTitle,JFrame frame)
	{	
		activeButton = new JButton(img);
		
		buttonLabel = new JLabel(imgTitle);
		
		activeButton.setPreferredSize(new Dimension(290, 290));
		
		frame.add(buttonLabel);
		
		frame.add(activeButton);
		
		return activeButton;
	}
	
	public JButton addButton(String caption,int dimension,JFrame frame)
	{
		activeButton = new JButton(caption);
		
		activeButton.setPreferredSize(new Dimension(dimension,dimension-100));
		
		frame.add(activeButton);
		
		return activeButton;
	}
	
	private void makeFrameFullSize(JFrame aFrame)
	{
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    aFrame.setSize(screenSize.width, screenSize.height);
	}
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==Back) ActivateHome();
			else if(event.getSource()==GenreSelection) ActivateGenreSelection();
			else if(event.getSource()==previouslyWatched) ActivatePreviouslyWatched();
			else if(event.getSource()==favorites) ActivateFavorites();
			else if(event.getSource()==selectByGenre) ActivateGenreSelection();
			else if(event.getSource()==Action) ActivateGenre("Action");
			else if(event.getSource()==Childrens) ActivateGenre("Childrens");
			else if(event.getSource()==Horror) ActivateGenre("Horror");
			else if(event.getSource()==Romance) ActivateGenre("Romance");
			else if(event.getSource()==RFU) ActivateRFU();
			else if(event.getSource()==RMN) ActivateRMN();
			else if(event.getSource()==randomMovie) ActivateWatchMovie(randomMovie.getText());
			else if(event.getSource()==addToFavs) {
				if(count == 0) {
					user.addMovieToDb(currentMovie,"favorites");
					ActivateHome();
					count++;
				}else {
					count=0;
				}
			}
			if(movies!=null) 
				for(int i =0;i<movies.length;i++)
				{
					if(event.getSource()==movies[i]) {
						ActivateWatchMovie(movies[i].getText());
						return;
					}
						
				}
		}
	}
}


