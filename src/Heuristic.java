public class Heuristic {
    Grid g=new Grid();
    //heuristic function to calculate scor
    public  int heuristic(char[][] grid){
        return g.countScore(grid,'2')-g.countScore(grid,'1');
    }
}
