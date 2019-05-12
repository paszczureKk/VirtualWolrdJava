package VirtualWorldJava.General;

import java.util.ArrayList;
import java.util.List;

import VirtualWorldJava.General.Engine.Board;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Entities;
import VirtualWorldJava.General.Entities.Unique.Human;
import VirtualWorldJava.General.Navigation.*;
import VirtualWorldJava.General.Utilities.Functional.ToKill;
import VirtualWorldJava.General.Engine.Layout;

public class World {

    //#region CONSTUCTOR

    public World() {
        this.organisms = new ArrayList<Organism>();
        this.born = new ArrayList<Organism>();
        this.dead = new ArrayList<Organism>();

        this.organismsC = 0;

        this.layout = new Layout(this);
    }

    //#endregion

    //#region PRIVATE_VARIABLES

    Board board;
    Layout layout;

    int organismsC;
    Human player;

    List<Organism> organisms;
    List<Organism> born;
    List<Organism> dead;

    WorldDirections direction;

    //#endregion

    //#region PRIVATE_FUNCTIONS

    private void Populate(int n) {
        for(int i = 0; i < n; i++) {
            for (Entities entity : Entities.values()) {
                Organism o = entity.Create();
                o.SetAge(this.GetAge());
                o.SetWorldRef(this);
                AddToWorld(o, Navigation.NULL_POINT);
            }
        }
        
    }
    private Organism Create(char c, int a) {
        //TO DO
        return null;
    }

    private void NextTurn() {
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

    private void DrawOutline(int x, int y, int height, int width) {
        //TO DO
    }
    private void DrawLegend() {
        //TO DO
    }
    private void ClearLegend() {
        //TO DO
    }

    

    //#endregion

    //#region PUBLIC_FUNCTIONS

    public void Init(int rows, int cols, int oc) {

        this.board = new Board(rows, cols, this);

        this.player = new Human(this.GetAge(), this);
        this.AddToWorld((Organism)player, Navigation.NULL_POINT);

        this.Populate(oc);

        //this.DrawOutline(layout.GetBoardX() - 1, layout.GetBoardY() - 1, board.GetRow() + 2, board.GetCol() + 2);
        this.DrawLegend();

        this.Start();
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

    public void Start() {
        while (player.IsAlive()) {
            this.NextTurn();
            this.ClearLegend();
        }
        this.ClearOutput();
        this.Draw();
        this.Notify("You died. ");
    }
    public void Notify(String s) {
        //TO DO
    }

    public void AddToWorld(Organism o, Point p) {
        born.add(o);

        if (p == Navigation.NULL_POINT) {
            this.board.SetAt(o);
        }
        else {
            this.board.SetAt(p, o);
        }
        this.layout.Update(o);
    }
    public void RemoveFromWorld(Organism o) {
        this.board.KillAt(o.GetLocation());
        this.layout.Update(o);
	    this.dead.add(o);
    }
    public void RemoveFromWorld(String s, Point p, ToKill foo) {
        int x = (p.GetX() - 1 < 0) ? 0 : p.GetX() - 1;
        int yB = (p.GetY() - 1 < 0) ? 0 : p.GetY() - 1;

        int xMax = (p.GetX() + 1 == board.GetRow()) ? p.GetX() : p.GetX() + 1;
        int yMax = (p.GetY() + 1 == board.GetCol()) ? p.GetY() : p.GetY() + 1;

        Point temp = new Point(0, 0);
        Organism o;

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
                this.layout.Update(o);
            }
        }
    }
    
    public boolean PointValidate(Point p) {
        return board.Validate(p);
    }
    public Point SeekForFree(Point p) {
        return board.SeekForFree(p);
    }
    public Organism GetAt(Point p) {
        return board.GetAt(p);
    }
    public Organism MoveTo(Point p, Organism o) {
        Organism org = board.SetAt(p, o);
        this.layout.Update(o);
        this.layout.Clear(p);

        return org;
    }

    public void Draw() {
        layout.Draw();
    }
    public void LegendUpdate(WorldDirections dir, String s) {
        //TO DO
    }
    public void ClearInput() {
        this.direction = WorldDirections.DIR_NULL;
    }
    public void ClearOutput() {
        //TO DO
    }

    public void Save() {
        //TO DO
    }
    public void Load() {
        //TO DO
    }

    //#endregion

}

