//package checkers;

/**
 * Eval.java
 * @author Eric
 * May 3, 2019
 */

import java.awt.Point;

class Eval {
 private static int[][] board = new int[26][18];
 private static int[][] dist;

 public static int getDist(Point piece) {
  return dist[piece.x][piece.y];
 }

 public static void computeDist() {
  dist = new int [board.length][board[0].length];
  for(int i = 9; i<board.length; i++) {
   for(int j = 0; j<board[i].length; j++){
    if (board[i][j] != -1){
     dist[i][j] = calcDist(new Point(i, j));
    }
   }
  }
 }

 private static int calcDist(Point piece){
  boolean found = false;
  int currX = piece.x;
  int currY = piece.y;
  int distance = 0;
  while (!found) {
   if (currX == 25 && currY == 13) {
    found = true;
   }
   if (!found) {
    //figure if on left or right of midline (y = 2x or currY = currX/2)
    //move down
    //else move towards midline
    if ((currX/2)<currY) {//right of midline
     if (currX + 1 < board.length && board[currX+1][currY]!=-1) {
      currX++;
      distance++;
     } else{
      //currX++;
      currY--;
      distance++;
     }
    } else {
     if(currX+1 < board.length && currY+1 < board[currX].length && board[currX+1][currY+1]!=1){
      currX++;
      currY++;
      distance++;
     } else {
      currY++;
      distance++;
     }
    }
   }
  }
  return distance;
 }
 public static void updateBoard(int [][] newboard){
   board = newboard;
 }
 public static int distCentroid(Point piece){
    int x = piece.x;
    int y = piece.y;
    int teamnumber = 1;
    double avgX = 0;
    double avgY = 0;
    double count = 0;
    for(int i = 8; i<board.length; i++){
      for(int j = 0; j<board[i].length; j++){
        if(board[i][j] == 1){
          avgX+=i;
          avgY+=(j+0.5*(board.length-i));
          //System.out.println(i+" "+j+" "+(j+0.5*(board.length-i)));
          count++;
        }
      }
    }
    //System.out.println();
    //System.out.println(avgX/count);
    //System.out.println((avgY/count)-(0.5*(board.length-(avgX/count))));
    avgX = Math.round(avgX/count);
    avgY = Math.round((avgY/count)-(0.5*(board.length-avgX)));
    int dist = 0;
    boolean found = false;
    while(!found){
      if(x == avgX && y == avgY){
        found = true;
      }
      if(!found){
        if(x<avgX){
          if ((y+0.5*(board.length-x)>avgY+0.5*(board.length-avgX))){//if right of target
            if(board[x+1][y]!=-1){
              x++;
              dist++;
            } else if(x<board.length-1 && y<board[x].length-1 && board[x+1][y+1]!=-1){
              x++;
              y++;
              dist++;
            } else {
              y--;
              dist++;
            }
          } else {
            if(board[x+1][y+1]!=-1){
              x++;
              y++;
              dist++;
            } else if(x<board.length-1 && board[x+1][y]!=-1){
              x++;
              dist++;
            } else {
              y++;
              dist++;
            }
          }
        } else if(x>avgX){
          if((y+0.5*(board.length-x)>avgY+0.5*(board.length-avgX))){//if right of target
            if(board[x-1][y-1]!=-1){
              x--;
              y--;
              dist++;
            } else if(x>0 && board[x-1][y]!=-1){
              x--;
              dist++;
            } else{
              y--;
              dist++;
            }
          } else{
            if(board[x-1][y]!=-1){
              x--;
              dist++;
            } else if(x>0 && y>0 && board[x-1][y-1]!=-1){
              x--;
              y--;
              dist++;
            } else{
              y++;
              dist++;
            }
          }
        } else{
          if(y+0.5*(board.length-x)>avgY+0.5*(board.length-avgX)){//if right of target
            y--;
            dist++;
          } else{
            y++;
            dist++;
          }
        }
      }
    }
    return dist;
  }
 public static boolean isBehindCentroid(Point piece){
   int x = piece.x;
   int teamnumber = 1;
   double avgX = 0;
   double count = 0;
   for(int i = 0; i<board.length; i++){
     for(int j = 0; j<board[i].length; j++){
      // System.out.print(board[i][j]+" ");
       if(board[i][j] == 1){
         //System.out.println("reached");
         avgX+=i;
         count++;
       }
     }
     //System.out.println();
   }
   //System.out.println(count);
   avgX = avgX/count;
   //System.out.println("Centroid: ");
   //System.out.println(avgX);
   if(x<avgX){
     return true;
   } else {
     return false;
   }
 }
}
