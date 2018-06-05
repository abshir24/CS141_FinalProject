import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui extends JFrame{	
	private JLabel buttonLabel;
	private JButton activeButton;
	private JButton previouslyWatched;
	private JButton selectByGenre;
	private JButton favorites;
	private JButton RFU;
	private JButton RBU;
	private JButton RMN;
	Program home = new Program();
	private User user;
	
	
	
	public Gui()
	{
		super("Gator Streaming");
	}
	
	public void ActivateHome()
	{
		ButtonHandler handler = new ButtonHandler();
		user = home.retrieveUser();
		setLayout(new FlowLayout());
		previouslyWatched = addButton("Previously Watched");
		previouslyWatched.addActionListener(handler);
		selectByGenre = addButton("Select Movies By Genre");
		favorites = addButton("Favorites");
		RFU = addButton("Recommended for you");
		RBU = addButton("Recommended by us");
		RMN = addButton("Random Movie Night");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeFrameFullSize(this);
		setVisible(true);
	}
	
	
	
	public void ActivateGenreSelection()
	{
		setLayout(new FlowLayout());
		Icon action = new ImageIcon(getClass().getResource("action.jpg"));
		Icon horror = new ImageIcon(getClass().getResource("horror.jpg"));
		Icon romance = new ImageIcon(getClass().getResource("romance.jpg"));
		Icon childrens = new ImageIcon(getClass().getResource("childrens.jpg"));
		addButton(action,"Action)");
		addButton(horror,"Horror)");
		addButton(romance,"Romance)");
		addButton(childrens,"Childrens)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeFrameFullSize(this);
		setVisible(true);
	}
	
	public JButton addButton(Icon img,String imgTitle)
	{	
		activeButton = new JButton(img);
		buttonLabel = new JLabel(imgTitle);
		activeButton.setPreferredSize(new Dimension(290, 290));
		add(buttonLabel);
		add(activeButton);
		return activeButton;
	}
	
	public JButton addButton(String caption)
	{
		activeButton = new JButton(caption);
		activeButton.setPreferredSize(new Dimension(290, 290));
		add(activeButton);
		return activeButton;
	}
	private void makeFrameFullSize(JFrame aFrame)
	{
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    aFrame.setSize(screenSize.width, screenSize.height);
	}
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==previouslyWatched) ActivatePreviouslyWatched();
			else if(event.getSource()==selectByGenre) ActivateSelectByGenre();
			else if(event.getSource()==favorites) ActivateFavorites();
			else if(event.getSource()==RFU) ActivateRFU();
			else if(event.getSource()==RBU) ActivateRBU();
			else if(event.getSource()==RMN) ActivateRMN();
			else if(event.getSource()==Action) ActivateAction();
			else if(event.getSource()==Romance) ActivateRomance();
			else if(event.getSource()==Childrens) ActivateChildrens();
			else ActivateHorror();
		}
	}
}


