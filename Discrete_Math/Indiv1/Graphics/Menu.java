package Graphics;

import Algorithm.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

class Menu implements MenuListener
{
	private JMenuBar menuBar;
	private JMenu helpMenu;

	private JFrame frame;

	Menu(JFrame frame)
	{
		this.frame = frame;

		init();
	}

	private void init()
	{
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(30, 30, 30));
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		helpMenu = new JMenu("Help");
		helpMenu.setBackground(new Color(100, 100, 100));
		helpMenu.setForeground(new Color(230, 230, 230));

		helpMenu.addMenuListener(this);
	
		menuBar.add(helpMenu);

		frame.setJMenuBar(menuBar);
	}

	@Override
	public void menuSelected(MenuEvent event)
	{
		JOptionPane.showConfirmDialog(frame, 
		"<html>" + 
			"<pre>Press \"Open\" button to browse an input file.</pre>" +
			"<pre>Press \"Solve\" button to view the solution of the given problem.</pre>" +
			"<br/>" +
			"<pre>Input file format:</pre>" +
			"<pre>&lt W &gt &lt W &gt ... &lt W &gt &lt P &gt</pre>" +
			"<pre>&lt W &gt &lt W &gt ... &lt W &gt &lt P &gt</pre>" +
			"<pre>...</pre>" +
			"<pre>&lt W &gt &lt W &gt ... &lt W &gt &lt P &gt</pre>" +
			"<pre>&lt C &gt &lt C &gt ... &lt C &gt</pre>" +
			"<br/>" +
			"<pre>Where W = weight, P = producer, C = consumer.</pre>" +
		"</html>", 
		"Help", JOptionPane.DEFAULT_OPTION);
	}

	@Override
	public void menuCanceled(MenuEvent event) 
	{
	}

	@Override
	public void menuDeselected(MenuEvent event) 
	{
	}
}
