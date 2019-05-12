package VirtualWorldJava.General.Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import VirtualWorldJava.General.Entities.Unique.Human;
import VirtualWorldJava.General.Engine.InputEnum;

public class InputHandler extends JPanel implements ActionListener, KeyListener {

    public InputHandler(Human h) {
        this.player = h;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        
        System.out.println("Taking key code..");

        switch (c) {
            case KeyEvent.VK_LEFT:
                this.player.SetInput(InputEnum.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                this.player.SetInput(InputEnum.RIGHT);
                break;
            case KeyEvent.VK_UP:
                this.player.SetInput(InputEnum.UP);
                break;
            case KeyEvent.VK_DOWN:
                this.player.SetInput(InputEnum.DOWN);
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Player control..");
                this.player.SetInput(InputEnum.ENTER);
                this.notify();
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Player control..");
                this.player.SetInput(InputEnum.SPACE);
                this.notify();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private Human player;
}