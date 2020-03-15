package Graphics;

import Algorithm.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.nio.file.Paths;
import java.nio.file.Path;

class ProblemChooser implements ActionListener
{
	private Solver solver;

	private Interface inter;
	private InputShower inputShower;

	private JPanel openPanel;
	private JButton openButton;

	ProblemChooser(Solver solver, Interface inter, InputShower inputShower)
	{
		this.solver = solver;
		this.inter = inter;
		this.inputShower = inputShower;

		init();
	}

	private void init()
	{
		int width = (int)inter.getRenderSize().getWidth() / 4;
		int height = (int)inter.getRenderSize().getHeight() / 4;

		openPanel = new JPanel(new BorderLayout());
		openPanel.setBounds(width / 2, height / 2, width, height);

		openPanel.setOpaque(false);
		openPanel.setVisible(true);

		openButton = new JButton("Open");
		openButton.addActionListener(this);
		openButton.setBackground(new Color(100, 100, 100));
		openButton.setForeground(new Color(230, 230, 230));
		
		openPanel.add(openButton, BorderLayout.CENTER);

		inter.add(openPanel);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Path currentRelativePath = Paths.get("");
		String name = currentRelativePath.toAbsolutePath().toString();

		JFileChooser fileChooser = new JFileChooser(name);

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION)
		{
			solver.load(fileChooser.getSelectedFile().getAbsolutePath());
			inputShower.displayInput();
		}
	}
}
