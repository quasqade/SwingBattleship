package view;

import model.ShipType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 11-Feb-18.
 */
public class ControlPanel extends JPanel {
	public ControlPanel()
	{
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=100;
		gbc.weighty=100;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;
		gbc.insets=new Insets(5,5,5,5);
		for (ShipType type: ShipType.values()
			 )
		{
			JButton but = new JButton(type.toString());
			add(but, gbc);
			but.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					processPlacingClick(type);
				}
			});
			gbc.gridy++;
		}
	}

	private void processPlacingClick(ShipType type)
	{

	}
}
