/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.util.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sareetta
 */
public class SudokuGameTest {
    SudokuGame sudoku;
    List<Integer> values;
    
    @Before
    public void setUp() {
        sudoku = new SudokuGame();
        values = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++) {
            values.add(i);
        }
        
    }
    
    @Test
    public void constructorCreatesNewSudoku() {
        assertTrue(sudoku != null);
    }
    
    @Test
    public void constructorSetsDifficulty() {
        assertTrue(sudoku.getDifficulty() == 25);
    }
    
    @Test
    public void newSudokuSetsBoardWithCorrectHeight() {
        assertTrue(9 == sudoku.getSudoku().length);
    }
    
    @Test
    public void newSudokuSetsBoardWithCorrectWidht() {
        assertTrue(9 == sudoku.getSudoku()[0].length);
    }
    
    @Test 
    public void generateNewSudokuGeneratesSolution() {
        sudoku.generateSudoku(0);
        int numbers = 0;
        int empty = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.getValue(i, j) == 0) {
                    empty++;
                } else if(sudoku.getValue(i, j) != 0) {
                    numbers++;
                }
            }
        }
        assertTrue(numbers+empty == 81);
    }
    
    @Test
    public void isPossibleSubgridReturnFalseIfValueInTheRow() {
        sudoku.setValue(1, 1, 8);
        assertTrue(!sudoku.isPossibleSubgrid(1, 2, 8));
    }
    
    @Test
    public void isPossibleSubgridReturnFalseIfValueInTheColumn() {
        sudoku.setValue(1, 1, 8);
        assertTrue(!sudoku.isPossibleSubgrid(2, 1, 8));
    }
    
    @Test
    public void isPossibleSubgridReturnTrueIfNumberCanBeInserted() {
        assertTrue(sudoku.isPossibleSubgrid(0, 0, 8));
    }
    
    @Test 
    public void isPossibleSubgridReturnFalseIfValueInTheSubgrid() {
        sudoku.setValue(1, 1, 8);
        assertTrue(!sudoku.isPossibleSubgrid(2, 2, 8));
    }
    
   @Test
    public void isPossibleSubgridSetsValueToRow0IfRowIsLessThan3() {
        sudoku.setValue(2, 0, 8);
        assertTrue(!sudoku.isPossibleSubgrid(1, 1, 8));
    }
    
    @Test
    public void isPossibleSubgridSetsValueToRow3IfRowIsLessThan6() {
        sudoku.setValue(5, 0, 8);
        assertTrue(!sudoku.isPossibleSubgrid(3, 1, 8));
    }
    
    @Test
    public void isPossibleSubgridSetsValueToRow6IfRowIsMoreThan5() {
        sudoku.setValue(6, 0, 8);
        assertTrue(!sudoku.isPossibleSubgrid(8, 1, 8));
    }
    
    @Test
    public void isPossibleSubgridSetsValueToColumn0IfColumnIsLessThan3() {
        sudoku.setValue(0, 2, 8);
        assertTrue(!sudoku.isPossibleSubgrid(1, 1, 8));
    }
    
    @Test
    public void isPossibleSubgridSetsValueToColumn3IfColumnIsLessThan6() {
        sudoku.setValue(0, 5, 8);
        assertTrue(!sudoku.isPossibleSubgrid(1, 3, 8));
    }
    
    @Test
    public void isPossibleSubgridSetsValueToColumn6IfColumnIsMoreThan5() {
        sudoku.setValue(0, 6, 8);
        assertTrue(!sudoku.isPossibleSubgrid(1, 6, 8));
    }
    
    @Test
    public void isPossibleRowReturnsTrueIfValueCanBeInserted() {
        assertTrue(sudoku.isPossibleRow(0, 2));
    }
    
    @Test
    public void isPossibleRowReturnsFalseIfValueCanNotBeInserted() {
        sudoku.setValue(0, 0, 2);
        assertTrue(!sudoku.isPossibleRow(0, 2));
    }
    
    @Test
    public void isPossibleColumnReturnsTrueIfValueCanBeInserted() {
        assertTrue(sudoku.isPossibleColumn(0, 2));
    }
    
    @Test
    public void isPossibleColumnReturnsFalseIfValueCanNotBeInserted() {
        sudoku.setValue(0, 0, 2);
        assertTrue(sudoku.isPossibleColumn(1, 2));
    }
    
    @Test
    public void getNextPossibleValueReturnPossibleValue() {
        sudoku.setValue(0, 1, 1);
        int value = sudoku.getNextPossibleValue(0, 0, values);
        assertTrue(sudoku.isPossibleSubgrid(0, 0, value));
    }
    
    @Test
    public void checkSudokuWorksIfSudokuNotCorrect() {
        sudoku.newSudoku();
        assertTrue(!sudoku.checkSudoku());
    }
    
    @Test
    public void checkSudokuWorkdIfSudokuIsCorrect() {
        sudoku.newSudoku();
        
        int[][] solution = sudoku.solution;
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                sudoku.setValue(i, j, solution[j][i]);
            }
        }
        assertTrue(sudoku.checkSudoku());
    }
    
    @Test
    public void setAndGetValueWorksCorrectly() {
        sudoku.setValue(1, 1, 8);
        assertTrue(sudoku.getValue(1,1) == 8);
    }
    
    @Test
    public void setValueChecksIfTheValueIsTooBig() {
        sudoku.setValue(1,1,10);
        assertTrue(sudoku.getValue(1, 1) == 1);
    }
    
    @Test
    public void setValueChecksIfTheValueIsTooSmall() {
        sudoku.setValue(1,1,-1);
        assertTrue(sudoku.getValue(1, 1) == 1);
    }
    
    
    @Test
    public void setAndGetDifficultyWorksCorrectly() {
        sudoku.setDifficulty(35);
        assertTrue(sudoku.getDifficulty() == 35);
    }
    
    @Test
    public void addDifficultyLevelWorksCorrectly() {
        int[][] grid = new int[][]{
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {2,3,4,5,6,7,8,9,1},
            {5,6,7,8,9,1,2,3,4},
            {8,9,1,2,3,4,5,6,7},
            {3,4,5,6,7,8,9,1,2},
            {6,7,8,9,1,2,3,4,5},
            {9,1,2,3,4,5,6,7,8}
            };
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku.setValue(i, j, grid[i][j]);
            }
        }
        sudoku.addDifficultyLevel(25);
        int emptied = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.getValue(i, j) == 0) {
                    emptied++;
                }
            }
        }
        assertTrue(emptied == 25);
    }
   
}
