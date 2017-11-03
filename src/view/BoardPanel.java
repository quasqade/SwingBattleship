package view;

import controller.ModelListener;
import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;
import event.view.ViewEvent;
import event.view.ViewEventType;
import model.Cell;
import model.board.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoardPanel extends JPanel {

	private JLabel[][] cellLabels;
	private ModelListener modelListener;

	public BoardPanel()
	{
		super();
	}

	public BoardPanel(GameBoard board)
	{
		this();
		initializeBoard(board);
	}

	public BoardPanel(ModelListener modelListener)
	{
		this.modelListener = modelListener;
	}

	public void initializeBoard(GameBoard board)
	{
		cellLabels = new JLabel[board.getX()][board.getY()];

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=100;
		gbc.weighty=100;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;

		for(int i = 0; i < board.getX(); i++)
		{
			for (int j = 0; j < board.getY(); j++)
			{
				cellLabels[i][j] = new JLabel(board.getSymbolArray()[i][j]);
				CellPanel cellPanel = new CellPanel(new Cell(i,j));
				cellPanel.setBorder(BorderFactory.createEtchedBorder());
				cellPanel.add(cellLabels[i][j]);
				cellPanel.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (modelListener!=null)
						{
							ExecutorService executor = Executors.newSingleThreadExecutor();
							executor.execute(new Runnable() {
								@Override
								public void run() {
									CellPanel cellPanel = (CellPanel)e.getSource();
									modelListener.handleEvent(new ViewEvent(this, ViewEventType.FIRE, cellPanel.cell.x+" "+cellPanel.cell.y));
								}
							});
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}
				});
				add(cellPanel, gbc);
				gbc.gridx++;
			}
			gbc.gridx=0;
			gbc.gridy++;
		}

		revalidate();

	}

	public void updateCell(String message, Cell coordinates)
	{
		cellLabels[coordinates.x][coordinates.y].setText(message);
		cellLabels[coordinates.x][coordinates.y].updateUI();
		revalidate();
		repaint();
	}
}
