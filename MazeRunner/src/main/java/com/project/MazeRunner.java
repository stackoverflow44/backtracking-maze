/* A better solution to solving maze
A maze is a 2D matrix in which some cells are blocked. 
One of the cells is the source cell, from where we have to start. 
And another one of them is the destination, where we have to reach. 
Find a path from the source to the destination without moving into any of the blocked cells, git hmarked as 0.
*/
package com.project;

public class MazeRunner{
    int[][] myMaze;
    int mazeSize;

    public MazeRunner(int N)
    {
        mazeSize = N;
        myMaze = new int[mazeSize][mazeSize];
        for (int i=0; i < mazeSize; i++){
            for(int j=0; j < mazeSize; j++){
                myMaze[i][j] = 0;
            }
        }
    }

    public void solveMaze(int [][]maze){
        if (findPath(0,0,maze,"down"))
            printMaze();
        else
            System.out.println("No path out of maze.");
    }

    public boolean findPath(int x, int y, int[][]maze, String Direction){
       if(x == mazeSize-1 && y == mazeSize-1){
            //reach the end of maze
            myMaze[x][y] = 1;
            return true;
        }

        if(isSafeToMove(x,y, maze))
        {
            myMaze[x][y] = 1; //safe to move to

            if(Direction != "left" && findPath(x, y+1, maze, "right")){ // move right
                return true;
            }
            if(Direction != "up" && findPath(x+1, y, maze, "down")){ //move down
                return true;
            }
            if(Direction != "down" && findPath(x-1, y, maze,"up")){ //move up
                return true;
            }
            if(Direction != "right" && findPath(x, y-1, maze, "left")){ // move left
                return true;
            }
            //backtrack if no path found
            myMaze[x][y]=0;
            return false;
        }
        else
        {
             return false;
        }
    }

    public boolean isSafeToMove(int x, int y, int[][]maze)
    {
        //array boundary check, check if current pos is blocked, check if current pos has been visisted
        if(x >= 0 && y >=0 && x < mazeSize && y < mazeSize && maze[x][y]==1 && myMaze[x][y]==0)
            return true;
        else
            return false;
    }
    public void printMaze()
    {
        for(int i=0; i<mazeSize; i++){
            for(int j=0; j<mazeSize; j++){
                System.out.print(myMaze[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int[][] maze = { { 1, 0, 0, 1, 1 }, { 1, 1, 1, 0, 1 }, { 0, 1, 1, 1, 1 },
		               { 0, 0, 0, 1, 0 },{ 0, 0, 0, 1, 1 } }; 
        MazeRunner myMaze = new MazeRunner(maze.length);
        myMaze.solveMaze(maze);
    }
}