import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class GUI extends JPanel implements ActionListener, ChangeListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Timer timer;
    private final JFrame frame;
    private final int maxDelay = 500;
    private Board board;
    private JButton start;
    private JButton clear;
    private JSlider pred;
    private int iterNum = 0;
    private boolean running = false;

    public GUI(JFrame jf) {
        frame = jf;
        int initDelay = 100;
        timer = new Timer(initDelay, this);
        timer.stop();
    }

    public void initialize(Container container) {
        container.setLayout(new BorderLayout());
        container.setSize(new Dimension(1024, 768));

        JPanel buttonPanel = new JPanel();

        start = new JButton("Start");
        start.setActionCommand("Start");
        start.addActionListener(this);

        clear = new JButton("Clear");
        clear.setActionCommand("clear");
        clear.addActionListener(this);

        pred = new JSlider();
        pred.setMinimum(0);
        pred.setMaximum(maxDelay);
        pred.addChangeListener(this);
        pred.setValue(maxDelay - timer.getDelay());

        buttonPanel.add(start);
        buttonPanel.add(clear);
        buttonPanel.add(pred);

        board = new Board(1024, 768 - buttonPanel.getHeight());
        container.add(board, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(timer)) {
            iterNum++;
            frame.setTitle("Freeway Traffic simulation (" + iterNum + " iteration)");
            board.iteration();
        } else {
            String command = e.getActionCommand();
            if (command.equals("Start")) {
                if (!running) {
                    timer.start();
                    start.setText("Pause");
                } else {
                    timer.stop();
                    start.setText("Start");
                }
                running = !running;
                clear.setEnabled(true);

            } else if (command.equals("clear")) {
                iterNum = 0;
                timer.stop();
                start.setEnabled(true);
                board.clear();
                frame.setTitle("Cellular Automata Toolbox");
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        timer.setDelay(maxDelay - pred.getValue());
    }
}