package Graphics;

import Algorithm.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

class SolutionShower
{
	private Solver solver;

	private Interface inter;
	
	private JPanel solutionPanel;
	private JScrollPane scrollPane;
	private JTable solutionTable;
	
	private JPanel labelPanel;
	private JLabel solutionLabel;
	
	private JPanel fPanel;
	private JLabel fLabel;

	SolutionShower(Solver solver, Interface inter)
	{
		this.solver = solver;
		this.inter = inter;

		init();
	}

	private void init()
	{
		int width = (int)inter.getRenderSize().getWidth() / 2;
		int height = (int)inter.getRenderSize().getHeight() / 2;

		solutionPanel = new JPanel(new BorderLayout());
		labelPanel = new JPanel(new BorderLayout());
		fPanel = new JPanel(new BorderLayout());

		solutionPanel.setBounds(width - width / 16, height + height / 8, width, height / 2);
		labelPanel.setBounds(width - width / 16, height / 2 + height / 4, width, height / 2);
		fPanel.setBounds(width - width / 16, height + height / 2 + height / 48, width, height / 2);

		solutionPanel.setOpaque(false);
		solutionPanel.setVisible(true);
		
		labelPanel.setOpaque(false);
		labelPanel.setVisible(true);
		
		fPanel.setOpaque(false);
		fPanel.setVisible(true);

		solutionTable = new JTable();
		solutionTable.setModel(new DefaultTableModel(20, 20)
							{
								@Override
								public boolean isCellEditable(int row, int column) 
								{
									return false;
								}
							});
		
		solutionTable.setBackground(new Color(30, 30, 30));
        solutionTable.setForeground(new Color(230, 230, 230));

		solutionTable.setTableHeader(null);
		solutionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane = new JScrollPane(solutionTable);
		scrollPane.getViewport().setBackground(new Color(30, 30, 30));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		solutionPanel.add(scrollPane, BorderLayout.CENTER);
		
		solutionLabel = new JLabel("Problem Solution");
		solutionLabel.setBackground(new Color(100, 100, 100));
		solutionLabel.setForeground(new Color(230, 230, 230));
		solutionLabel.setFont(new Font("Serif", Font.PLAIN, height / 12));
		
		labelPanel.add(solutionLabel, BorderLayout.CENTER);
		
		fLabel = new JLabel("Fmin = ...");
		fLabel.setBackground(new Color(100, 100, 100));
		fLabel.setForeground(new Color(230, 230, 230));
		fLabel.setFont(new Font("Serif", Font.PLAIN, height / 12));
		
		fPanel.add(fLabel, BorderLayout.CENTER);

		inter.add(labelPanel);
		inter.add(fPanel);
		inter.add(solutionPanel);
	}

	void displaySolution()
	{
		Object tableInfo[][] = solver.getSolution();	
		Object tableColums[] = new Object[tableInfo[0].length];

		for (int i = 0; i < tableColums.length; i++)
		{
			tableColums[i] = i;
		}	

		solutionTable.setModel(new DefaultTableModel(tableInfo, tableColums)
							{
								@Override
								public boolean isCellEditable(int row, int column) 
								{
									return false;
								}
							});

		fLabel.setText("Fmin = " + solver.getF());
	}
}
