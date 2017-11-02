package view;

import controller.ModelListener;
import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;
import event.view.ViewEvent;
import event.view.ViewEventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by user on 23-Oct-17.
 */

/*A game menu*/

public class MenuFrame extends JFrame
{
private ModelListener modelListener;
private JPanel mainPanel;
private JPanel AIPanel;


public MenuFrame(String name, ModelListener modelListener)
{
	super(name);
	this.modelListener=modelListener;
	setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter()
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			if (JOptionPane.showConfirmDialog(MenuFrame.this, "Are you sure you want to quit?", "Really quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
			{
				modelListener.handleEvent(new ViewEvent(this, ViewEventType.ABORT, "Window cross"));
			}
		}
	});
	setMinimumSize(new Dimension(320, 480));
	setLocationRelativeTo(null); //center window on screen
	setResizable(false);
	layoutMainMenuComponents();
	pack();
	setVisible(true);

	Logger.push(new DebugMessage("Initialized menu frame " + this.getTitle() + " with listener " + modelListener.toString() + " on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
}

private void layoutMainMenuComponents()
{

if (mainPanel==null)
{
	mainPanel=new JPanel();
	mainPanel.setBorder(BorderFactory.createEtchedBorder());
	mainPanel.setLayout(new GridBagLayout());

	GridBagConstraints gbc = new GridBagConstraints();

	gbc.gridx=0;
	gbc.gridy=0;
	gbc.fill=GridBagConstraints.BOTH;
	gbc.insets=new Insets(10,10,10,10);

	gbc.anchor=GridBagConstraints.LINE_START;

	ImageIcon logo = new ImageIcon("resources/menulogo.png");
	JLabel logoLabel = new JLabel("", logo, JLabel.CENTER);
	mainPanel.add(logoLabel, gbc);

	gbc.gridy++;

	JButton but = new JButton("VS AI");
	but.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			layoutAIMenuComponents();
		}
	});
	mainPanel.add(but, gbc);

	gbc.gridy++;

	but = new JButton("Network");
	but.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{

		}
	});
	mainPanel.add(but, gbc);
	gbc.gridy++;

	but = new JButton("2P");
	but.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{

		}
	});
	mainPanel.add(but, gbc);

	gbc.gridy++;

	but = new JButton("Exit");
	but.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (JOptionPane.showConfirmDialog(MenuFrame.this, "Are you sure you want to quit?", "Really quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
			{
				modelListener.handleEvent(new ViewEvent(this, ViewEventType.ABORT, "Exit button"));
			}
		}
	});
	mainPanel.add(but, gbc);
}

	//turn off other panels
	if (AIPanel!=null)
	{
		remove(AIPanel);
	}

	//turn on this one
	add(mainPanel);

	//update frame
	pack();
	repaint();

}

private void layoutAIMenuComponents()
{
	if (AIPanel==null)
	{
	AIPanel = new JPanel();

	AIPanel.setBorder(BorderFactory.createEtchedBorder());
	AIPanel.setLayout(new GridBagLayout());

	GridBagConstraints gbc = new GridBagConstraints();

	gbc.gridx=0;
	gbc.gridy=0;
	gbc.fill=GridBagConstraints.BOTH;
	gbc.insets=new Insets(10,10,10,10);

	gbc.anchor=GridBagConstraints.LINE_START;

	JButton but = new JButton("Start");
	but.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			modelListener.handleEvent(new ViewEvent(this, ViewEventType.GAME_START_REQUEST, "AI"));
		}
	});
	AIPanel.add(but,gbc);

	gbc.gridy++;

	but = new JButton("Back");
	but.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			layoutMainMenuComponents();
		}
	});
	AIPanel.add(but,gbc);

	}

	//turn off other panels
	if (mainPanel!=null)
	{
		remove(mainPanel);
	}

	//turn on this one
	add(AIPanel);

	//update frame
	pack();
	repaint();

}


}
