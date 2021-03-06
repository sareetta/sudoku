/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.SudokuScore;


/**
 *
 * @author sareetta
 */
public class ScoreDaoTest {
    DBScore db;
    ScoreDao score;
    
    public ScoreDaoTest() throws SQLException {
    }
    
 
    @Before
    public void setUp() {
        String dbUrl = "jdbc:sqlite:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "test.db";
        db = new DBScore(dbUrl, "Easy", "Medium", "Hard");
        try {
            score = new ScoreDao(db, "Easy");
        } catch (SQLException e) {
            assert false;
        }
        db.connect();
    }
    
    @Test
    public void methodSaveWorks() {
        try {
            score.save(new SudokuScore(1,"rs","00:10"), "Easy");
        } catch (SQLException e) {
            assert false;
        }
        db.connect();
        ResultSet rs = db.select("SELECT * FROM Easy");
        if (rs == null) assert false;
        try {
            assertTrue(rs.getString("name").equals("rs"));
        } catch (SQLException e) {
            assert false;
        }
    }
    
    @Test 
    public void methodListWorks() throws SQLException {
        try {
            score.save(new SudokuScore(1,"rs","00:10"), "Easy");
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(score.list("Easy").get(0).getName().equals("rs"));
        
    }
    
    @After
    public void tearDown() throws IOException {
        db.close();
        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + "test.db"));
    }

}
