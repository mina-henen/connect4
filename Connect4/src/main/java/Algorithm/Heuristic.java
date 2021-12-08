package Algorithm;

import java.util.HashMap;

public class Heuristic {


    public int calcHeuristic(char[][] grid, char p){
        int fours=0,optFours,threes=0,twos=0,temp=0,temp2=0,num=0,heu;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,0);
        map.put(2,0);
        map.put(3,0);
        map.put(4,1);
        map.put(5,2);
        map.put(6,3);
        map.put(7,4);
        map.put(8,4);
        map.put(9,4);
        map.put(10,4);
        map.put(11,5);
        map.put(12,6);
        map.put(13,7);
        map.put(14,8);
        map.put(15,8);
        map.put(16,8);
        map.put(17,8);
        map.put(18,9);
        map.put(19,10);
        map.put(20,11);
        map.put(21,12);

        // count horizontally
        for (int i=0;i<grid.length;++i){
            for (int j=0;j<grid[0].length;++j){
                if (grid[i][j]==p) {
                    temp++;
                    num++;
                }
                else {
                    fours+=Math.max(0,temp-3);
                    if (grid[i][j]=='0'){
                        threes+=Math.max(0,temp-2);
                    }
                    temp=0;
                }
            }
            fours+=Math.max(0,temp-3);
            temp=0;
        }

        // count vertically
        for (int i=0;i<grid[0].length;++i){
            for (int j=grid.length-1;j>=0;--j){
                if (grid[j][i]==p)
                    temp++;
                else{
                    fours+=Math.max(0,temp-3);
                    if (grid[j][i]=='0'){
                        threes+=Math.max(0,temp-2);
                        twos+=Math.max(0,temp-1);
                    }
                    temp=0;
                }
            }
            fours+=Math.max(0,temp-3);
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
                    fours+=Math.max(0,temp-3);
                    if (grid[x][j]=='0'){
                        threes+=Math.max(0,temp-2);
                        twos+=Math.max(0,temp-1);
                    }
                    temp=0;
                }

                int a=5-j,b=6-x;
                if (grid[a][b]==p)
                    temp2++;
                else{
                    fours+=Math.max(0,temp2-3);
                    if (grid[a][b]=='0'){
                        threes+=Math.max(0,temp2-2);
                        twos+=Math.max(0,temp2-1);
                    }
                    temp2=0;
                }
            }
            fours+=Math.max(0,temp-3);
            fours+=Math.max(0,temp2-3);
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
                    fours+=Math.max(0,temp-3);
                    if (grid[i][x]=='0'){
                        threes+=Math.max(0,temp-2);
                        twos+=Math.max(0,temp-1);
                    }
                    temp=0;
                }

            }
            fours+=Math.max(0,temp-3);
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
                    fours+=Math.max(0,temp-3);
                    if (grid[x][j]=='0'){
                        threes+=Math.max(0,temp-2);
                        twos+=Math.max(0,temp-1);
                    }
                    temp=0;
                }
            }
            fours+=Math.max(0,temp-3);
            temp=0;
        }
        optFours = map.get(num);
        heu = (fours*10)+(threes*5)+(twos*2)-((optFours-fours)*3);
        return heu;
    }
    //heuristic function to calculate score
    public  int heuristic(char[][] grid){
        double h2=calcHeuristic(grid,'2');
        double h1=calcHeuristic(grid,'1')*1.5;
        //System.out.println("h2= "+h2+" h1= "+h1);
        return (int) (h2-h1);
    }
}
