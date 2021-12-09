package Algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MiniMax {
    Grid g=new Grid();
    public int nodes=0;
    Heuristic h=new Heuristic();
    //min function takes a state ,K and a player turn
    public State minimize(State state,int K,char player){
        nodes++;
        //to check if K is 0 or if we reached a terminal node
        if(K==0 || g.is_terminal_node(state)){
            //call the heuristic to set the utility of the state then return it
            state.setUtility(h.heuristic(state.grid));
            return state;
        }
        //a state to put the minimum value found
        State minimum=new State(state.grid);
        minimum.setUtility(Integer.MAX_VALUE);

       g.put_children(state,player);
       //loop over the children of the state to update the value of minimum state
        for(State child : state.getChildren()){
            child.setUtility(maximize(child,K-1,'2').getUtility());

            if(child.getUtility()<minimum.getUtility()) {
                minimum.setState(child);
            }
        }
        //return the state with the minimum value found
        return minimum;
    }
    //min function takes a state ,K and a player turn
    public State maximize(State state,int K,char player){
        nodes++;
        //to check if K is 0 or if we reached a terminal node
        if(K==0 || g.is_terminal_node(state)){
            //call the heuristic to set the utility of the state then return it
            state.setUtility(h.heuristic(state.grid));
            return state;
        }
        //a state to put the maximum value found
        State maximum=new State(state.grid);
        maximum.setUtility(Integer.MIN_VALUE);

       g.put_children(state,player);
        //loop over the children of the state to update the value of maximum state
        for(State child : state.getChildren()){
            child.setUtility(minimize(child,K-1,'1').getUtility());

            if(child.getUtility()>maximum.getUtility()) {
                maximum.setState(child);
            }
        }
        //return the state with the maximum value found
        //g.printGame(maximum.getChildren().get(5).grid);
        return maximum;
    }
}
