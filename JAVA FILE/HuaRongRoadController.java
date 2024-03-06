/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HuaRongRoadController implements Initializable {
    @FXML
    private Button showPath;
    @FXML
    private TextArea outputArea;
    @FXML
    private ImageView Road;
    
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    StringBuilder output = new StringBuilder();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Road.setImage(new Image("/img/huarongFirst.png"));
    }    
    
    public void showPath(ActionEvent event)throws Exception{
        int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        int[][] visited = new int[maze.length][maze[0].length];
        int[][] previous = new int[maze.length][maze[0].length];

        int[] startPoint = findStartPoint(maze);
        int[] exitPoint = findExitPoint(maze);

        boolean pathFound = bfs(maze, startPoint, exitPoint, visited, previous);
        if (pathFound) {
            Road.setImage(new Image("/img/huarongFinal.png"));
            output.append("Path found!\n");
            printPath(previous, exitPoint);
        } else {
            output.append("Path not found.\n");
        }
        outputArea.setText(output.toString());
    }
    
    private static int[] findStartPoint(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 2) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    private static int[] findExitPoint(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 3) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    private static boolean bfs(int[][] maze, int[] startPoint, int[] exitPoint, int[][] visited, int[][] previous) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(startPoint);
        visited[startPoint[0]][startPoint[1]] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == exitPoint[0] && current[1] == exitPoint[1]) {
                return true;
            }

            for (int[] direction : DIRECTIONS) {
                int newRow = current[0] + direction[0];
                int newCol = current[1] + direction[1];

                if (isValidMove(maze, newRow, newCol, visited)) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = 1;
                    previous[newRow][newCol] = current[0] * maze[0].length + current[1];
                }
            }
        }

        return false;
    }
    
    private static boolean isValidMove(int[][] maze, int row, int col, int[][] visited) {
        int numRows = maze.length;
        int numCols = maze[0].length;

        return row >= 0 && row < numRows && col >= 0 && col < numCols && maze[row][col] != 1 && visited[row][col] != 1;
    }
    
    private void printPath(int[][] previous, int[] exitPoint) {
        int exitIndex = exitPoint[0] * previous[0].length + exitPoint[1];
        LinkedList<Integer> path = new LinkedList<>();

        while (exitIndex != 0) {
            int row = exitIndex / previous[0].length;
            int col = exitIndex % previous[0].length;

            path.addFirst(exitIndex);
            exitIndex = previous[row][col];
        }

        int lastIndex = path.size() - 1;
        for (int i = 0; i < path.size(); i++) {
            int index = path.get(i);
            int row = index / previous[0].length;
            int col = index % previous[0].length;

            output.append("[" + row + ", " + col + "]");

            if (i != lastIndex) {
                output.append(" -> ");
            }else{
                output.append("\n");
            }
            
            if(i%5==0 && i!=0){
                output.append("\n");
            }
        }
    }
    
}
