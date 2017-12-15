/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.text;

import files.IO.TextIO;
import files.business.Mp3File;
import files.exceptions.DaoException;
import files.interfaces.Mp3FileDao;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author gerry
 */
public class Mp3FileTextDao implements Mp3FileDao{
    File mp3TextFile=null;

    public Mp3FileTextDao()
    {
        mp3TextFile=new File("textdao.txt");
        
        //my exported datafile with mrdj works fine
        //mp3TextFile=new File("mrdjexportjava.txt");
    }


    //create a simple file
    public void saveFile(ArrayList <Mp3File> mp3List) throws DaoException {
        //save array to file i.e artist:album: etc with : as delimter
        TextIO.saveArrayList(mp3List, mp3TextFile);
    }

    //read in all records on file and retunr results as an arraylist
    public ArrayList<Mp3File> findAll() throws DaoException {
        
        //create arraylist
        ArrayList <Mp3File> inputMp3List=new ArrayList<Mp3File>();
        //read in
        inputMp3List=TextIO.readFile(mp3TextFile);

        return inputMp3List;
    }


    //append data to the file
    public void add(Mp3File mp3) throws DaoException {
        //append data to file
        TextIO.add(mp3, mp3TextFile);
    }

    //update
    public int update(Mp3File mp3,int iRecordNumber) throws DaoException {
        //read in file
        TextIO.update(mp3, mp3TextFile, iRecordNumber, true);

        return 0;

    }

    //delete
    public int delete(int recordID) throws DaoException {
        //delete record number
        TextIO.update(null, mp3TextFile, recordID, false);

        return 0;
    }

    public String getFileName()
    {
        return mp3TextFile.toString();
    }

}
