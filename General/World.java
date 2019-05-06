package VirtualWorldJava.General;

import java.util.List;

import VirtualWorldJava.General.Engine.Board;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Entities;
import VirtualWorldJava.General.Entities.Unique.Human;
import VirtualWorldJava.General.Navigation.*;

public class World {

    //#region CONSTUCTOR

    public World(int rows, int cols, int oc) {

        organisms = new List<Organism>();
        born = new List<Organism>();
        dead = new List<Organism>();

        organismsC = 0;

        //layout = new Layout;
        this.LayoutInit(rows, cols);

        board = new Board(rows, cols, this);

        player = new Human(this.GetAge(), this);
        this.AddToWorld((Organism)player, Navigation.NULL_POINT);

        this.Populate(oc);

        //this.DrawOutline(layout.GetBoardX() - 1, layout.GetBoardY() - 1, board.GetRow() + 2, board.GetCol() + 2);
        this.DrawLegend();

    }

    //#endregion

    //#region PRIVATE_VARIABLES

    Board board;
    //Layout layout;

    int organismsC;
    Human player;

    List<Organism> organisms;
    List<Organism> born;
    List<Organism> dead;

    //#endregion

    //#region PRIVATE_FUNCTIONS

    private void Populate(int n) {
        //TO DO
    }
    private Organism Create(char c, int a) {
        //TO DO
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

    private void Save() {
        //TO DO
    }
    private void Load() {
        //TO DO
    }

    //#endregion

    //#region PUBLIC_FUNCTIONS

    public void LayoutInit(int r, int c) {

    }

    public int GetAge() {
        return ++this.organismsC;
    }
    public void SetAge(int value) {
        this.organismsC = value;
    }
    /*public Layout GetLayout() {
        return this.layout;
    }*/

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
        born.push_back(o);

        if (p == Navigation.NULL_POINT) {
            this.board.SetAt(o, Navigation.NULL_POINT);
        }
        else {
            this.board.SetAt(p, o);
        }
    }
    public void RemoveFromWorld(Organism o) {
        this.board.KillAt(o.GetLocation());
	    this.dead.push_back(o);
    }
    public void RemoveFromWorld(String s, Point p, boolean(ToKill)(Organism o)) {
        int x = (p.x - 1 < 0) ? 0 : p.x - 1;
        int yB = (p.y - 1 < 0) ? 0 : p.y - 1;

        int xMax = (p.x + 1 == board.GetRow()) ? p.x : p.x + 1;
        int yMax = (p.y + 1 == board.GetCol()) ? p.y : p.y + 1;

        Point temp;
        Organism o;

        for (; x <= xMax; x++) {
            for (int y = yB; y <= yMax; y++) {
                if (x == p.x && y == p.y) {
                    continue;
                }

                temp.x = x;
                temp.y = y;

                o = board.GetAt(temp);
                if (o == null) {
                    continue;
                }
                if (ToKill(o) == false) {
                    continue;
                }

                o.Kill(s);
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
	    return board.SetAt(p, o);
    }

    public void Draw() {
        board.Draw();
    }
    public void LegendUpdate(WorldDirections dir, String s) {
        //TO DO
    }
    public WorldDirections GetInput(WorldDirections dir, Point p) {
        //TO DO
    }
    public void ClearOutput() {
        //TO DO
    }

    //#endregion

}