package VirtualWorldJava.General.Navigation;

import VirtualWorldJava.General.Utilities.Utilities;

public class Navigation {

    public final static Point NULL_POINT = new Point(-1, -1);
    
	public static Point Translate(Point p, WorldDirections dir) {
		if (dir == WorldDirections.DIR_NULL) {
            dir = WorldDirections.values()[Utilities.random(0, WorldDirections.DIRECTIONS_COUNT.ordinal())];
		}

        Point point = new Point(p.GetX(), p.GetY());
        
        int x = point.GetX();
        int y = point.GetY();

		switch (dir) {
		case NORTH:
			y--;
			break;
		case EAST:
			x++;
			break;
		case SOUTH:
			y++;
			break;
		case WEST:
			x--;
            break;
        default:
            break;
        }

        point.Set(x, y);
		return point;
	}
	public static String ToString(WorldDirections dir) {

		switch (dir) {
			case NORTH:
				return "North";
			case EAST:
				return "East ";
			case SOUTH:
				return "South";
			case WEST:
				return "West ";
			default:
				return "";
			}
	}
}