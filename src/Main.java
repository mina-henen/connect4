import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int p1Score=0;
    static int p2Score=0;
    private static void print_score(){
        System.out.println("Player 1 Score: "+p1Score);
        System.out.println("Player 2 Score: "+p2Score);
    }
    public static void main(String[] args) {
        char[][] grid=new char[6][7];
        char Player='1';
        char AI='2';

        MiniMax m =new MiniMax();
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
                print_score();
                turn++;
                turn=turn%2;
            }
            else{
                System.out.println("Player 2 Turn : ");
                State state=new State(grid);
                State temp=m.minimize(state,K,AI);
                int col=temp.col;
                g.play(grid,col,AI);
                p2Score=g.countScore(grid,AI);
                print_score();
                turn++;
                turn=turn%2;
            }
        }
        if(p1Score>p2Score)
            System.out.println("Player 1 Wins");
        else
            System.out.println("Player 2 Wins");
        System.out.println("GAAAAMEEE OVVEEEEEER");
    }
}