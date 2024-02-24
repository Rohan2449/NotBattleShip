package src;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(){
        x=0;
        y=0;
    }
    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "" + (char)(65+y) + (x+1);
    }
}
