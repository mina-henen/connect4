package Algorithm;

public class MiniMaxWithPruning {
    Grid g=new Grid();
    int nodes=0;
    Heuristic h=new Heuristic();

    public State minimize_with_pruning(State state,int K,char player , double alpha , double beta){
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
            child.setUtility(maximize_with_pruning(child,K-1,'2',alpha,beta).getUtility());

            if(child.getUtility()<minimum.getUtility()) {
                minimum.setState(child);
                beta = minimum.getUtility();//
            }
            if (beta <= alpha){//
                break;//
            }//
        }
       /* // alpha pruning
        if (minimum.getUtility() <=alpha){
            return minimum;
        }
        beta = (beta < minimum.getUtility()) ? beta : minimum.getUtility();
*/
        return minimum;
    }




    public State maximize_with_pruning(State state,int K,char player , double alpha , double beta){
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

            child.setUtility(minimize_with_pruning(child,K-1,'1',alpha,beta).getUtility());

            if(child.getUtility()>maximum.getUtility()) {
                maximum.setState(child);
                alpha = maximum.getUtility();//
            }
            if (beta <= alpha){//
                break;//
            }//
        }
     /*   // Beta pruning
        if (maximum.getUtility() >= beta){
            return maximum;
        }
        alpha = (alpha > maximum.getUtility()) ? alpha : maximum.getUtility();
*/
        return maximum;
    }
}
