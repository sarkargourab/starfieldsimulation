import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class StarFieldPanel extends JPanel implements Runnable{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_STARS = 1000;

    private Star[] stars = new Star[NUM_STARS];

    private Random random = new Random();

    public StarFieldPanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        for (int i = 0; i < NUM_STARS; i++) {
            stars[i] = new Star(random.nextInt(WIDTH), random.nextInt(HEIGHT), random.nextInt(5)+1);
        }
        new Thread(this).start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (Star star : stars){
            g.fillOval(star.x, star.y, star.size,star.size);
        }
    }

    @Override
    public void run(){
        while (true){
            for (Star star : stars){
                star.y += star.size;
                if (star.y > HEIGHT){
                    star.y = 0;
                    star.x = random.nextInt(WIDTH);
                }
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
