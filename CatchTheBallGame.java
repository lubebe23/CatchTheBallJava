import javax.util.*;

public class CatchTheBallGame extends JFrame {
    private int paddleX = 200;
    private int ballX = 50;
    private int ballY = 50;

    public CatchTheBallGame() {
        setTitle("Catch the Ball Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBall();
                repaint();
            }
        });
        timer.start();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                movePaddle(evt);
            }
        });
        setFocusable(true);
    }

    private void moveBall() {
        ballX += 2;
        if (ballX > getWidth()) {
            ballX = 0;
            ballY = (int) (Math.random() * getHeight());
        }

        if (ballX > paddleX && ballX < paddleX + 60 && ballY > getHeight() - 50) {
            ballY = -10;
        }
    }

    private void movePaddle(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_LEFT && paddleX > 0) {
            paddleX -= 10;
        } else if (key == KeyEvent.VK_RIGHT && paddleX < getWidth() - 60) {
            paddleX += 10;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, 20, 20);

        g.setColor(Color.BLUE);
        g.fillRect(paddleX, getHeight() - 50, 60, 10);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CatchTheBallGame().setVisible(true);
            }
        });
    }
}
