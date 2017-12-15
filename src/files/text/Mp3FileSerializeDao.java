/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.text;

import files.IO.SerializeIO;
import files.business.Mp3File;
import files.exceptions.DaoException;
import files.interfaces.Mp3FileDao;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerry
 */
public class Mp3FileSerializeDao implements Mp3FileDao {
    File mp3SerializeFile=null;

    public Mp3FileSerializeDao()
    {
        mp3SerializeFile=new File("mp3serialize.txt");
    }

    public void saveFile(ArrayList<Mp3File> mp3List) throws DaoException {
        try {
            SerializeIO.writeSerialized(mp3SerializeFile, mp3List);
        } catch (IOException ex) {
            Logger.getLogger(Mp3FileSerializeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Mp3File> findAll() throws DaoException {
        //create arraylist
        ArrayList <Mp3File> inputMp3List=new ArrayList<Mp3File>();
        
        try {
            inputMp3List = SerializeIO.readSerialized(mp3SerializeFile);
        } catch (IOException ex) {
            Logger.getLogger(Mp3FileSerializeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inputMp3List;
    }

    public void add(Mp3File mp3) throws DaoException {
        
        try {
            SerializeIO.writeAppendSerialized(mp3SerializeFile, mp3);
        } catch (IOException ex) {
            Logger.getLogger(Mp3FileSerializeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int update(Mp3File mp3, int iRecordNumber) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(int recordID) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getFileName()
    {
        return mp3SerializeFile.toString();
    }

}
