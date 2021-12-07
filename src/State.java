import java.util.ArrayList;
<<<<<<< HEAD
public class State {
    public char[][] grid;
    private int utility;
=======

public class State {

    public char[][] grid;
    public int utility;
>>>>>>> 26e343a5caab83e6e7bafc4e7d1a1937d5d6c2fe
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

<<<<<<< HEAD
    public int getUtility(){
        return utility;
    }
=======
>>>>>>> 26e343a5caab83e6e7bafc4e7d1a1937d5d6c2fe
    public void addChild(State s){
        children.add(s);
    }

    public void setCol(int col) {
        this.col = col;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 26e343a5caab83e6e7bafc4e7d1a1937d5d6c2fe
