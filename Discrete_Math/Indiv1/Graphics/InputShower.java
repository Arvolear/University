package Graphics;

import Algorithm.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

class InputShower
{
	private Solver solver;

	private Interface inter;
	
	private JPanel inputPanel;
	private JTable inputTable;

	InputShower(Solver solver, Interface inter)
	{
		this.solver = solver;
		this.inter = inter;

		init();
	}

	private void init()
	{
		int width = (int)inter.getRenderSize().getWidth() / 2;
		int height = (int)inter.getRenderSize().getHeight() / 2;

		inputPanel = new JPanel(new BorderLayout());
		inputPanel.setBounds(width, height / 4, width, height / 2);

		inputPanel.setOpaque(false);
		inputPanel.setVisible(true);

		inputTable = new JTable();
		
		inputTable.setBackground(new Color(30, 30, 30));
        inputTable.setForeground(new Color(230, 230, 230));

		inputTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		inputPanel.add(inputTable, BorderLayout.CENTER);

		inter.add(inputPanel);
	}

	void displayInput()
	{
		Object tableInfo[][] = solver.getInput();	
		Object tableColums[] = new Object[tableInfo[0].length];

		for (int i = 0; i < tableColums.length; i++)
		{
			tableColums[i] = i;
		}	

		inputTable.setModel(new DefaultTableModel(tableInfo, tableColums));
	}
}
