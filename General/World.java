package VirtualWorldJava.General;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextArea;

import VirtualWorldJava.General.Engine.Board;
import VirtualWorldJava.General.Engine.InputEnum;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Entities;
import VirtualWorldJava.General.Entities.Unique.Human;
import VirtualWorldJava.General.Navigation.*;
import VirtualWorldJava.General.Utilities.Functional.ToKill;
import VirtualWorldJava.General.Engine.Layout;

public class World {

    // #region CONSTUCTOR

    public World() {
        this.organisms = new ArrayList<Organism>();
        this.born = new ArrayList<Organism>();
        this.dead = new ArrayList<Organism>();

        this.organismsC = 0;
        this.start = false;

        this.layout = new Layout(this);
    }

    // #endregion

    // #region PRIVATE_VARIABLES

    Board board;
    Layout layout;

    int organismsC;
    Human player;
    boolean start;

    List<Organism> organisms;
    List<Organism> born;
    List<Organism> dead;

    WorldDirections direction;

    // #endregion

    // #region PRIVATE_FUNCTIONS

    private void Populate(int n) {
        for (int i = 0; i < n; i++) {
            for (Entities entity : Entities.values()) {
                Organism o = entity.Create();
                o.SetAge(this.GetAge());
                o.SetWorldRef(this);
                AddToWorld(o, Navigation.NULL_POINT);
            }
        }

    }

    public void NextTurn() {

        this.Notify("New turn!");

        if (born.size() != 0) {
            for (Organism o : born) {
                organisms.add(o);
            }

            born.clear();
            organisms.sort(null);
        }

        for (Organism o : organisms) {
            if (o.IsAlive() == true) {
                o.Action();
            }
        }

        if (dead.size() != 0) {
            for (Organism o : dead) {
                organisms.remove(o);
            }
            dead.clear();
        }
    }

    private void ClearLegend() {
        JTextArea legend = this.layout.GetLegend();
        legend.setText(null);
    }

    // #endregion

    // #region PUBLIC_FUNCTIONS

    public void Init(int oc) {

        int rows = this.layout.GetHeight();
        int cols = this.layout.GetWidth();

        this.board = new Board(cols, rows);

        this.player = new Human(this.GetAge(), this);
        this.AddToWorld((Organism) player, Navigation.NULL_POINT);

        this.Populate(oc);
    }

    public int GetAge() {
        return ++this.organismsC;
    }

    public void SetAge(int value) {
        this.organismsC = value;
    }

    public Layout GetLayout() {
        return this.layout;
    }

    public void SetStart(boolean value) {
        this.start = value;
    }
    public boolean GetStart() {
        return this.start;
    }
    public boolean GetFinish() {
        return !this.player.IsAlive();
    }

    public void SetInput(InputEnum code) {
        this.player.SetInput(code);
    }

    public void Notify(String s) {
        this.layout.Print(s);
    }

    public void AddToWorld(Organism o, Point p) {
        born.add(o);

        if (p == Navigation.NULL_POINT) {
            this.board.SetAt(o);
        } else {
            this.board.SetAt(p, o);
        }
        this.layout.Update(o);
    }

    public void RemoveFromWorld(Organism o) {
        this.layout.Clear(o.GetLocation());
        this.board.KillAt(o.GetLocation());
        this.dead.add(o);
    }

    public void RemoveFromWorld(String s, Point p, ToKill foo) {
        int x = (p.GetX() - 1 < 0) ? 0 : p.GetX() - 1;
        int yB = (p.GetY() - 1 < 0) ? 0 : p.GetY() - 1;

        int xMax = (p.GetX() + 1 == board.GetRow()) ? p.GetX() : p.GetX() + 1;
        int yMax = (p.GetY() + 1 == board.GetCol()) ? p.GetY() : p.GetY() + 1;

        Organism o;
        Point temp = new Point(0, 0);

        for (; x <= xMax; x++) {
            for (int y = yB; y <= yMax; y++) {
                if (x == p.GetX() && y == p.GetX()) {
                    continue;
                }

                temp.SetX(x);
                temp.SetY(y);

                o = board.GetAt(temp);
                if (o == null) {
                    continue;
                }
                if (foo.kill(o) == false) {
                    continue;
                }

                o.Kill(s);
            }
        }
    }

    public boolean PointValidate(Point p) {
        return this.board.Validate(p);
    }

    public Point SeekForFree(Point p) {
        return this.board.SeekForFree(p);
    }

    public Organism GetAt(Point p) {
        return this.board.GetAt(p);
    }

    public void MoveTo(Point p, Organism o) {
        this.layout.Clear(o.GetLocation());
        this.board.SetAt(p, o);
        this.layout.Update(o);
    }

