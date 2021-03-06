/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.SQLException;
import java.util.List;
import sudoku.domain.SudokuScore;

/**
 * DAO interface.
 * @author sareetta
 */
public interface SQLDao {
    /**
     * Method for saving a score.
     * 
     * @param score         The score to be saved.
     * @param tableName     The table from database.
     * @throws SQLException If exception occurs.
     */
    void save(SudokuScore score, String tableName) throws SQLException;

    /**
     * Method for getting the list of the scores.
     * @param tableName     The table from the database.
     * @return              List of the scores.
     * @throws SQLException If exception occurs.
     */
    List<SudokuScore> list(String tableName) throws SQLException;
}
