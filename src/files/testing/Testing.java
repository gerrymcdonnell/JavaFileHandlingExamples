/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.testing;

import files.IO.BinaryIO;
import files.Utils.FileIOUtil;
import files.IO.RandomFileAccessIO;
import files.IO.SerializeIO;
import files.IO.TextIO;
import files.Utils.Utilities;
import files.business.Mp3File;
import files.exceptions.DaoException;
import files.text.Mp3FileTextDao;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerry
 */
public class Testing {
    static final String TEXT_FILE="test.txt";
    static final String BIN_FILE="binary.txt";
    static final String RND_FILE="randomaccess.txt";
    static final String SERIALIZE_FILE="mp3serialize.txt";


    public static void main(String args[])
    {
        ArrayList<Mp3File> mp3List = new ArrayList<Mp3File>();

        //create data and add to array list
        //****************************************************
        mp3List=createData();
        //****************************************************

        //test sort
        //****************************************************
        //Collections.sort(mp3List);
        //Utilities.printMp3ArrayList(mp3List);
        //****************************************************
        

        //test text file IO TESTED
        //****************************************************
        //testTextFiles(mp3List);
        //****************************************************


        //tested ok
        //****************************************************
        //testBinaryFile(mp3List);
        //****************************************************



        //test random accessfile TESTED OK
        //****************************************************
        //testRandomAccessFile(mp3List);
        //*******************************************



        //****************************************************
        //testSerialize OK
        //****************************************************
        //testSerialize(mp3List);




        //****************************************************
        //test text DAO interface
        //****************************************************
        testTextDao(mp3List);

    }



    //****************************************************
    //METHODS START HERE
    //****************************************************


    //create data and add to arraylist
    public static ArrayList <Mp3File> createData()
    {
        ArrayList<Mp3File> mp3Files = new ArrayList<Mp3File>();

        //String sArtist, String sAlbum, String sGenre, int iYear, String sTrackListString
        Mp3File f1=new Mp3File("Oasis","be here now","rock",1998,"track 1");
        Mp3File f2=new Mp3File("Foo Fighters","nothing left to lose","rock",1998,"nothing left to lose");

        Mp3File f4=new Mp3File("Paul van dyk","out there and back","trance",1999,"pikes");

        Mp3File f3=new Mp3File();
        f3.setsArtist("doves");
        f3.setsAlbum("Kingdom of rust");
        f3.setiYear(2009);
        f3.setsGenre("rock");
        f3.setsTrack("kingdom of rust");

        //add files to arraylist collection
        mp3Files.add(f1);
        mp3Files.add(f2);
        mp3Files.add(f3);
        mp3Files.add(f4);

        return mp3Files;

    }

    public static void testTextFiles(ArrayList<Mp3File> mp3Files)
    {
        ArrayList<Mp3File> mp3ReadTextFile = new ArrayList<Mp3File>();
        ArrayList<Mp3File> mp3SearchResults = new ArrayList<Mp3File>();
        String sSearch="";

        File txtFile=new File(TEXT_FILE);

         //delete file if it exists, juut for testing
        //****************************************************
        FileIOUtil.deleteFileIfExists(txtFile);
        //****************************************************


        //append objects to file
        Mp3File mp3File1=new Mp3File("manic street preachers","album","genre",1998,"track1");
        Mp3File mp3File2=new Mp3File("guns and roses","album","genre",1998,"track1");
        Mp3File mp3File3=new Mp3File("guns and roses","dfdfdfd","genre",1998,"track1");

        //save arraylist to file
        TextIO.saveArrayList(mp3Files, txtFile);

        //append object to text file
        TextIO.add(mp3File1, txtFile);
        TextIO.add(mp3File2, txtFile);

        //read file in
        mp3ReadTextFile=TextIO.readFile(txtFile);
        
        //printit out
        Utilities.printMp3ArrayList(mp3ReadTextFile);



        //****************************************************
        //Test updating a record
        //****************************************************
        System.out.println("Updating Record:");
        int iUpdateRecord=0;
        boolean bUpdateRecord=true;
        //update record and replace its contents with modified object
        TextIO.update(mp3File3, txtFile, iUpdateRecord,bUpdateRecord);

        //read file in and printit out after update
        mp3ReadTextFile=TextIO.readFile(txtFile);
        Utilities.printMp3ArrayList(mp3ReadTextFile);
        //****************************************************

        

        //****************************************************
        //test delete, i.e delete record 1
        //****************************************************
        int iDeleteRecord=1;
        System.out.println("Deleting Record:" + iDeleteRecord);
        TextIO.update(mp3File3, txtFile, iDeleteRecord,false);

        //read file in and printit out after update
        mp3ReadTextFile=TextIO.readFile(txtFile);
        Utilities.printMp3ArrayList(mp3ReadTextFile);
        //****************************************************




        //****************************************************
        //test search for artist
        sSearch="oasis";

        mp3SearchResults=Utilities.searchArrayListForArtist(mp3Files, sSearch);
        if(mp3SearchResults!=null)
        {
            System.out.println("=======================");
            System.out.println("Search Results...");
            System.out.println("=======================");
            Utilities.printMp3ArrayList(mp3SearchResults);
        }
        else
        {
            System.out.println("No matches found for " + sSearch);
        }
        //****************************************************
        


         //****************************************************
        //test search for genre
        sSearch="trance";

        mp3SearchResults=Utilities.searchArrayListForGenre(mp3Files, sSearch);
        if(mp3SearchResults!=null)
        {
            System.out.println("=======================");
            System.out.println("Search Results...");
            System.out.println("=======================");
            
            Utilities.printMp3ArrayList(mp3SearchResults);
        }
        else
        {
            System.out.println("No matches found for " + sSearch);
        }
        //****************************************************

    }

