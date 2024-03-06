/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class RedCliffController implements Initializable {

    @FXML
    private Label display;
    @FXML
    private Label displayMatrix;
    @FXML
    private Label clusterFound;
    @FXML
    private Button find;
    private boolean[][] visited;
    private int rows;
    private int cols;
    
    int[][] matrix = {
            {1, 1, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display.setText("Position of all Cao Cao chained battleships: \n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j] + " ");
            }
            sb.append("\n");
        }
        displayMatrix.setText(sb.toString());
    }    
    
    public void find(ActionEvent event){
        clusterFound.setText(countBattleshipClusters(matrix) + " clusters");
    }
    
    public int countBattleshipClusters(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.visited = new boolean[rows][cols];

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || matrix[row][col] == 0 || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        dfs(row - 1, col);
        dfs(row + 1, col);
        dfs(row, col - 1);
        dfs(row, col + 1);
        dfs(row - 1, col - 1);
        dfs(row - 1, col + 1);
        dfs(row + 1, col - 1);
        dfs(row + 1, col + 1);
    }
}
