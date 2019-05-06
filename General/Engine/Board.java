package VirtualWorldJava.General.Engine;

import java.util.Vector;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Navigation.Navigation;
import VirtualWorldJava.General.Navigation.Point;
import VirtualWorldJava.General.Utilities.Utilities;

public class Board {

    public Board(int r, int c, World w) {
        this.row = r;
        this.col = c;
        this.world = w;

        this.organisms = new Organism[r * c];

        for (int i = 0; i < this.row * this.col; i++) {
            this.organisms[i] = null;
        }
    }

    public int GetRow() {
        return this.row;
    }

    public int GetCol() {
        return this.col;
    }

    public boolean Validate(Point p) {
        if (p.GetX() < 0 || p.GetY() < 0 || p.GetX() >= this.row || p.GetY() >= this.col) {
            return false;
        } else {
            return true;
        }
    }

    public Point SeekForFree(Point p) {
        seekBuffer.clear();

        int x = (p.GetX() - 1 < 0) ? 0 : p.GetX() - 1;
        int y = (p.GetY() - 1 < 0) ? 0 : p.GetY() - 1;

        int xMax = (p.GetX() + 1 == row) ? p.GetX() : p.GetX() + 1;
        int yMax = (p.GetY() + 1 == col) ? p.GetY() : p.GetY() + 1;

        Point temp = new Point(0, 0);

        for (; x <= xMax; x++) {
            for (; y <= yMax; y++) {
                if (x == p.GetX() && y == p.GetY()) {
                    continue;
                }

                temp.SetX(x);
                temp.SetY(y);

                if (this.organisms[GetIndex(temp)] == null) {
                    seekBuffer.add(temp);
                }
            }
        }

        if (seekBuffer.size() == 0) {
            return Navigation.NULL_POINT;
        }

        return seekBuffer.get(Utilities.random(0, seekBuffer.size()));
    }

    public void SetAt(Organism o) {
        seekBuffer.clear();

        Point temp = new Point(0, 0);
    
        for (int x = 0; x < this.row; x++) {
            for (int y = 0; y < this.col; y++) {
    
                temp.SetX(x);
                temp.SetY(y);
    
                if (this.organisms[this.GetIndex(temp)] == null) {
                    seekBuffer.add(temp);
                }
            }
        }
    
        if (seekBuffer.size() == 0) {
            return;
        }
    
        if(!(o.GetLocation() == Navigation.NULL_POINT)) {
            this.organisms[this.GetIndex(o.GetLocation())] = null;
        }
    
        temp = seekBuffer.get(Utilities.random(0, seekBuffer.size()));
        o.SetLocation(temp);
        this.organisms[GetIndex(temp)] = o;
    }
    public Organism SetAt(Point p, Organism organism) {
        if (this.Validate(p) == false) {
            return null;
        }
    
        Organism o = this.GetAt(p);
        if (o == null) {
            if (!(organism.GetLocation() == Navigation.NULL_POINT)) {
                this.organisms[this.GetIndex(organism.GetLocation())] = null;
            }
            organism.SetLocation(p);
            this.organisms[GetIndex(p)] = organism;
        }
        
        return o;
    }

    public void KillAt(Point p) {
        this.organisms[this.GetIndex(p)] = null;
    }

    public Organism GetAt(Point p) {
        return this.organisms[this.GetIndex(p)];
    }

    public void Draw() {
        //to do
    }

    private int row;
    private int col;
    private Organism[] organisms;

    private World world;

    private Vector<Point> seekBuffer;

    private int GetIndex(Point p) {
        return p.GetY() * row + p.GetX();
    }

};