import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.io.Serial;

public class Board extends JComponent implements MouseInputListener, ComponentListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int size = 10;
    private Point[][] points;

    private int actualY = 0;

    public Board(int length, int height) {
        initialize(length, height);
        addMouseListener(this);
        addComponentListener(this);
        addMouseMotionListener(this);
        setBackground(Color.WHITE);
        setOpaque(true);
        actualY = 0;
    }

    private void initialize(int length, int height) {
        points = new Point[length][height];
        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y)
                points[x][y] = new Point();
    }

    public void clear() {
        for (Point[] point : points)
            for (Point value : point) {
                value.clear();
            }
        this.repaint();
    }

    private void clearRow(int y) {
        for (Point[] point : points) {
            point[y].clear();
        }
    }

    public void iteration() {
        Point tmp;

        moveRowsDown();

        for (int x = 0; x < points.length; ++x) {
            tmp = points[x][actualY];
            tmp.randomDisappear();
            tmp.accelerate();
            tmp.slowDown(getDistToNextCar(tmp, x, actualY));
            tmp.randomSlowDown();
            tmp.setMoved(false);
        }

        for (int x = 0; x < points.length; ++x) {
            tmp = points[x][actualY];
            tmp.moveCar(points[(x + tmp.getVelocity()) % points.length][actualY]);
        }
        points[0][0].randomAppear();
        this.repaint();
    }

    private int getDistToNextCar(Point tmp, int x, int y) {
        if (!tmp.hasCar()) return 0;
        for (int i = 0; i < Point.MAX_VELOCITY; i++) {
            if (points[(x + i + 1) % points.length][y].hasCar())
                return i;
        }
        return Point.MAX_VELOCITY;
    }

    private void moveRowsDown() {
        for (int y = points[0].length - 1; y > 0; y--) {
            for (Point[] point : points) {
                if (point[y - 1].hasCar())
                    point[y].clicked();
                else
                    point[y].clear();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        g.setColor(Color.GRAY);
        drawNetting(g);
    }

    private void drawNetting(Graphics g) {
        Insets insets = getInsets();
        int firstX = insets.left;
        int firstY = insets.top;
        int lastX = this.getWidth() - insets.right;
        int lastY = this.getHeight() - insets.bottom;

        int x = firstX;
        while (x < lastX) {
            g.drawLine(x, firstY, x, lastY);
            x += 10;
        }

        int y = firstY;
        while (y < lastY) {
            g.drawLine(firstX, y, lastX, y);
            y += 10;
        }

        for (x = 0; x < points.length; ++x) {
            for (y = 0; y < points[x].length; ++y) {
                float a = 1.0F;
                if (points[x][y].hasCar())
                    g.setColor(Color.BLACK);
                else
                    g.setColor(Color.WHITE);
                g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        getCellSize(e);
    }

    public void componentResized(ComponentEvent e) {
        int len = (this.getWidth() / size) + 1;
        int height = (this.getHeight() / size) + 1;
        initialize(len, height);
    }

    public void mouseDragged(MouseEvent e) {
        getCellSize(e);
    }

    private void getCellSize(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x >= 0) && y == 0) {
            points[x][y].clicked();
            this.repaint();
        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

}