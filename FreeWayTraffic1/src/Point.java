public class Point {
    public final static int MAX_VELOCITY = 5;
    final static double SLOW_DOWN_CHANCE = 0.1;
    final static double APPEAR_CHANCE = 0.1;
    final static double DISAPPEAR_CHANCE = 0.01;
    private boolean hasMoved;
    private int velocity;
    private boolean car;

    public Point() {
        hasMoved = false;
        velocity = (int) (Math.random() * 5);
        car = false;
        clear();
    }

    public boolean hasCar() {
        return car;
    }

    public int getVelocity() {
        return velocity;
    }

    public boolean getMoved() {
        return hasMoved;
    }

    public void setMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void clicked() {
        if (!car) {
            this.car = true;
        }
    }

    public void randomAppear() {
        if (!car && Math.random() <= APPEAR_CHANCE) {
            car = true;
        }
    }

    public void clear() {
        velocity = 0;
        car = false;
    }

    public void accelerate() {
        if (car && velocity < MAX_VELOCITY) {
            velocity++;
        }
    }

    public void slowDown(int distanceToNextCar) {
        if (car && distanceToNextCar < velocity) {
            velocity = distanceToNextCar;
        }
    }

    public void randomSlowDown() {
        if (car && Math.random() <= SLOW_DOWN_CHANCE && velocity > 0) {
            velocity--;
        }
    }

    public void randomDisappear() {
        if (Math.random() <= DISAPPEAR_CHANCE) {
            car = false;
        }
    }

    public void moveCar(Point newPoint) {
        if (car && !hasMoved && velocity > 0) {
            newPoint.car = true;
            newPoint.velocity = velocity;
            newPoint.hasMoved = true;
            clear();
        }
    }

}