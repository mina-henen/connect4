package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

public class State {
    public char[][] grid;
    private int utility;
    private ArrayList<State> children;
    private int col;

    public State(char[][] grid) {
        this.grid = grid;
        this.col = -1;
        this.children = new ArrayList<>();
    }
    public void setState(State state){
        this.utility=state.getUtility();
        this.col= state.getCol();
    }
    public void setUtility(int utility) {
        this.utility = utility;
    }

    public int getUtility(){
        return utility;
    }
    public void addChild(State s){
        children.add(s);
    }
    public ArrayList<State> getChildren(){
        return this.children;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getCol(){
        return this.col;
    }
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "",1);
        return buffer.toString();
    }
    public void printTree(){
        System.out.println(this.toString());
    }
    private void print(StringBuilder buffer, String prefix, String childrenPrefix,int level) {
        buffer.append(prefix);
        if(level%2==1) {
            buffer.append("Level: " + level + " turn: Agent  Value : " + this.utility);
            buffer.append('\n');
        }
        else{
            buffer.append("Level: " + level + " turn: Player  Value : " + this.utility);
            buffer.append('\n');
        }
        for (Iterator<State> it = children.iterator(); it.hasNext();) {
            State next = it.next();

            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ",level+1);
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ",level+1);
            }
        }
    }
}