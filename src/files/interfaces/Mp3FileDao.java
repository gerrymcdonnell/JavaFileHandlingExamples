/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.interfaces;

import files.business.Mp3File;
import files.exceptions.DaoException;
import java.util.ArrayList;

/**
 *
 * @author gerry
 */
public interface Mp3FileDao {

    void saveFile(ArrayList <Mp3File> mp3List) throws DaoException;
    ArrayList<Mp3File> findAll() throws DaoException;

    //Mp3File findById(int playerId) throws DaoException;
    //Mp3File findByUsernamePassword(String username, String password) throws DaoException;
    //ArrayList<Mp3File> findPlayersByNumLives(int numLives) throws DaoException;

    void add(Mp3File mp3)throws DaoException;
    int update(Mp3File mp3,int iRecordNumber)throws DaoException;
    int delete(int recordID)throws DaoException;
    String getFileName();
}


