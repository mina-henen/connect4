public class connect4 {

    public static char[][] grid = new char[6][7];

    public static void printGame(){
        for (int i=0;i<6;++i){
            for (int j=0;j<7;++j)
                System.out.print(grid[i][j]+" ");
            System.out.println();
        }
    }
    public static void initializeGrid(){
        for (int i=0;i<6;++i) {
            for (int j =0; j<7;++j)
                grid[i][j] = '0';
        }
    }

    public static boolean putButton(char p,int n){
        if (n>6 || n<0)
            return false;
        for (int i=5;i>=0;--i){
            if (grid[i][n]=='0'){
                grid[i][n]=p;
                return true;
            }
        }
        return false;
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

    public static void main(String[] args){
        //printGame();
        initializeGrid();
        putButton('1',3);
        putButton('2',3);
        putButton('1',4);
        putButton('2',3);
        putButton('1',3);
        putButton('1',2);
        putButton('1',5);
        putButton('1',1);
        putButton('1',1);
        putButton('1',1);
        putButton('1',1);
        putButton('1',1);
        putButton('1',1);
        putButton('1',0);
        putButton('1',0);
        putButton('1',0);
        putButton('1',0);
        putButton('1',0);
        putButton('1',2);
        putButton('1',2);
        putButton('1',2);
        putButton('1',2);
        putButton('1',2);
        putButton('1',2);
        putButton('1',4);
        putButton('1',5);
        putButton('1',5);
        putButton('1',6);
        putButton('1',6);
        putButton('1',6);
        putButton('1',6);
        putButton('1',6);
        putButton('1',6);
        putButton('1',6);

        printGame();
        System.out.println(countScore(grid,'1'));
    }
}
