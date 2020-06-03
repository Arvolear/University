package Graphics;

import Algorithm.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;

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
		JFrame help = new JFrame("Help");
		
		help.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		int width = frame.getSize().width;
		int height = frame.getSize().height;

		help.setSize((int)(width / 1.2), height);
		help.setLocation(frame.getLocation().x + width / 2, frame.getLocation().y);
		help.setResizable(false);

		help.getContentPane().setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(new Color(30, 30, 30));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
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

		JLabel actualHelp = new JLabel();
		actualHelp.setBorder(new EmptyBorder(height / 60, width / 53, height / 40, width / 160));
		actualHelp.setText(
		"<html>" + 
			"<pre>Press \"Open\" button to browse an input file.</pre>" +
			"<br/>" +
			"<pre>Input file format:</pre>" +
			"<pre>&lt V &gt &lt W &gt ... &lt V &gt &lt W &gt</pre>" +
			"<pre>&lt V &gt &lt W &gt ... &lt V &gt &lt W &gt</pre>" +
			"<pre>...</pre>" +
			"<pre>&lt V &gt &lt W &gt ... &lt V &gt &lt W &gt</pre>" +
			"<br/>" +
			"<pre>Where V = vertex, W = weight.</pre>" +
			"<br/>" +
			"<pre>There are 15 calculations available:</pre>" +
			"<pre>A1 - Transition probability from &lt i &gt to &lt j &gt state on &lt m &gt step. </pre>" +
			"<pre>A2 - Probability distridution on &lt m &gt step with the given distibution. </pre>" +
			"<pre>A3 - Expected value &lt m &gt with the given probability distribution. </pre>" +
			"<br/>" +
			"<pre>B1 - Accessibility from the state &lt i &gt state &lt j &gt . </pre>" +
			"<pre>B2 - All accessible states from the state &lt i &gt . </pre>" +
			"<pre>B3 - Significance of the state &lt i &gt . </pre>" +
			"<pre>B4 - Communication between the states &lt i &gt and &lt j &gt . </pre>" +
			"<pre>B5 - All equality classes of communicative states. </pre>" +
			"<pre>B6 - All absorbing states. </pre>" +
			"<br/>" +
			"<pre>C1 - Irreducibility of the chain. Number of communicative classes. </pre>" +
			"<pre>C2 - All significant and insignificant states. </pre>" +
			"<pre>C3 - Chain period. </pre>" +
			"<pre>C4 - All reccurent states. </pre>" +
			"<pre>C5 - All periodical states. </pre>" +
			"<pre>C6 - Theoretical ergodic distribution. Approximate distribution (2, 4, 8). </pre>" +
			"<br/>" +
			"<pre>Enter the values into the \"Additional Input\" field and press one of the</pre>" + 
			"<pre>buttons to start calculation. Expect the output in the \"Output\" field. </pre>" +
		"</html>");
		
		help.add(scrollPane, BorderLayout.CENTER);

		scrollPane.setViewportView(actualHelp);
		help.setVisible(true);
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
