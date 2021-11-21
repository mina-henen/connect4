public class connect4 {

    public static char[][] grid = new char[7][6];

    public static void printGame(){
        for (int i=5;i>-1;--i){
            for (int j=0;j<6;++j)
                System.out.print(grid[j][i]+" ");
            System.out.println();
        }
    }
    public static void initializeGrid(){
        for (int i=0;i<6;++i) {
            for (int j = 6; j > -1; --j)
                grid[j][i] = '0';
        }
    }

    public static boolean putButton(char p,int n){
        if (n>6 || n<0)
            return false;
        for (int i=0;i<6;++i){
            if (grid[n][i]=='0'){
                grid[n][i]=p;
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
        printGame();
    }
}
