package VirtualWorldJava.General;

import java.util.List;

public class World {

    //#region CONSTUCTOR

    public World(int rows, int cols, int oc) {

        organisms = new List<Organism>;
        born = new List<Organism>;
        dead = new List<Organism>;

    }

    //#endregion

    //#region PRIVATE_VARIABLES

    Board board;
    Layout layout;

    int organismsC;
    Human player;

    List organisms;
    List born;
    List dead;

    //#endregion

    //#region PRIVATE_FUNCTIONS

    private void Populate(int n);
    Organism Create(char c, int a);

    void NextTurn();

    void DrawOutline(int x, int y, int height, int width);
    void DrawLegend();
    void ClearLegend();

    void Save();
    void Load();

    //#endregion

    //#region PUBLIC_FUNCTIONS

    public void LayoutInit(int r, int c);

    public int GetAge();
    public void SetAge(int value);
    public Layout GetLayout();

    public void Start();
    public void Notify(String s);

    public void AddToWorld(Organism o, Point p);
    public void RemoveFromWorld(Organism o);
    public void RemoveFromWorld(String s, Point p, boolean(ToKill)(Organism o));
    
    public boolean PointValidate(Point p);
    public Point SeekForFree(Point p);
    public Organism GetAt(Point p);
    public Organism MoveTo(Point p, Organism o);

    public void Draw();
    public void LegendUpdate(WorldDirections dir, String s);
    public WorldDirections GetInput(WorldDirections dir, Point p);
    public void ClearOutput();

    //#endregion

}