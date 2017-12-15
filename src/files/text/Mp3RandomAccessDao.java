/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.text;

import files.IO.RandomFileAccessIO;
import files.business.Mp3File;
import files.exceptions.DaoException;
import files.interfaces.Mp3FileDao;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerry
 */
public class Mp3RandomAccessDao implements Mp3FileDao{
    
    File mp3RandomAccessFile=null;
    RandomAccessFile rndOut=null;

    public Mp3RandomAccessDao()
    {        
        mp3RandomAccessFile=new File("randomaccess.txt");
        rndOut=RandomFileAccessIO.getWriteRandomAccessFile(mp3RandomAccessFile);
    }

    public void saveFile(ArrayList<Mp3File> mp3List) throws DaoException {
        for(int x=0;x<mp3List.size();x++)
        {
            //RandomAccessFile rndout,Mp3File mp3,int recordNum)
            RandomFileAccessIO.writeRandomAccessMp3File(rndOut, mp3List.get(x), x);
        }
    }

    public ArrayList<Mp3File> findAll() throws DaoException {
        ArrayList <Mp3File> inputList=new ArrayList();

        try {
            inputList = RandomFileAccessIO.getRecordsFromRandomFile(rndOut);
        } catch (IOException ex) {
            Logger.getLogger(Mp3RandomAccessDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inputList;
    }

    public void add(Mp3File mp3) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int update(Mp3File mp3, int iRecordNumber) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(int recordID) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public String getFileName()
    {
        return mp3RandomAccessFile.toString();
    }

}
