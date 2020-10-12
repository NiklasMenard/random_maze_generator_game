package random_maze_generator_game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

//Class that generates the maze randomly. Algorithm removes walls randomly from
//neighbouring cells. When all neighbouring cells have been visited it goes back
//to a cell that has not yet been visited and continues from there (DFS)
// Algorithm stops when all cells have been visited.
public class MazeBuilder {

    private static final int gridscale = 40;
    private final int width;
    private final int height;
    private final Cell[][] cell_array;
    private final Stack<Cell> cell_stack;
    private Cell current;
    private final Cell end;
    private final Random rand;


    public MazeBuilder(int width, int height) {


        this.width = width / gridscale;
        this.height = height / gridscale;

        cell_stack = new Stack<>();
        cell_array = new Cell[this.width][this.height];
        rand = new Random();

        for (int row = 0; row < cell_array.length; row++) {
            for (int column = 0; column < cell_array[row].length; column++) {
                cell_array[row][column] = new Cell(row, column);
            }
        }

        current = cell_array[0][0];
        current.setVisited(true);

        end = cell_array[this.width - 1][this.height - 1];
        end.setEnd(true);

    }

    public void drawMaze() {

        Cell next = checkNeighbours();
        if (next != null) {

            next.setVisited(true);
            cell_stack.push(current);
            removeWall(current, next);
            current = next;

        } else if (cell_stack.size() > 0) {
            current = cell_stack.pop();
        }
    }

    public Cell checkNeighbours() {

        ArrayList<Cell> neighbours = new ArrayList<>();

        int current_x = current.getxCoor() / gridscale;
        int current_y = current.getyCoor() / gridscale;

        Cell top = null;
        Cell right = null;
        Cell bottom = null;
        Cell left = null;

        if (inBounds(current_x, current_y - 1)) {
            top = cell_array[current_x][current_y - 1];
        }

        if (inBounds(current_x + 1, current_y)) {
            right = cell_array[current_x + 1][current_y];
        }

        if (inBounds(current_x, current_y + 1)) {
            bottom = cell_array[current_x][current_y + 1];
        }

        if (inBounds(current_x - 1, current_y)) {
            left = cell_array[current_x - 1][current_y];
        }

        if (top != null && top.isVisited()) {
            neighbours.add(top);
        }

        if (right != null && right.isVisited()) {
            neighbours.add(right);
        }

        if (bottom != null && bottom.isVisited()) {
            neighbours.add(bottom);
        }

        if (left != null && left.isVisited()) {
            neighbours.add(left);
        }

        if (neighbours.size() > 0) {
            int n = rand.nextInt(neighbours.size());
            return neighbours.get(n);
        }

        return null;
    }

    public boolean inBounds(int x, int y) {

        return x >= 0 && y >= 0 && x <= this.width - 1 && y <= this.height - 1;
    }

    public void removeWall(Cell current, Cell next) {

        int removeXwall = (current.getxCoor() / gridscale) - (next.getxCoor() / gridscale);
        int removeYwall = (current.getyCoor() / gridscale) - (next.getyCoor() / gridscale);

        if (removeXwall == 1) {
            current.setLeft_wall(false);
            next.setRight_wall(false);

        } else if (removeXwall == -1) {
            current.setRight_wall(false);
            next.setLeft_wall(false);
        }

        if (removeYwall == 1) {
            current.setTop_wall(false);
            next.setBottom_wall(false);

        } else if (removeYwall == -1) {
            current.setBottom_wall(false);
            next.setTop_wall(false);
        }

    }

    public Cell[][] getCell_array() {
        return cell_array;
    }

    public int getEndX() {

        return end.getxCoor() / gridscale;
    }

    public int getEndY() {

        return end.getxCoor() / gridscale;
    }

}
