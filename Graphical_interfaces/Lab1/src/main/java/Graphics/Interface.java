package Graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Interface extends JPanel implements ActionListener {
    private Dimension renderSize;
    private JFrame frame;

    private JPanel panel;
    private JButton button;

    private OuterTitleChanger outerTitleChanger;

    class TitleChanger implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Interface.this.frame.setTitle("Clicked 3");
        }
    }

    public Interface() {
        renderSize = Toolkit.getDefaultToolkit().getScreenSize();
        renderSize.setSize(renderSize.getWidth() / 2, renderSize.getHeight() / 1.5);

        setLayout(null);

        panel = new JPanel(new BorderLayout());
        panel.setBounds((int)(renderSize.getWidth() / 2) - 150, (int)(renderSize.getHeight() / 1.5 / 2), 300, 300);

        frame = new JFrame("Unnamed");

        frame.setSize(renderSize);

        frame.setLocation(100, 50);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(this);
        frame.setVisible(true);

        button = new JButton("button");

        setDifferentActionListeners();

        button.setBackground(new Color(100, 100, 100));
        button.setForeground(new Color(230, 230, 230));
        button.setFont(new Font("Arial", Font.PLAIN, 40));

        panel.add(button, BorderLayout.CENTER);

        add(panel);
    }

    private void setDifferentActionListeners() {
        outerTitleChanger = new OuterTitleChanger(frame);

        button.addActionListener(this);
        button.addActionListener(actionEvent -> frame.setTitle("Clicked 2"));
        button.addActionListener(new TitleChanger());
        button.addActionListener(outerTitleChanger);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(30, 30, 30));
        g.fillRect(0, 0, (int) renderSize.getWidth(), (int) renderSize.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        frame.setTitle("Clicked 1");
    }
}
