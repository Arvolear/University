package Graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuterTitleChanger implements ActionListener {
    private JFrame frame;

    public OuterTitleChanger(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        frame.setTitle("Clicked 3");
    }
}
