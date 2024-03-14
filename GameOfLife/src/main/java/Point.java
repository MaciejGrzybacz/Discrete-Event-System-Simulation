import java.util.ArrayList;

public class Point {
    private final ArrayList<Point> neighbors;
    private int currentState;
    private int nextState;
    private final int numStates = 6;

    public Point() {
        currentState = 0;
        nextState = 0;
        neighbors = new ArrayList<Point>();
    }

    public void clicked() {
        currentState = (++currentState) % numStates;
    }

    public int getState() {
        return currentState;
    }

    public void setState(int s) {
        currentState = s;
    }

    public void calculateNewState() {
        int liveNeighbours = countNeighburs();

//        // 23/3
//        if (this.currentState == 6 && liveNeighbours == 3) {
//            nextState = 1;
//        } else if (this.currentState < 5 && (liveNeighbours == 2 || liveNeighbours == 3)) {
//            nextState = currentState + 1;
//        } else if (this.currentState == 6 || (liveNeighbours < 2 || liveNeighbours > 3)) {
//            nextState = 6;
//        }

//
//         //2345/45678
//        if (this.currentState == 6 && liveNeighbours >3) {
//            nextState = 1;
//        } else if (this.currentState < 5 && (liveNeighbours >=2 && liveNeighbours <= 5)) {
//            nextState = currentState + 1;
//        } else if (this.currentState == 6 || (liveNeighbours < 2 || liveNeighbours > 5 )) {
//            nextState = 6;
//        }

            //45678/3
//        if (this.currentState == 6 && liveNeighbours == 3) {
//            nextState = 1;
//        } else if (this.currentState < 5 && (liveNeighbours >=4 && liveNeighbours <= 8)) {
//            nextState = currentState + 1;
//        } else if (this.currentState == 6 || (liveNeighbours <4 )) {
//            nextState = 6;
//        }

        //rain
        if(this.currentState>0) {
            nextState=currentState-1;
        }
        if(!this.neighbors.isEmpty()) {
            if (this.currentState == 0 && this.neighbors.get(0).getState() > 0) {
                this.nextState = 6;
            }
        }
    }

    public void changeState() {
        currentState = nextState;
    }

    public void addNeighbor(Point nei) {
        neighbors.add(nei);
    }

    //TODO: write method counting all active neighbors of THIS point
    public int countNeighburs() {
        int count = 0;
        for (Point n : this.neighbors) {
            if (n.getState() != 6) {
                count++;
            }
        }
        return count;
    }

    public void drop(){
        double chance = Math.random();
        if(chance<0.03) {
            setState(6);
        }
    }

}
