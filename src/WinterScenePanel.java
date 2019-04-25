// (c) A+ Computer Science
// www.apluscompsci.com
// Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;

public class WinterScenePanel extends JPanel implements Runnable, KeyListener {

    private List<AbstractShape> shapes;
    private AbstractShape sMan;
    private boolean key;

    public WinterScenePanel() {
        setVisible(true);
        
        shapes = new ArrayList<AbstractShape>();
        for (int i = 0; i < 50; i++) {
            int y = (int) (Math.random() * 600);
            int s = (int) (Math.random() * 30) + 20;
//            shapes.add(new SimpleSnowFlake(i * 14, y, s, s));
//            shapes.add(new FancySnowFlake(i * 14, y, s, s));
            shapes.add(new StormySnowFlake(i * 14, y, s, s, (int)(Math.random() * 2)));
        }
        
        sMan = new SnowMan(400, 350, 200, 150);
        
        this.addKeyListener(this);
        new Thread(this).start();
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        window.setColor(Color.BLUE);
        window.fillRect(0, 0, getWidth(), getHeight());
        window.setColor(Color.WHITE);
        window.drawRect(20, 20, getWidth() - 40, getHeight() - 40);
        window.setFont(new Font("TAHOMA", Font.BOLD, 18));
        window.drawString("MAKE A WINTER SCENE!", 40, 40);

        sMan.draw(window);
        
        if(key == true){
            for (AbstractShape sh : shapes) {
                StormySnowFlake s = (StormySnowFlake) sh;
                s.storm(window);
                
                if (s.getYPos() >= getHeight()) {
                    s.setYPos(0);
                }
                
                if (s.getXPos() >= getWidth()) {
                    s.setXPos(0);
                }
            }
        }
        
        else{
            for (AbstractShape sh : shapes) {
                sh.moveAndDraw(window);
                if (sh.getYPos() >= getHeight()) {
                    sh.setYPos(0);
                }
            }
        }
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            key = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(35);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}
