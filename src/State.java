import java.util.ArrayList;

public class State {

    public char[][] grid;
    public int utility;
    public ArrayList<State> children;
    public int col;

    public State(char[][] grid) {
        this.grid = grid;
        col = -1;
        children = new ArrayList<State>();
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public void addChild(State s){
        children.add(s);
    }

    public void setCol(int col) {
        this.col = col;
    }
}
