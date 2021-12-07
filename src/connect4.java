import java.util.Arrays;

public class connect4 {


    public static void printGame(char[][] grid){
        for (int i=0;i<6;++i){
            for (int j=0;j<7;++j)
                System.out.print(grid[i][j]+" ");
            System.out.println();
        }
    }
    public static void initializeGrid(char[][] grid){
        for (int i=0;i<6;++i) {
            for (int j =0; j<7;++j)
                grid[i][j] = '0';
        }
    }

    public static char[][] putButton(char[][] grid, char p, int n){
        if (n>6 || n<0)
            return grid;
        for (int i=5;i>=0;--i){
            if (grid[i][n]=='0'){
                grid[i][n]=p;
                return grid;
            }
        }
        return grid;
    }

    public static int countScore(char[][] grid, char p){
        int score=0;
        int temp=0;
        int temp2=0;

        // count horizontally
        for (int i=0;i<grid.length;++i){
            for (int j=0;j<grid[0].length;++j){
                if (grid[i][j]==p)
                    temp++;
                else{
                    score+=Math.max(0,temp-3);
                    temp=0;
                }
            }
            score+=Math.max(0,temp-3);
            temp=0;
        }

        // count vertically
        for (int i=0;i<grid[0].length;++i){
            for (int j=0;j<grid.length;++j){
                if (grid[j][i]==p)
                    temp++;
                else{
                    score+=Math.max(0,temp-3);
                    temp=0;
                }
            }
            score+=Math.max(0,temp-3);
            temp=0;
        }

        // count diagonally 1
        for (int i=3;i<6;++i){
            for (int j=0;j<7;++j){
                int x=i-j;
                if (x<0)
                    break;
                if (grid[x][j]==p)
                    temp++;
                else{
                    score+=Math.max(0,temp-3);
                    temp=0;
                }

                int a=5-j,b=6-x;
                if (grid[a][b]==p)
                    temp2++;
                else{
                    score+=Math.max(0,temp2-3);
                    temp2=0;
                }
            }
            score+=Math.max(0,temp-3);
            score+=Math.max(0,temp2-3);
            temp=0;
            temp2=0;
        }

        // count diagonally 2
        for (int j=3;j>=0;j--){
            for (int i=0;i<6;++i){
                int x = j+i;
                if(x>6)
                    break;
                if(grid[i][x]==p)
                    temp++;
                else{
                    score+=Math.max(0,temp-3);
                    temp=0;
                }

            }
            score+=Math.max(0,temp-3);
            temp=0;
        }
        for (int i=1;i<3;++i){
            for (int j=0;j<7;++j){
                int x =i+j;
                if(x>5)
                    break;
                if (grid[x][j]==p)
                    temp++;
                else{
                    score+=Math.max(0,temp-3);
                    temp=0;
                }
            }
            score+=Math.max(0,temp-3);
            temp=0;
        }
        return score;
    }

    public static char[][] copy(char[][] grid){
        char[][] g = new char[grid.length][grid[0].length];
        for (int i=0;i<6;++i) {
            for (int j =0; j<7;++j)
                g[i][j] = grid[i][j];
        }
        return g;
    }

    public static int heuristic(char[][] grid){
        return countScore(grid,'2')-countScore(grid,'1');
    }

    public static void main(String[] args){
        char[][] grid = new char[6][7];

        initializeGrid(grid);
        char[][] g = copy(grid);

        putButton(grid,'1',5);
        putButton(g,'2',5);

        printGame(grid);

        System.out.println(countScore(grid,'1'));
        printGame(g);

        State state = new State(putButton(copy(grid),'1',2));
        state.addChild(new State(putButton(copy(grid),'1',2)));
    }
}
