package VirtualWorldJava.General.Engine;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Entities;
import VirtualWorldJava.General.Navigation.Point;

public class Layout {

    public Layout(World w) {

        this.width = 5;
        this.height = 5;
        this.world = w;

        this.buttons = new Vector<JButton>();

        this.jframe = new JFrame("Welcome");
        this.jframe.setFocusable(true);
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new FlowLayout());

        this.jframe.setSize(600, 100);

        JMenuBar jmenubar = new JMenuBar();
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.Load();
            }
        });
        this.save = new JMenuItem("Save");
        this.save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.Save();
            }
        });
        this.save.setEnabled(false);

        JMenu fMenu = new JMenu("File");
        fMenu.add(load);
        fMenu.add(save);

        jmenubar.add(fMenu);

        this.jframe.setJMenuBar(jmenubar);

        JLabel info = new JLabel("Insert board bounds.");

        jpanel.add(info);

        JTextField jwidth = new JTextField(10);
        jwidth.setText(Integer.toString(this.width));
        JTextField jheight = new JTextField(10);
        jheight.setText(Integer.toString(this.height));

        jpanel.add(jwidth);
        jpanel.add(jheight);

        JButton jbtn = new JButton("Done");
        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jframe.remove(jpanel);
                Build(Integer.parseInt(jheight.getText()), Integer.parseInt(jwidth.getText()));

            }
        });

        jpanel.add(jbtn);
        this.jframe.add(jpanel);

        this.jframe.setVisible(true);

    }

    private int width;
    private int height;

    private World world;
    private JTextArea console;
    private JTextArea legend;

    private JFrame jframe;
    private JMenuItem save;
    private JPanel jbuttons;

    private ActionListener al;

    private final int multiplier = 140;
    private static final ImageIcon backgroundTile = new ImageIcon("VirtualWorldJava/General/Resources/background.png");

    private Vector<JButton> buttons;

    private int GetIndex(Point p) {
        return p.GetY() * this.width + p.GetX();
    }

    private Point GetButtonIndex(JButton b) {
        Point p = new Point(0, 0);
        int index = this.buttons.indexOf(b);

        p.SetX(index % this.width);
        p.SetY(index / this.width);

        return p;
    }

    public void Update(Organism o) {
        JButton jbtn = buttons.get(GetIndex(o.GetLocation()));

        jbtn.setIcon(o.GetImage());
        jbtn.setActionCommand(o.toString());
    }

    public void Print(String s) {
        this.console.append(s + "\n");
    }

    public void Clear(Point p) {
        JButton jbtn = buttons.get(GetIndex(p));

        jbtn.setIcon(Layout.backgroundTile);
        jbtn.setActionCommand("null");
    }

    public JTextArea GetLegend() {
        return this.legend;
    }

    public int GetWidth() {
        return this.width;
    }

    public int GetHeight() {
        return this.height;
    }

    private void Build(int r, int c) {

        this.width = c;
        this.height = r;
        this.buttons.clear();

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(true);

        JPanel mcontainer = new JPanel();
        mcontainer.setLayout(new BoxLayout(mcontainer, BoxLayout.X_AXIS));
        mcontainer.setOpaque(true);

        this.jframe.setName("Virtual World Java, Daniel Szynszecki 175683");
        this.jbuttons = new JPanel();

        this.jbuttons.setLayout(new GridLayout(width, height));
        this.jframe.setSize(1200, 800);
        this.jframe.setLayout(new BorderLayout());

        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() instanceof JButton) {

                    JButton btn = (JButton) e.getSource();
                    JPopupMenu jpu = new JPopupMenu();

                    btn.addFocusListener(new FocusListener() {

                        @Override
                        public void focusLost(FocusEvent e) {
                            jpu.setVisible(false);
                        }

                        @Override
                        public void focusGained(FocusEvent e) {
                        }
                    });

                    if (e.getActionCommand() == "null") {

                        for (Entities entity : Entities.values()) {
                            JMenuItem jmi = new JMenuItem(entity.toString());
                            jmi.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Organism o = entity.Create();
                                    o.SetAge(world.GetAge());
                                    o.SetWorldRef(world);
                                    world.AddToWorld(o, GetButtonIndex(btn));
                                    jpu.setVisible(false);
                                }
                            });
                            jpu.add(jmi);
                        }
                    } else {
                        jpu.add(new JMenuItem(e.getActionCommand()));
                    }

                    jpu.setVisible(true);
                    jpu.setLocation(btn.getBounds().x, btn.getBounds().y);

                }
            }
        };

        Buttons(this.width, this.height);

        JScrollPane jScrollPane = new JScrollPane(this.jbuttons);
        mcontainer.add(jScrollPane, BorderLayout.CENTER);
        this.save.setEnabled(true);

        JTextArea mtextArea = new JTextArea(15, 50);
        this.legend = mtextArea;
        mtextArea.setWrapStyleWord(true);
        mtextArea.setEditable(false);
        mtextArea.setFont(Font.getFont(Font.SANS_SERIF));
        JScrollPane mscroller = new JScrollPane(mtextArea);
        mscroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mscroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mcontainer.add(mscroller);

        container.add(mcontainer);

        JTextArea textArea = new JTextArea(15, 50);
        this.console = textArea;
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(Font.getFont(Font.SANS_SERIF));
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        container.add(scroller);
        this.jframe.add(BorderLayout.CENTER, container);
        this.jframe.pack();

        this.jframe.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int c = e.getKeyCode();
        
                switch (c) {
                    case KeyEvent.VK_LEFT:
                        world.SetInput(InputEnum.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        world.SetInput(InputEnum.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        world.SetInput(InputEnum.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        world.SetInput(InputEnum.DOWN);
                        break;
                    case KeyEvent.VK_ENTER:
                        world.SetInput(InputEnum.ENTER);
                        break;
                    case KeyEvent.VK_SPACE:
                        world.SetInput(InputEnum.SPACE);
                        break;
        
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        this.world.SetStart(true);
    }

    public void Buttons(int cols, int rows) {
        this.jbuttons.removeAll();
        this.buttons.clear();
        this.jbuttons.setLayout(new GridLayout(rows, cols));
        
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JButton jbtn = new JButton(Layout.backgroundTile);
                jbtn.setSize(multiplier, multiplier);
                jbtn.setBorder(BorderFactory.createEmptyBorder());
                jbtn.setContentAreaFilled(false);
                jbtn.setActionCommand("null");
                jbtn.addActionListener(this.al);
                jbtn.setLocation(x * this.multiplier, y * this.multiplier);
                this.jbuttons.add(jbtn);

                this.buttons.add(jbtn);
            }
        }

        this.jbuttons.repaint();
    }
    
}