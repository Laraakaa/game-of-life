package ninja.lars.gameoflife;

public class Cell {
    private boolean isAlive = false;
    private int x;
    private int y;

    public Cell(boolean isAlive, int x, int y) {
        this.isAlive = isAlive;
        this.x = x;
        this.y = y;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
