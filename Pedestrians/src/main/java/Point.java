
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Comparator;


public class Point{

	private static final int SFMAX = 10000;

	public ArrayList<Point> neighbors;
	public static Integer []types ={0,1,2,3};
	public int type;
	public int staticField;
	public boolean isPedestrian;
	boolean blocked = false;

	public Point() {
		type=0;
		staticField = SFMAX;
		neighbors= new ArrayList<Point>();
	}

	public void clear() {
		staticField = SFMAX;
	}

	public boolean calcStaticField() {
		int minStaticField = SFMAX;
		if (!neighbors.isEmpty()){
			minStaticField = neighbors.stream().map(p -> p.staticField).min(Integer::compare).get();
		}
		if (staticField > minStaticField + 1){
			staticField = minStaticField + 1;
			return true;
		}
		return false;
	}

	public void move() {
		if (isPedestrian && !neighbors.isEmpty() && !blocked) {
			Optional<Point> minSFNNeighborOpt = neighbors.stream()
					.filter(a -> !a.isPedestrian && a.type != 1 && !a.blocked)
					.min(Comparator.comparingInt(a -> a.staticField));

			if (minSFNNeighborOpt.isPresent()) {
				Point minSFNNeighbor = minSFNNeighborOpt.get();

				if (minSFNNeighbor.type != 2 && minSFNNeighbor.type != 1) {
					movePedestrianTo(minSFNNeighbor);
				} else if (minSFNNeighbor.type == 2) {
					resetPedestrian();
				}
			}
		}
	}

	private void movePedestrianTo(Point destination) {
		destination.isPedestrian = true;
		destination.type = 3;
		destination.blocked = true;
		resetPedestrian();
	}

	private void resetPedestrian() {
		isPedestrian = false;
		type = 0;
	}

	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}

}