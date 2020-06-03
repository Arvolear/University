package Graphics;

import Algorithm.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.plaf.basic.*;

class InputShower
{
	private Solver solver;

	private Interface inter;
	
	private JPanel inputPanel;
	private JScrollPane scrollPane;
	private JTable inputTable;

	private JPanel labelPanel;
	private JLabel inputLabel;

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
		labelPanel = new JPanel(new BorderLayout());

		inputPanel.setBounds(width - width / 25, height / 4, width, height / 2);
		labelPanel.setBounds(width - width / 25, 0, width, height / 4);

		inputPanel.setOpaque(false);
		inputPanel.setVisible(true);

		labelPanel.setOpaque(false);
		labelPanel.setVisible(true);

		inputTable = new JTable();
		inputTable.setModel(new DefaultTableModel(20, 20)
							{
								@Override
								public boolean isCellEditable(int row, int column) 
								{
									return false;
								}
							});

		
		inputTable.setBackground(new Color(30, 30, 30));
        inputTable.setForeground(new Color(230, 230, 230));
		inputTable.setRowHeight(height / 20);

		inputTable.setTableHeader(null);
		inputTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane = new JScrollPane(inputTable);
		scrollPane.getViewport().setBackground(new Color(30, 30, 30));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setBackground(new Color(30, 30, 30));
		scrollPane.getHorizontalScrollBar().setBackground(new Color(30, 30, 30));

		JPanel cornerPanel = new JPanel();
		cornerPanel.setBackground(new Color(30, 30, 30));

		scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, cornerPanel);

		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() 
												{
												    @Override
												    protected void configureScrollBarColors() 
													{
											        	this.thumbColor = new Color(100, 100, 100);
							    					}
												});

		scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() 
												{
												    @Override
												    protected void configureScrollBarColors() 
													{
											        	this.thumbColor = new Color(100, 100, 100);
							    					}
												});

		inputPanel.add(scrollPane, BorderLayout.CENTER);

		inputLabel = new JLabel("Problem Input");
		inputLabel.setBackground(new Color(100, 100, 100));
		inputLabel.setForeground(new Color(230, 230, 230));
		inputLabel.setFont(new Font("Serif", Font.PLAIN, height / 12));

		labelPanel.add(inputLabel, BorderLayout.CENTER);

		inter.add(labelPanel);
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

		inputTable.setModel(new DefaultTableModel(tableInfo, tableColums)
				{
					@Override
					public boolean isCellEditable(int row, int column) 
					{
						return false;
					}
				});
	}
}
