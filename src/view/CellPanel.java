package view;

import model.Cell;

import javax.swing.*;

//CellPanel is a simple JPanel that also stores a Cell object with its coordinates on board

public class CellPanel extends JPanel {
	public Cell cell;

	public CellPanel(Cell cell)
	{
		this.cell=cell;
	}
}
