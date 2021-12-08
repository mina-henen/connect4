package Algorithm;

import java.util.ArrayList;

public class Grid {
    //grid dimensions 6*7
    private int ROWS=6;
    private int COLUMNS=7;
    //playing function
    public void play(char[][] grid,int col,char player){
        int row=get_next_open_row(grid,col);
        put_piece(grid,row,col,player);
        printGame(grid);
    }
    //function to print the game
    public void printGame(char[][] grid){
        for (int i=0;i<ROWS;++i){
            for (int j=0;j<COLUMNS;++j)
                System.out.print(grid[i][j]+" ");
            System.out.println();
        }
    }
    //function to initialize the grid with zeroes
    public void initializeGrid(char[][] grid){
        for (int i=0;i<ROWS;++i) {
            for (int j =0; j<COLUMNS;++j)
                grid[i][j] = '0';
        }
    }
    //checking if state is a terminal state
    public boolean is_terminal_node(State state){
        //if all columns filled then it is terminal
        return get_valid_locations(state.grid).size()==0;
    }

    //function to put children of a specific state
    public void put_children(State state,char player){
        //get valid locations of putting a piece
        ArrayList<Integer> valid_locations=get_valid_locations(state.grid);
        //loop over the valid pieces

        for(int i=0;i<valid_locations.size();i++){
            //put piece in a temp state and add it to the children array
            //State temp=new State(copy(state.grid));
            State temp=new State(copy(state.grid));
            int col=valid_locations.get(i);
            int row=get_next_open_row(temp.grid,col);
            put_piece(temp.grid,row,col,player);
            state.addChild(temp);
            //check the parent if the root of the tree it will set the col
            if(state.col==-1) {
                temp.setCol(col);
            }
            //if not it will just get the same col as the parent
            else{
                temp.setCol(state.col);
            }
        }
      /*  for (int i=0 ; i<temp.grid.length ; i++){
            for (int j=0 ; j< temp.grid[0].length ; j++){
                System.out.print(temp.grid[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("**************");*/

    }
    //put piece in the right position by using row and col and player turn
    private void put_piece(char[][] grid,int row,int col,char player){
        grid[row][col]=player;
    }

    //function to get the row to put piece in get the number of the first empty row(which equal to 0) of a column
    private int get_next_open_row(char[][] grid,int col){
        for(int i =ROWS-1;i>=0;i--)
            if (grid[i][col] == '0')
                return i;
        return -1;
    }
    //function to get valid locations of a piece (return arraylist of integers of valid columns)
    public ArrayList get_valid_locations(char[][] grid){
        ArrayList<Integer> valid_locations=new ArrayList();
        for(int i=0;i<COLUMNS;i++)
            if(check_Column(grid,i))
                valid_locations.add(i);
        return valid_locations;
    }
    //function to check if a column is not filled to the top
    private boolean check_Column(char[][] grid,int col){
        return grid[0][col]=='0';
    }
    public int countScore(char[][] grid, char p){
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
    //function to copy grid
    private char[][] copy(char[][] grid){
        char[][] g = new char[grid.length][grid[0].length];
        for (int i=0;i<ROWS;++i) {
            for (int j =0; j<COLUMNS;++j)
                g[i][j] = grid[i][j];
        }
        return g;
    }

}
