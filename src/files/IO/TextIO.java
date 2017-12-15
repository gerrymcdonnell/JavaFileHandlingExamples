/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.IO;

import files.Utils.FileIOUtil;
import files.Utils.Utilities;
import files.business.Mp3File;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author gerry
 */
public class TextIO {

    //read all lines in a text file into an array list
    public static ArrayList <Mp3File> readFile(File f)
    {
        ArrayList<Mp3File> mp3Files = new ArrayList<Mp3File>();
        String sLine="";
        BufferedReader br=FileIOUtil.getBufferedReader(f);

        try{
            sLine=br.readLine();
            while(sLine!=null)
            {
                //System.out.println("Line= " +sLine);

                //extract data and create mp3file
                Mp3File mp3file=Utilities.splitLine(sLine);
                //add to collection
                mp3Files.add(mp3file);
                //read next line
                sLine=br.readLine();
            }
        }
        catch(Exception e){
            System.out.println("error importing data file "+ f.toString());
        }

        FileIOUtil.close(br);
        return mp3Files;

    }//end



    //append to a text file
    public static void add(Mp3File mp3,File f)
    {
        String s= Utilities.makeLine(mp3);
        //System.out.println(s);

        //save
        PrintWriter pw=FileIOUtil.getPrintWriter(f,true);//append mode
        pw.println(s);

        FileIOUtil.close(pw);
    }
    


    //update a record number
    //read in the file into the array
    //change the data of the record in the array to the data of the passed in object
    public static boolean update(Mp3File mp3,File f,int iRecordNumber,boolean bUpdate)
    {
        ArrayList<Mp3File> mp3Files = new ArrayList<Mp3File>();
        mp3Files=readFile(f);
        int iRecCount=mp3Files.size();

        System.out.println("found " + iRecCount + " records");

        if(iRecordNumber>=0 && iRecordNumber<iRecCount && iRecCount >0)
        {
            //update the object with the new details
            if(bUpdate==true)
            {
                mp3Files.get(iRecordNumber).setsArtist(mp3.getsArtist());
                mp3Files.get(iRecordNumber).setsAlbum(mp3.getsAlbum());
                mp3Files.get(iRecordNumber).setsGenre(mp3.getsGenre());
                mp3Files.get(iRecordNumber).setsTrack(mp3.getsTrack());
                mp3Files.get(iRecordNumber).setiYear(mp3.getiYear());
            }
            else
            {
                //delete
                System.out.println("Deleting Record Num: "+ iRecordNumber);
                mp3Files.remove(iRecordNumber);
            }
            //save to file
            saveArrayList(mp3Files,f);
            return true;
        }
        else
        {
            System.out.println("RecordNumber out of valid range.");
            return false;
        }

    }//end of method



    //add all items in an array list to the text file
    public static void saveArrayList(ArrayList <Mp3File> mp3Files,File f)
    {
        String s="";
        PrintWriter pw=null;

        pw=FileIOUtil.getPrintWriter(f,false);//not append mode

        for(int i=0;i<mp3Files.size();i++)
        {
            s= Utilities.makeLine(mp3Files.get(i));
            //System.out.println("save array " + s);
            pw.println(s);
        }
        FileIOUtil.close(pw);
    }




}//end of file
