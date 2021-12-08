package com.example.gui;

public class MiniMaxWithPruning {
    Grid g=new Grid();
    Heuristic h=new Heuristic();

    public State minimize_with_pruning(State state, int K, char player , double a , double b){
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
        for(State child : state.children){
            state=maximize_with_pruning(child,K-1,'2' , a , b);

            if(state.getUtility()<minimum.getUtility()) {
                minimum.setUtility(state.getUtility());
                minimum.setCol(state.col);

            }
        }
        // alpha pruning
        if (minimum.getUtility() <=a){
            return minimum;
        }
        b = (b < minimum.getUtility()) ? b : minimum.getUtility();

        return minimum;
    }




    public State maximize_with_pruning(State state, int K, char player , double a , double b){
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
        for(State child : state.children){
            state=minimize_with_pruning(child,K-1,'1' , a , b);

            if(state.getUtility()>maximum.getUtility()) {
                maximum.setUtility(state.getUtility());
                maximum.setCol(state.col);

            }
        }
        // Beta pruning
        if (maximum.getUtility() >= b){
            return maximum;
        }
        a = (a > maximum.getUtility()) ? a : maximum.getUtility();

        return maximum;
    }
}
