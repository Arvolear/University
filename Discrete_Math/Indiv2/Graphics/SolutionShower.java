package Graphics;

import Algorithm.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.plaf.basic.*;

class SolutionShower
{
	private Interface inter;

	private JPanel inputPanel;
	private JPanel outputPanel;

	private JLabel inputLabel;
	private JLabel outputLabel;

	private JTextField inputField;
	private JTextField outputField;

	SolutionShower(Interface inter)
	{
		this.inter = inter;

		init();
	}

	private void init()
	{
		int width = (int)inter.getRenderSize().getWidth() / 2;
		int height = (int)inter.getRenderSize().getHeight() / 2;

		inputPanel = new JPanel(new BorderLayout());
		outputPanel = new JPanel(new BorderLayout());
		
		inputPanel.setBounds(width - width / 25, height, width, height / 4);
		outputPanel.setBounds(width - width / 25, height + height / 4 + height / 8, width, height / 4);
		
		inputPanel.setOpaque(false);
		inputPanel.setVisible(true);

		outputPanel.setOpaque(false);
		outputPanel.setVisible(true);
		
		inputLabel = new JLabel("Additional Input");
		inputLabel.setBackground(new Color(100, 100, 100));
		inputLabel.setForeground(new Color(230, 230, 230));
		inputLabel.setFont(new Font("Serif", Font.PLAIN, height / 12));
		
		outputLabel = new JLabel("Output");
		outputLabel.setBackground(new Color(100, 100, 100));
		outputLabel.setForeground(new Color(230, 230, 230));
		outputLabel.setFont(new Font("Serif", Font.PLAIN, height / 12));

		inputField = new JTextField();
		inputField.setBackground(new Color(100, 100, 100));
		inputField.setForeground(new Color(230, 230, 230));
		inputField.setFont(new Font("Serif", Font.PLAIN, height / 12));
		
		outputField = new JTextField();
		outputField.setBackground(new Color(100, 100, 100));
		outputField.setForeground(new Color(230, 230, 230));
		outputField.setFont(new Font("Serif", Font.PLAIN, height / 12));

		inputPanel.add(inputLabel, BorderLayout.CENTER);
		inputPanel.add(inputField, BorderLayout.PAGE_END);
		
		outputPanel.add(outputLabel, BorderLayout.CENTER);
		outputPanel.add(outputField, BorderLayout.PAGE_END);
		
		inter.add(inputPanel);
		inter.add(outputPanel);
	}

	void clearInput()
	{
		inputField.setText("");
	}

	String getInput()
	{
		return inputField.getText();
	}

	void displaySolution(String solution)
	{
		outputField.setText(solution);
	}
}
