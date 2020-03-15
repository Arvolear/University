package Graphics;

import Algorithm.*;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Interface extends JPanel
{
	private Dimension renderSize;
	private JFrame frame;

	private ProblemChooser prolemChooser;
	private InputShower inputShower;

	private Solver solver;

	public Interface()
	{
		solver = new Solver();

		frame = new JFrame("Transport problem solver");

		renderSize = Toolkit.getDefaultToolkit().getScreenSize();
		renderSize.setSize(renderSize.getWidth() / 2, renderSize.getHeight() / 1.5);

		setLayout(null);

		frame.setSize(renderSize);

		frame.setLocation(100, 50);
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		inputShower = new InputShower(solver, this);
		prolemChooser = new ProblemChooser(solver, this, inputShower);
		
		frame.add(this);
		
		frame.setVisible(true);
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
}
