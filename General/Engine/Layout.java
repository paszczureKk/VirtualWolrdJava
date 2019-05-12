package VirtualWorldJava.General.Engine;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Navigation.Point;


public class Layout {

    public Layout(World w) {

        this.width = 5;
        this.height = 5;
        this.world = w;
        
        this.buttons = new Vector<JButton>();

        JFrame jframe = new JFrame("Welcome");
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new FlowLayout());

        jframe.setSize(600, 100);

        JMenuBar jmenubar = new JMenuBar();
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                world.Load();
            }
        });
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                world.Save();
            }
        });
        save.setEnabled(false);

        JMenu fMenu = new JMenu("File");
        fMenu.add(load);
        fMenu.add(save);

        jmenubar.add(fMenu);

        jframe.setJMenuBar(jmenubar);

        JLabel info = new JLabel("Insert board bounds.");

        jpanel.add(info);

        JTextField jwidth = new JTextField(10);
        jwidth.setText(Integer.toString(this.width));
        jwidth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                width = Integer.parseInt(jwidth.getText());
            }
        });
        JTextField jheight = new JTextField(10);
        jheight.setText(Integer.toString(this.height));
        jheight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                height = Integer.parseInt(jheight.getText());
            }
        });

        jpanel.add(jwidth);
        jpanel.add(jheight);

        JButton jbtn = new JButton("Done");
        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel jpnlOld = jpanel;
                jpnlOld.setVisible(false);

                jframe.setName("Virtual World Java, Daniel Szynszecki 175683");
                JPanel jpanel = new JPanel();

                jpanel.setLayout(new GridLayout(width, height));
                jframe.setSize(1200, 800);

                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                ActionListener al = new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getActionCommand() == "null") {

                            if(e.getSource() instanceof JButton) {

                                JButton btn = (JButton)e.getSource();
                                //create menu
                            }
                            else {
                                //show tooltip
                            }
                        }
                    }
                };

                for(int y = 0; y < height; y++) {
                    for(int x = 0; x < width; x++) {
                        JButton jbtn = new JButton(Layout.backgroundTile);
                        jbtn.setSize(multiplier, multiplier);
                        jbtn.setBorder(BorderFactory.createEmptyBorder());
                        jbtn.setContentAreaFilled(false);
                        jbtn.setActionCommand("null");
                        jbtn.addActionListener(al);
                        jpanel.add(jbtn);

                        buttons.add(jbtn);
                    }
                }

                JScrollPane jScrollPane = new JScrollPane(jpanel);
                jframe.add(jScrollPane, BorderLayout.CENTER);
                save.setEnabled(true);

                jframe.remove(jpnlOld);

                world.Init(width, height, 2);
            }
        });

        jpanel.add(jbtn);
        jframe.add(jpanel);

        jframe.setVisible(true);

    }

    private int width;
    private int height;

    private World world;

    private final int multiplier = 140;
    private static final ImageIcon backgroundTile = new ImageIcon("VirtualWorldJava/General/Resources/background.png");

    private Vector<JButton> buttons;

    private int GetIndex(Point p) {
        return p.GetY() * this.width + p.GetX();
    }

    public void Update(Organism o) {
        JButton jbtn = buttons.get(GetIndex(o.GetLocation()));

        jbtn.setIcon(o.GetImage());
        jbtn.setActionCommand(o.toString());
    }
    public void Clear(Point p) {
        JButton jbtn = buttons.get(GetIndex(p));

        jbtn.setIcon(Layout.backgroundTile);
        jbtn.setActionCommand("null");
    }

    public void Draw() {
        //to do
    }
    
}