    //test binary file
    public static void testBinaryFile(ArrayList<Mp3File> mp3Files)
    {
        File binFile=new File(BIN_FILE);

        //arraylist to hold bin data when read in
        ArrayList<Mp3File> mp3ReadBinFile = new ArrayList<Mp3File>();
        
        //create a new mp3file object
        Mp3File mp3File1=new Mp3File("u2","zooropa","rock",1992,"lemon");

        //delete file if it exists, juut for testing
        //****************************************************
        FileIOUtil.deleteFileIfExists(binFile);
        //****************************************************
        

        //****************************************************
        //read arraylist and save to binary file
        //****************************************************
        for(int x=0;x<mp3Files.size();x++)
        {
            BinaryIO.writeBin(mp3Files.get(x), binFile,true);
        }
        //****************************************************


        //****************************************************
        //print binary File out
        //****************************************************
        System.out.println("\nprinting " + mp3ReadBinFile .size() + " collection of mp3files");
        Utilities.printMp3ArrayList(mp3ReadBinFile );
        //****************************************************



        //****************************************************
        //add the created object to bin file
        //****************************************************
        BinaryIO.writeBin(mp3File1, binFile,true);
        //****************************************************



        //****************************************************
        //read in and print binary File out
        //****************************************************
        try {
            //read back in binary file
            mp3ReadBinFile  =BinaryIO.readAllBin(binFile);
        } catch (IOException ex) {
            //Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }

        //****************************************************
        //print out
        //****************************************************
        System.out.println("\nprinting " + mp3ReadBinFile .size() + " collection of mp3files");
        Utilities.printMp3ArrayList(mp3ReadBinFile );
        //****************************************************


        //****************************************************
        //write out contents of arraylist to binary file
        //****************************************************
        //create another record
        Mp3File mp3File2=new Mp3File("supergrass","album","rock",2005,"going out");
        //add to arraylist
        mp3ReadBinFile.add(mp3File2);

        System.out.println("Saving ArrayList Contents to BinaryFile.");
        BinaryIO.writeArrayListToBinFile(mp3ReadBinFile, binFile, true);
        //****************************************************


        //****************************************************
        //print out
        //****************************************************
        System.out.println("\nprinting " + mp3ReadBinFile .size() + " collection of mp3files");
        Utilities.printMp3ArrayList(mp3ReadBinFile );
        //****************************************************

    }



    //****************************************************
    //random access file
    //****************************************************
    public static void testRandomAccessFile(ArrayList<Mp3File> mp3Files)
    {
        //arraylist to hold contents of read randomaccessfile
        ArrayList<Mp3File> mp3randomFiles=new ArrayList <Mp3File>();

        File rndFile=new File(RND_FILE);
        int recordNum=1;
        boolean bState;
        int iReadRecordNumber;
        int recCount=-1;

        //delete file if it exists, juut for testing
        //****************************************************
        FileIOUtil.deleteFileIfExists(rndFile);
        //****************************************************

        
        RandomAccessFile rndout=RandomFileAccessIO.getWriteRandomAccessFile(rndFile);

        //save arraylist to random accessfile
        System.out.println("Saving " + mp3Files.size() + " records.");
        for(int x=0;x<mp3Files.size();x++)
        {
            //file,object,record number
            bState=RandomFileAccessIO.writeRandomAccessMp3File(rndout, mp3Files.get(x), x);
        }

        //print them out
        Utilities.printMp3ArrayList(mp3Files);

        
        //****************************************************
        //print 1 record out i.e read a record from file and return it.
        //****************************************************
        iReadRecordNumber=0;

        Mp3File f4=new Mp3File();
        try {
            f4 = RandomFileAccessIO.getRecordFromRandomFile(rndout, iReadRecordNumber);
        } catch (IOException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("========================");
        System.out.println("Printing Record: [" + iReadRecordNumber + "]");
        System.out.println("========================");
        f4.printMp3File();
        //****************************************************




        
        //****************************************************
        //test read all
        //****************************************************
        System.out.println("========================");
        System.out.println("print randomfile read");
        System.out.println("========================");
        
        try {            
            recCount=RandomFileAccessIO.getRandomFileRecordCount(rndout);
            System.out.println("Found " + recCount + " records.");
            //read all records
            mp3randomFiles = RandomFileAccessIO.getRecordsFromRandomFile(rndout);
            //print them out
            Utilities.printMp3ArrayList(mp3randomFiles);
        } catch (IOException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //****************************************************

        
        
        //****************************************************
        //create a new object
        //****************************************************
        Mp3File mp3File1=new Mp3File("u2","zooropa","rock",1992,"lemon");
        //add it to file NOT WORKING
        bState=RandomFileAccessIO.addRecord(rndout, mp3File1);
        //****************************************************



        
        //****************************************************
        //test read all
        //****************************************************
        System.out.println("===========================================");
        System.out.println("print randomfile read after new object added");
        System.out.println("===========================================");

        try {
            recCount=RandomFileAccessIO.getRandomFileRecordCount(rndout);
            System.out.println("Found " + recCount + " records.");
            //read all records
            mp3randomFiles = RandomFileAccessIO.getRecordsFromRandomFile(rndout);
            //print them out
            Utilities.printMp3ArrayList(mp3randomFiles);
        } catch (IOException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //****************************************************

        

    }//end of method

    public static void testSerialize(ArrayList<Mp3File> mp3Files)
    {
        File f=new File(SERIALIZE_FILE);
        ArrayList <Mp3File> mp3ReadSerialize=new ArrayList <Mp3File>();

        //test object
        Mp3File mp3File1=new Mp3File("manic street preachers","album","genre",1998,"track1");

        //test write of objects to file, write contents of array list to file
        //****************************************************
        try {
            SerializeIO.writeSerialized(f, mp3Files);
        } catch (IOException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //****************************************************


        //read in serialized file into arraylist mp3ReadSerialize
        //****************************************************
        try {
            mp3ReadSerialize=SerializeIO.readSerialized(f);
        } catch (IOException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //****************************************************


        //print out arraylist
        //****************************************************
        Utilities.printMp3ArrayList(mp3ReadSerialize);
        //****************************************************



        //****************************************************
        //test appened - not working
        //****************************************************
        /*try {
            //test append
            SerializeIO.writeAppendSerialized(f, mp3File1);
        } catch (IOException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //read in serialized file into arraylist mp3ReadSerialize after append
        //****************************************************
        try {
            mp3ReadSerialize=SerializeIO.readSerialized(f);
        } catch (IOException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //****************************************************
        //print out arraylist
        //****************************************************
        Utilities.printMp3ArrayList(mp3ReadSerialize);
        //****************************************************

        */


    }//end method

    //test Text DAO
    public static void testTextDao(ArrayList<Mp3File> mp3Files)
    {
        ArrayList <Mp3File> mp3ReadResults=new ArrayList <Mp3File>();

        int iRecordNumber;

        //test object
        Mp3File mp3File1=new Mp3File("manic street preachers","album","genre",1998,"track1");

        //create interface object
        Mp3FileTextDao mp3TextDao=new Mp3FileTextDao();

        //save out test data arraylist to file
        try {
            //pass our arraylist of data in and create a file
            mp3TextDao.saveFile(mp3Files);
        } catch (DaoException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }

        //read the file back into an array list
        try {
            mp3ReadResults = mp3TextDao.findAll();
        } catch (DaoException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }

        //print out the arraylist
        System.out.println("TextDAO: Printing File");
        Utilities.printMp3ArrayList(mp3ReadResults);
        
        
        //test adding a record
        try {
            mp3TextDao.add(mp3File1);
        } catch (DaoException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }


        //read file back in and print out
        try {
            mp3ReadResults = mp3TextDao.findAll();
            //print out the arraylist
            System.out.println("TextDAO: Printing File after add()");
            Utilities.printMp3ArrayList(mp3ReadResults);
        } catch (DaoException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }




        //test delete
        /*iRecordNumber=0;
        try {
            //test delete
            mp3TextDao.delete(iRecordNumber);
            //read data in
            mp3ReadResults = mp3TextDao.findAll();
            //print out the arraylist
            System.out.println("TextDAO: Printing File after delete()");
            Utilities.printMp3ArrayList(mp3ReadResults);
        } catch (DaoException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }*/



        //test edit
        /*iRecordNumber=1;
        mp3File1.setsAlbum("edit album field");
        try {
            //test delete
            mp3TextDao.update(mp3File1, iRecordNumber);
            //read data in
            mp3ReadResults = mp3TextDao.findAll();

            //print out the arraylist
            System.out.println("TextDAO: Printing File after edit()");
            Utilities.printMp3ArrayList(mp3ReadResults);

        } catch (DaoException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }*/



    }




}//end of class
