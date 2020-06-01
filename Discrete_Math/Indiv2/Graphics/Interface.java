package Graphics;

import Algorithm.*;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Interface extends JPanel
{
	private Dimension renderSize;
	private JFrame frame;

	private Menu menu;

	private ProblemChooser prolemChooser;
	private InputShower inputShower;

	private Solutor solutor;
	private SolutionShower solutionShower;

	private Solver solver;

	public Interface()
	{
		renderSize = Toolkit.getDefaultToolkit().getScreenSize();
		renderSize.setSize(renderSize.getWidth() / 2, renderSize.getHeight() / 1.5);

		configure();

		solver = new Solver();

		frame = new JFrame("Markov chains solver");

		setLayout(null);

		frame.setSize(renderSize);

		frame.setLocation(100, 50);
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		inputShower = new InputShower(solver, this);
		prolemChooser = new ProblemChooser(solver, this, inputShower);

		solutionShower = new SolutionShower(this);
		solutor = new Solutor(solver, this, solutionShower);
		
		menu = new Menu(frame);
		frame.add(this);
		
		frame.setVisible(true);
	}

	private void configure()
	{
		UIManager.put("OptionPane.background", new Color(30, 30, 30));
		UIManager.put("OptionPane.messageForeground", new Color(230, 230, 230));
		
		UIManager.put("Button.background", new Color(100, 100, 100));
		UIManager.put("Button.foreground", new Color(230, 230, 230));

		UIManager.put("ToggleButton.background", new Color(100, 100, 100));
		UIManager.put("ToggleButton.foreground", new Color(230, 230, 230));

		UIManager.put("ToolBar.background", new Color(30, 30, 30));
		UIManager.put("ToolBar.foreground", new Color(230, 230, 230));

		UIManager.put("Panel.background", new Color(30, 30, 30));
		UIManager.put("Panel.foreground", new Color(230, 230, 230));
		
		UIManager.put("Label.background", new Color(30, 30, 30));
		UIManager.put("Label.foreground", new Color(230, 230, 230));

		UIManager.put("Viewport.background", new Color(30, 30, 30));
		UIManager.put("Viewport.foreground", new Color(230, 230, 230));

		UIManager.put("TextField.background", new Color(30, 30, 30));
		UIManager.put("TextField.foreground", new Color(230, 230, 230));

		UIManager.put("List.background", new Color(30, 30, 30));
		UIManager.put("List.foreground", new Color(230, 230, 230));

		UIManager.put("ComboBox.background", new Color(30, 30, 30));
		UIManager.put("ComboBox.foreground", new Color(230, 230, 230));
		
		UIManager.put("TableHeader.background", new Color(30, 30, 30));
		UIManager.put("TableHeader.foreground", new Color(230, 230, 230));	

		setUIFont(new javax.swing.plaf.FontUIResource("Serif", Font.PLAIN, (int)(renderSize.getHeight() / 45.0)));
	}

	private void setUIFont(javax.swing.plaf.FontUIResource f)
	{
		java.util.Enumeration<?> keys = UIManager.getDefaults().keys();

		while (keys.hasMoreElements()) 
		{
			Object key = keys.nextElement();
			Object value = UIManager.get (key);

			if (value instanceof javax.swing.plaf.FontUIResource)
			{
				UIManager.put(key, f);
			}
		}
	} 

	@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			g.setColor(new Color(30, 30, 30));
			g.fillRect(0, 0, (int)renderSize.getWidth(), (int)renderSize.getHeight()); 
		}

	Dimension getRenderSize()
	{
		return renderSize;
	}

	JFrame getFrame()
	{
		return frame;
	}
}
