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

	private JPanel aPanel;
	private JPanel bPanel;
	private JPanel cPanel;

	private JLabel aLabel;
	private JLabel bLabel;
	private JLabel cLabel;

	private JButton a1Button;
	private JButton a2Button;
	private JButton a3Button;
	
	private JButton b1Button;
	private JButton b2Button;
	private JButton b3Button;
	private JButton b4Button;
	private JButton b5Button;
	private JButton b6Button;
	
	private JButton c1Button;
	private JButton c2Button;
	private JButton c3Button;
	private JButton c4Button;
	private JButton c5Button;
	private JButton c6Button;

	Solutor(Solver solver, Interface inter, SolutionShower solutionShower)
	{
		this.solver = solver;
		this.inter = inter;
		this.solutionShower = solutionShower;

		init();
	}

	private void init()
	{
		int width = (int)inter.getRenderSize().getWidth() / 2;
		int height = (int)inter.getRenderSize().getHeight() / 2;

		aPanel = new JPanel();
		bPanel = new JPanel();
		cPanel = new JPanel();

		GridBagConstraints agbc = new GridBagConstraints();
		agbc.weighty = 1.0;
		agbc.gridx = 0;
		agbc.gridy = 0;
		agbc.anchor = GridBagConstraints.PAGE_START;
		agbc.fill = GridBagConstraints.HORIZONTAL;
		agbc.insets = new Insets(10, 0, 0, 0);
		agbc.ipadx = 0;
		agbc.ipady = -28;
		
		GridBagConstraints bgbc = new GridBagConstraints();
		bgbc.weighty = 1.0;
		bgbc.gridx = 0;
		bgbc.gridy = 0;
		bgbc.anchor = GridBagConstraints.PAGE_START;
		bgbc.fill = GridBagConstraints.HORIZONTAL;
		bgbc.insets = new Insets(10, 0, 0, 0);
		bgbc.ipadx = 0;
		bgbc.ipady = 20;
		
		GridBagConstraints cgbc = new GridBagConstraints();
		cgbc.weighty = 1.0;
		cgbc.gridx = 0;
		cgbc.gridy = 0;
		cgbc.anchor = GridBagConstraints.PAGE_START;
		cgbc.fill = GridBagConstraints.HORIZONTAL;
		cgbc.insets = new Insets(10, 0, 0, 0);
		cgbc.ipadx = 0;
		cgbc.ipady = 20;

		aPanel.setLayout(new GridBagLayout());
		bPanel.setLayout(new GridBagLayout());
		cPanel.setLayout(new GridBagLayout());

		aPanel.setBounds(width / 40, height - height / 24 + height / 17, (int)(width / 3.5), height - height / 4 - height / 28 + height / 600);
		bPanel.setBounds(width / 40 + (int)(width / 3.34), height - height / 24 + height / 29 + height / 500, (int)(width / 3.5), height - height / 4 - height / 22);
		cPanel.setBounds(width / 40 + 2 * (int)(width / 3.34), height - height / 24 + height / 29 + height / 500, (int)(width / 3.5), height - height / 4 - height / 22);

		aPanel.setOpaque(false);
		aPanel.setVisible(true);

		bPanel.setOpaque(false);
		bPanel.setVisible(true);

		cPanel.setOpaque(false);
		cPanel.setVisible(true);

		aLabel = new JLabel("A", SwingConstants.CENTER);
		aLabel.setBackground(new Color(100, 100, 100));
		aLabel.setForeground(new Color(230, 230, 230));
		aLabel.setFont(new Font("Serif", Font.PLAIN, height / 12));

		bLabel = new JLabel("B", SwingConstants.CENTER);
		bLabel.setBackground(new Color(100, 100, 100));
		bLabel.setForeground(new Color(230, 230, 230));
		bLabel.setFont(new Font("Serif", Font.PLAIN, height / 12));

		cLabel = new JLabel("C", SwingConstants.CENTER);
		cLabel.setBackground(new Color(100, 100, 100));
		cLabel.setForeground(new Color(230, 230, 230));
		cLabel.setFont(new Font("Serif", Font.PLAIN, height / 12));

		a1Button = new JButton("(i, j, step) prob.");
		a1Button.addActionListener(this);
		a1Button.setBackground(new Color(100, 100, 100));
		a1Button.setForeground(new Color(230, 230, 230));

		a2Button = new JButton("(init, step) prob.");
		a2Button.addActionListener(this);
		a2Button.setBackground(new Color(100, 100, 100));
		a2Button.setForeground(new Color(230, 230, 230));
		
		a3Button = new JButton("(val) expectancy");
		a3Button.addActionListener(this);
		a3Button.setBackground(new Color(100, 100, 100));
		a3Button.setForeground(new Color(230, 230, 230));
	

		b1Button = new JButton("(i, j) accesible");
		b1Button.addActionListener(this);
		b1Button.setBackground(new Color(100, 100, 100));
		b1Button.setForeground(new Color(230, 230, 230));

		b2Button = new JButton("(i) all accesible");
		b2Button.addActionListener(this);
		b2Button.setBackground(new Color(100, 100, 100));
		b2Button.setForeground(new Color(230, 230, 230));
		
		b3Button = new JButton("(i) meaningful");
		b3Button.addActionListener(this);
		b3Button.setBackground(new Color(100, 100, 100));
		b3Button.setForeground(new Color(230, 230, 230));
		
		b4Button = new JButton("(i, j) communicate");
		b4Button.addActionListener(this);
		b4Button.setBackground(new Color(100, 100, 100));
		b4Button.setForeground(new Color(230, 230, 230));

		b5Button = new JButton("Equality classes");
		b5Button.addActionListener(this);
		b5Button.setBackground(new Color(100, 100, 100));
		b5Button.setForeground(new Color(230, 230, 230));
		
		b6Button = new JButton("All absorbing");
		b6Button.addActionListener(this);
		b6Button.setBackground(new Color(100, 100, 100));
		b6Button.setForeground(new Color(230, 230, 230));
		

		c1Button = new JButton("Irreducible chain");
		c1Button.addActionListener(this);
		c1Button.setBackground(new Color(100, 100, 100));
		c1Button.setForeground(new Color(230, 230, 230));

		c2Button = new JButton("All meaningful");
		c2Button.addActionListener(this);
		c2Button.setBackground(new Color(100, 100, 100));
		c2Button.setForeground(new Color(230, 230, 230));
		
		c3Button = new JButton("Primit. period.");
		c3Button.addActionListener(this);
		c3Button.setBackground(new Color(100, 100, 100));
		c3Button.setForeground(new Color(230, 230, 230));
		
		c4Button = new JButton("All returnable");
		c4Button.addActionListener(this);
		c4Button.setBackground(new Color(100, 100, 100));
		c4Button.setForeground(new Color(230, 230, 230));

		c5Button = new JButton("All periodical");
		c5Button.addActionListener(this);
		c5Button.setBackground(new Color(100, 100, 100));
		c5Button.setForeground(new Color(230, 230, 230));
		
		c6Button = new JButton("Ergodic chain");
		c6Button.addActionListener(this);
		c6Button.setBackground(new Color(100, 100, 100));
		c6Button.setForeground(new Color(230, 230, 230));

		aPanel.add(aLabel, agbc);
		agbc.gridy++;
		agbc.ipady = 51;

		bPanel.add(bLabel, bgbc);
		bgbc.gridy++;

		cPanel.add(cLabel, cgbc);
		cgbc.gridy++;

		aPanel.add(a1Button, agbc);
		agbc.gridy++;
		aPanel.add(a2Button, agbc);
		agbc.gridy++;
		aPanel.add(a3Button, agbc);
		
		bPanel.add(b1Button, bgbc);
		bgbc.gridy++;
		bPanel.add(b2Button, bgbc);
		bgbc.gridy++;
		bPanel.add(b3Button, bgbc);
		bgbc.gridy++;
		bPanel.add(b4Button, bgbc);
		bgbc.gridy++;
		bPanel.add(b5Button, bgbc);
		bgbc.gridy++;
		bPanel.add(b6Button, bgbc);
		
		cPanel.add(c1Button, cgbc);
		cgbc.gridy++;
		cPanel.add(c2Button, cgbc);
		cgbc.gridy++;
		cPanel.add(c3Button, cgbc);
		cgbc.gridy++;
		cPanel.add(c4Button, cgbc);
		cgbc.gridy++;
		cPanel.add(c5Button, cgbc);
		cgbc.gridy++;
		cPanel.add(c6Button, cgbc);

		inter.add(aPanel);
		inter.add(bPanel);
		inter.add(cPanel);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		String what = event.getActionCommand();
		
		try
		{
			switch (what)
			{
				case "(i, j, step) prob.":
				{
					solutionShower.displaySolution(solver.a1Probability(solutionShower.getInput()));
					break;
				}
				case "(init, step) prob.":
				{
					solutionShower.displaySolution(solver.a2Probability(solutionShower.getInput()));
					break;
				}
				case "(i, j) accesible":
				{
					solutionShower.displaySolution(solver.b1Accesible(solutionShower.getInput()));
					break;
				}
				case "(i) all accesible":
				{
					solutionShower.displaySolution(solver.b2Accesible(solutionShower.getInput()));
					break;
				}
				case "(i) meaningful":
				{
					solutionShower.displaySolution(solver.b3Meaningful(solutionShower.getInput()));
					break;
				}
				case "(i, j) communicate":
				{
					solutionShower.displaySolution(solver.b4Communicate(solutionShower.getInput()));
					break;
				}
				case "Equality classes":
				{
					solutionShower.displaySolution(solver.b5EqualityClasses(solutionShower.getInput()));
					break;
				}
				case "All absorbing":
				{
					solutionShower.displaySolution(solver.b6Absorbing(solutionShower.getInput()));
					break;
				}
				case "Irreducible chain":
				{
					solutionShower.displaySolution(solver.c1Irreducible(solutionShower.getInput()));
					break;
				}
				case "All meaningful":
				{
					solutionShower.displaySolution(solver.c2AllMeaningful(solutionShower.getInput()));
					break;
				}
			}
		}
		catch (Exception ex)
		{
			solutionShower.displaySolution("Error");
			JOptionPane.showMessageDialog(inter.getFrame(), "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
