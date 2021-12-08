package Algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int p1Score=0;
    static int p2Score=0;
    private static void print_score(){
        System.out.println("Player 1 Score: "+p1Score);
        System.out.println("AI Score: "+p2Score);
    }
    public static void main(String[] args) {
        char[][] grid=new char[6][7];
        char Player='1';
        char AI='2';

        MiniMaxWithPruning mp = new MiniMaxWithPruning();
        MiniMax m = new MiniMax();
        Grid g=new Grid();
        g.initializeGrid(grid);

        int turn=1;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter K : ");
        int K=sc.nextInt();
        System.out.println();
        g.printGame(grid);
        while(g.get_valid_locations(grid).size()>0){
            if(turn==1){
                System.out.print("Player 1 Turn : ");
                int col=sc.nextInt();
                col--;
                g.play(grid,col,Player);
                p1Score=g.countScore(grid,Player);
            }
            else{
                System.out.println("AI Turn : ");
                State state=new State(grid);
                //State temp=m.maximize_with_pruning(state,K,AI , Double.MIN_VALUE , Double.MAX_VALUE );
                State temp=m.maximize(state,K,AI);
                int col=temp.col;
                g.play(grid,col,AI);
                p2Score=g.countScore(grid,AI);
            }
            print_score();
            turn++;
            turn=turn%2;
        }
        if(p1Score>p2Score)
            System.out.println("Player 1 Wins");
        else
            System.out.println("AI Wins");
        System.out.println("GAAAAMEEE OVVEEEEEER");
    }
}