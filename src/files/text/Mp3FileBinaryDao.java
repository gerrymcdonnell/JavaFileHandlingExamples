/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.text;

import files.IO.BinaryIO;
import files.IO.TextIO;
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
public class Mp3FileBinaryDao implements Mp3FileDao{
    File mp3BinaryFile=null;

    public Mp3FileBinaryDao()
    {
        mp3BinaryFile=new File("binary.txt");
    }


    //create a simple file
    public void saveFile(ArrayList <Mp3File> mp3List) throws DaoException {
        //save array to file i.e artist:album: etc with : as delimter
        BinaryIO.writeArrayListToBinFile(mp3List, mp3BinaryFile, false);
    }

    //read in all records on file and retunr results as an arraylist
    public ArrayList<Mp3File> findAll() throws DaoException {

        //create arraylist
        ArrayList <Mp3File> inputMp3List=new ArrayList<Mp3File>();

        try {
            //read in
            inputMp3List = BinaryIO.readAllBin(mp3BinaryFile);
        } catch (IOException ex) {
            Logger.getLogger(Mp3FileBinaryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inputMp3List;
    }


    //append data to the file
    public void add(Mp3File mp3) throws DaoException {
        //append data to file
        BinaryIO.writeBin(mp3, mp3BinaryFile,true);
    }

    public int update(Mp3File mp3, int iRecordNumber) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(int recordID) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getFileName()
    {
        return mp3BinaryFile.toString();
    }



}
