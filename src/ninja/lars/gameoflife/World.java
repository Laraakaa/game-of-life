package ninja.lars.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private int size;

    private Cell[][] data;

    private Random random = new Random();
    private World nextGen;

    public World(int size) {
        this.size = size;
        this.data = new Cell[size][size];
    }

    public void init(float probability) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = new Cell(getRandomBoolean(probability), i, j);
            }
        }
    }

    public void show() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(data[i][j].isAlive() ? "X " : "O ");
            }
            System.out.print("\n");
        }
    }

    public World getNextGeneration() {
        // Generate a new generation, copy the current world
        nextGen = new World(this.size);

        // Copy to next generation, appply rules
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = get(i, j);
                Cell newCell = new Cell(cell.isAlive(), cell.getX(), cell.getY());

                if (cell.isAlive()) {
                    // jede lebendige Zelle, die weniger als zwei lebendige Nachbarn hat, stirbt an Einsamkeit
                    if (getNumberOfNeighbors(cell) < 2) {
                        newCell.setAlive(false);
                        //System.out.println("A cell died because it has less than 2 neighbors (x:" + newCell.getX() + ", y:" + newCell.getY() + ")");
                    }
                    if (getNumberOfNeighbors(cell) > 3) {
                        newCell.setAlive(false);
                        //System.out.println("A cell died because it has more than 2 neighbors (x:" + newCell.getX() + ", y:" + newCell.getY() + ")");
                    }
                } else {
                    if (getNumberOfNeighbors(cell) == 3) {
                        newCell.setAlive(true);
                        //System.out.println("A cell was revived because it has 3 neighbors (x:" + newCell.getX() + ", y:" + newCell.getY() + ")");
                    }
                }

                nextGen.data[i][j] = newCell;
            }
        }
        return nextGen;
    }

    private boolean getRandomBoolean(float p){
        return random.nextFloat() < p;
    }

    private int getNumberOfNeighbors(Cell cell) {
        return (int) getNeighbors(cell).stream().filter(Cell::isAlive).count();
    }

    private List<Cell> getNeighbors(Cell cell) {
        ArrayList<Cell> cells = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                Cell nextCell = getRelative(cell, i, j);
                if (nextCell != null) {
                    cells.add(nextCell);
                }
            }
        }
        return cells;
    }

    private Cell getRelative(Cell origin, int xDiff, int yDiff) {
        int newX = origin.getX() + xDiff;
        int newY = origin.getY() + yDiff;

        return get(newX, newY);
    }

    public Cell get(int x, int y) {
        try {
            return data[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
