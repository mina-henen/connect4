public class connect4 {

    public static char[][] grid = new char[6][7];

    public static void printGame(){
        for (int i=5;i>-1;--i){
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
        for (int i=0;i<6;++i){
            if (grid[i][n]=='0'){
                grid[i][n]=p;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        //printGame();
        initializeGrid();
        putButton('1',3);
        putButton('2',3);
        putButton('1',4);
        putButton('2',3);
        putButton('1',3);
        putButton('2',4);
        putButton('1',2);
        putButton('1',5);
        printGame();
    }
}
