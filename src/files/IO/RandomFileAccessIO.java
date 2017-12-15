/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.IO;

import files.business.Mp3File;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author gerry
 */
public class RandomFileAccessIO {
    private static int FIELD_SIZE=32;
    private static int RECORD_SIZE=FIELD_SIZE*2 + FIELD_SIZE*2 +FIELD_SIZE*2 +FIELD_SIZE*2 +8;

    //************************************************************************
    //returns object to our random access file
    //************************************************************************
    public static RandomAccessFile getWriteRandomAccessFile(File rndFile)
    {
        RandomAccessFile mp3outRandom=null;
        try
        {
           mp3outRandom=new RandomAccessFile(rndFile,"rw");
        }
        catch(Exception e){}
        return mp3outRandom;
    }


    //random access file help method for fixes length strings
    public static String getFixedString(
    DataInput in, int length) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            char c = in.readChar();
            // if char is not Unicode zero add to string
            if (c != 0)
                sb.append(c);
        }
        return sb.toString();
    }


    public static void writeFixedString(DataOutput out, int length, String s) throws IOException
    {
        for (int i = 0; i < length; i++)
        {
            if (i < s.length())
                out.writeChar(s.charAt(i));    // write char
            else
                out.writeChar(0);      // write unicode zero
        }
    }

    //write a record to the file at a given postion
    public static boolean writeRandomAccessMp3File(RandomAccessFile rndout,Mp3File mp3,int recordNum)
    {
        try
        {
            rndout.seek(recordNum*RECORD_SIZE);
            writeFixedString(rndout,FIELD_SIZE,mp3.getsArtist());
            writeFixedString(rndout,FIELD_SIZE,mp3.getsAlbum());
            //year,genre track
            rndout.writeInt(mp3.getiYear());
            writeFixedString(rndout,FIELD_SIZE,mp3.getsGenre());
            writeFixedString(rndout,FIELD_SIZE,mp3.getsTrack());

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    //add a record to the file at the end
    public static boolean addRecord(RandomAccessFile rndout,Mp3File mp3)
    {
        int recordNum=-1;

        try
        {
            //write to the next space after last record
            recordNum=getRandomFileRecordCount(rndout);

            rndout.seek(recordNum*RECORD_SIZE);

            String sArtist=mp3.getsArtist();
            String sAlbum=mp3.getsAlbum();
            String sGenre=mp3.getsGenre();
            int iYear=mp3.getiYear();
            String sTrack=mp3.getsTrack();


            writeFixedString(rndout,FIELD_SIZE,sArtist);
            writeFixedString(rndout,FIELD_SIZE,sAlbum);
            //year,genre track
            rndout.writeInt(iYear);
            writeFixedString(rndout,FIELD_SIZE,sGenre);
            writeFixedString(rndout,FIELD_SIZE,sTrack);

            return true;
        }
        catch (Exception e)
        {
            System.out.println("Error Adding Record to RandomAccessFile.");
            return false;
        }
    }


    public static Mp3File getRecordFromRandomFile(RandomAccessFile f,int iRecordNumber)throws IOException
    {
        if(iRecordNumber>=0 && iRecordNumber<getRandomFileRecordCount(f))
        {
            f.seek(iRecordNumber * RECORD_SIZE);
            String sArtist=getFixedString(f,FIELD_SIZE);
            String sAlbum=getFixedString(f,FIELD_SIZE);
            int iYear=f.readInt();
            String sGenre=getFixedString(f,FIELD_SIZE);
            String sTrack=getFixedString(f,FIELD_SIZE);

            Mp3File mp3=new Mp3File(sArtist,sAlbum,sGenre,iYear,sTrack);

            return mp3;
        }
        else
            return null;
    }



    //read all from random file
    public static ArrayList <Mp3File> getRecordsFromRandomFile(RandomAccessFile f)throws IOException
    {
        ArrayList<Mp3File> mp3Files=new ArrayList <Mp3File>();
        try
        {
            System.out.println("num records " + getRandomFileRecordCount(f));

            for(int x=0;x<getRandomFileRecordCount(f);x++)
            {
                Mp3File mp3=getRecordFromRandomFile(f,x);
                mp3Files.add(mp3);
            }
        }
        catch (Exception e)
        {
        }

        return mp3Files;
    }



    //count how many records are in the rndfile
    public static int getRandomFileRecordCount(RandomAccessFile f) throws IOException
    {
        int recordCount=(int)f.length()/RECORD_SIZE;
        return recordCount+1;
    }
}
