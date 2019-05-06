package VirtualWorldJava.General.Navigation;

public class Point {

    private int x;
	private int y;

	Point(int xValue, int yValue) {
		this.Set(xValue, yValue);
	}

    @Override
	public boolean equals(Object other) {
        if(other instanceof Point) {
            Point p = (Point)other;
            return (this.GetX() == p.GetX() && this.y == p.GetY());
		}
		else {
			return false;
		}


    }
    @Override
	public String toString() {
		return "(" + this.x + " , " + this.y +")";
	}

	public int GetX() {
		return x;
	}
	public void SetX(int value) {
		this.x = value;
	}

	public int GetY() {
		return y;
	}
	public void SetY(int value) {
		this.y = value;
	}

	public void Set(int xValue, int yValue) {
		this.x = xValue;
		this.y = yValue;
	}

}