public class Minimax {
    public static void main(String[] args) {

    }


    public int Find_zero(char [][] grid , int column){
        int Zero_index = -1;
        for (int i=0 ; i<grid.length ; i++){
            if (grid[i][column] == '0'){
                Zero_index = i;
            }
        }
        return Zero_index;
    }
}