    public void LegendUpdate(WorldDirections dir) {
        JTextArea legend = this.layout.GetLegend();

        this.ClearLegend();

        legend.append("STATISTICS:\n");
        legend.append("Strength: " + player.GetStrength() + "\n");
        legend.append("Position: " + player.GetLocation().toString() + "\n");

        legend.append("\n");

        if (dir != WorldDirections.DIR_NULL) {
            legend.append("Current direction: " + Navigation.ToString(dir) + "\n");
        }

        legend.append("\n");

        legend.append("Cooldown: " + this.player.GetCooldown() + "\n");
        legend.append("Duration: " + this.player.GetDuration() + "\n");

        if (player.GetDuration() > 0) {
            legend.append("\n");
            legend.append("Player empowered." + "\n");
        }
    }

    public void ClearInput() {
        this.direction = WorldDirections.DIR_NULL;
    }

    public void Save() {

        PrintWriter out;
        try {
            out = new PrintWriter("VirtualWorldJava/General/Resources/save.txt");
        
            //saving
            this.Notify("Saving...");
            //

            //world
            out.printf("%d\n", organismsC);

            //board
            out.printf("%d\n%d\n", board.GetRow(), board.GetCol());

            Point p = new Point(0, 0);

            //organisms
            for (int y = 0; y < board.GetRow(); y++)
            {
                for (int x = 0; x < board.GetCol(); x++) {
                    p.SetX(x);
                    p.SetY(y);
                    Organism o = this.GetAt(p);

                    if (o == null) {
                        out.printf("%d\n", 0);
                    }
                    else {
                        out.printf("%d\n%c\n%d\n%d\n", 1, o.GetToken(), o.GetAge(), o.GetStrength());
                    }
                }
            }

            //born
            out.printf("%d\n", born.size());
            for (Organism o : this.born) {
                if (o == null) {
                    out.printf("%d\n", 0);
                }
                else {
                    out.printf("%d\n%d\n%d\n", 1, o.GetLocation().GetX(), o.GetLocation().GetY());
                }
            }
            //dead
            out.printf("%d\n", dead.size());
            for (Organism o : this.dead) {
                if (o == null) {
                    out.printf("%d\n", 0);
                }
                else {
                    out.printf("%d\n%d\n%d\n", 1, o.GetLocation().GetX(), o.GetLocation().GetY());
                }
            }

            out.printf("%d\n%d", this.player.GetCooldown(), this.player.GetDuration());

            out.close();
            this.Notify("Saving complete!");

        } catch (FileNotFoundException e) {
            assert true;
            e.printStackTrace();
        }  
    }
    public void Load() {
        Scanner in;
        
        try {
            in = new Scanner(new File("VirtualWorldJava/General/Resources/save.txt"));
        
            //saving
            this.Notify("Loading...");
            //


            //world
            this.SetAge(in.nextInt());

            

            //board
            int row = in.nextInt();
            int col = in.nextInt();

            this.layout.Buttons(col, row);

            this.born.clear();
            this.dead.clear();

            int b1, b2, b3, b4;
            char t;

            //organisms
            for (int y = 0; y < board.GetRow(); y++)
            {
                for (int x = 0; x < board.GetCol(); x++) {
                    
                    Point p = new Point(0, 0);
                    p.SetX(x);
                    p.SetY(y);
                    
                    b1 = in.nextInt();

                    if (b1 == 0) {
                        continue;
                    }
                    else {
                        t = in.next().charAt(0);

                        b2 = in.nextInt();
                        b3 = in.nextInt();

                        Organism o = Entities.Create(t);
                        o.SetWorldRef(this);
                        o.SetAge(b2);
                        o.SetStrength(b3);
                        o.SetLocation(p);
                        this.AddToWorld(o, p);
                    }
                }
            }

            //born
            b1 = in.nextInt();
            for (int i = 0; i < b1; i++) {
                b2 = in.nextInt();
                if (b2 == 0) {
                    continue;
                }
                else {
                    b3 = in.nextInt();
                    b4 = in.nextInt();

                    Point p = new Point(b3, b4);

                    born.add(this.GetAt(p));
                }
            }
            //dead
            b1 = in.nextInt();
            for (int i = 0; i < b1; i++) {
                b2 = in.nextInt();
                if (b2 == 0) {
                    continue;
                }
                else {
                    b3 = in.nextInt();
                    b4 = in.nextInt();

                    Point p = new Point(b3, b4);

                    dead.add(this.GetAt(p));
                }
            }

            b1 = in.nextInt();
            b2 = in.nextInt();
            
            this.player.SetActive(b1, b2);

            in.close();
            this.Notify("Loading complete!");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //#endregion

}



