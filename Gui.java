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
	private Button GenreSelection = new Button("Back to Genre Selection");
	private User user;
	private Movie[] allMovies;
	private JButton[] temp;
	String currentMovie;
	int counter;
	
	public Gui()
	{	
		user = Program.retrieveUser();
		
		allMovies = Program.retrieveMovies();
	}
	
	public void ActivateHome()
	{
		
		JFrame frame = new JFrame("Gator Streaming");
		
		ButtonHandler handler = new ButtonHandler();
		
		frame.setLayout(new FlowLayout());
		
		previouslyWatched = addButton("Previously Watched",290,frame);
		
		previouslyWatched.addActionListener(handler);
		
		selectByGenre = addButton("Select Movies By Genre",290,frame);
		
		selectByGenre.addActionListener(handler);
		
		favorites = addButton("Favorites",290,frame);
		
		favorites.addActionListener(handler);
		
		RFU = addButton("Recommended for you",290,frame);
		
		RFU.addActionListener(handler);
		
		RMN = addButton("Random Movie Night",290,frame);
		
		RMN.addActionListener(handler);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		makeFrameFullSize(frame);
		
		frame.setVisible(true);
		
		return;
	}
	
	
	
	public void ActivateGenreSelection()
	{
		ButtonHandler handler = new ButtonHandler();
		
		JFrame frame = new JFrame("Gator Streaming");
		
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
		
		activeButton.setPreferredSize(new Dimension(dimension,dimension));
		
		frame.add(activeButton);
		
		return activeButton;
	}
	
	private void makeFrameFullSize(JFrame aFrame)
	{
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    aFrame.setSize(screenSize.width, screenSize.height);
	}
	
	public void ActivateFavorites()
	{
		ButtonHandler handler = new ButtonHandler();
		
		JFrame frame = new JFrame("Gator Streaming");
		
		String[] favorites = user.retrieveFavorites();
		
		frame.setLayout(new FlowLayout());
		
		JLabel caption;
		
		if(favorites[0].contains("null")){
			
			caption= new JLabel("You don't have any movies in your favorites list");
			
			Back.setPreferredSize(new Dimension(150,150));
			
			Back.addActionListener(handler);
			
			frame.add(Back);
			
			frame.add(caption);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			makeFrameFullSize(frame);
			
			frame.setVisible(true);
		}else{
			
			JButton[] movies = createButtonArray(favorites);
			
			temp = movies;
			
			JButton button;
			
			for(int i = 0;i<movies.length;i++) {
				
				button = movies[i];
				
				currentMovie = button.getText();
				
				button.setPreferredSize(new Dimension(200,150));
				
				button.addActionListener(
				  new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
//				      System.out.println(currentMovie);
				    }
				  }
				);
				
				counter = i;
								
				currentMovie = movies[i].getText();
				
				movies[i].setPreferredSize(new Dimension(200,150));
				
				temp[counter].addActionListener(
				  new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	System.out.println(temp[counter].getText());
				    }
				  }
				);
				frame.add(movies[i]);
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
		ButtonHandler handler = new ButtonHandler();
		
		JFrame frame = new JFrame("Gator Streaming");
		
		String[] recentlyWatched = user.retrieveRecents();
		
		frame.setLayout(new FlowLayout());
		
		JLabel caption;
		
		if(recentlyWatched[0].contains("null")){
		
			caption= new JLabel("You haven't watched any movies yet");
			
			frame.add(caption);
			
			Back.setPreferredSize(new Dimension(150,150));
			
			Back.addActionListener(handler);
			
			frame.add(Back);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			makeFrameFullSize(frame);
			
			frame.setVisible(true);
		}else{
			
			JButton[] movies = createButtonArray(recentlyWatched);
			
			JButton button;
			
			for(int i = 0;i<movies.length;i++) {
				
				button = movies[i];
				
				button.setPreferredSize(new Dimension(200,150));
				
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
		ButtonHandler handler = new ButtonHandler();
		
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.setLayout(new FlowLayout());
		
		String[] displayArray = new String[25];
		
		String movieGenre;
		
		int idx = 0;
		
		for(int i = 0;i<allMovies.length;i++)
		{
			movieGenre = allMovies[i].retrieveGenre();
			
			if(movieGenre.equals(genre)) displayArray[idx++] = allMovies[i].retrieveTitle();
		}
		
		
		JButton[] movies = createButtonArray(displayArray);
		
		JButton button;
		
		for(int i = 0;i<movies.length;i++) {
			
			button = movies[i];
			
			button.setPreferredSize(new Dimension(200,150));
			
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
		ButtonHandler handler = new ButtonHandler();
		
		JFrame frame = new JFrame("Gator Streaming");
		
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
		
		JButton[] movies = createButtonArray(displayArray);
		
		JButton button;
		
		for(int i = 0;i<movies.length;i++) {
			
			button = movies[i];
			
			button.setPreferredSize(new Dimension(200,150));
			
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
		ButtonHandler handler = new ButtonHandler();
		
		JFrame frame = new JFrame("Gator Streaming");
		
		frame.setLayout(new FlowLayout());
		
		Random rand = new Random();
		
		String title = allMovies[rand.nextInt(100)+1].retrieveTitle();
		
		Button button = new Button(title);
		
		button.setPreferredSize(new Dimension(300,300));
		
		frame.add(button);
		
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
		
		JButton[] Buttons = new JButton[input.length];
		
        for(int i = 0; i < input.length; i++)
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
	
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
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
		}
	}
}


