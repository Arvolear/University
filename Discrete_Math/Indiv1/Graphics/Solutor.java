package Graphics;

import Algorithm.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Solutor implements ActionListener
{
	private Solver solver;

	private Interface inter;
	private SolutionShower solutionShower;

	private JPanel solvePanel;
	private JButton solveButton;

	Solutor(Solver solver, Interface inter, SolutionShower solutionShower)
	{
		this.solver = solver;
		this.inter = inter;
		this.solutionShower = solutionShower;

		init();
	}

	private void init()
	{
		int width = (int)inter.getRenderSize().getWidth() / 4;
		int height = (int)inter.getRenderSize().getHeight() / 4;

		solvePanel = new JPanel(new BorderLayout());
		solvePanel.setBounds(width / 2 - width / 12, height * 2 + height / 4, width, height);

		solvePanel.setOpaque(false);
		solvePanel.setVisible(true);

		solveButton = new JButton("Solve");
		solveButton.addActionListener(this);
		solveButton.setBackground(new Color(100, 100, 100));
		solveButton.setForeground(new Color(230, 230, 230));
		
		solvePanel.add(solveButton, BorderLayout.CENTER);

		inter.add(solvePanel);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		try
		{
			solver.solve();
			solutionShower.displaySolution();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(inter.getFrame(), "Load a valid file first!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
