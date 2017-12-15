/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.IO;

import files.Utils.FileIOUtil;
import files.business.Mp3File;
import java.io.*;
import java.util.ArrayList;


/**
 *
 * @author gerry
 */
public class SerializeIO {

    public static void writeSerialized(File f,ArrayList<Mp3File> mp3List) throws IOException
    {
        ObjectOutputStream out=FileIOUtil.getObjectOutputStream(f,false);
        for (Mp3File mp3 : mp3List) {
            out.writeObject(mp3);
        }
    }//end of method

    public static void writeAppendSerialized(File f,Mp3File mp3) throws IOException
    {
        ObjectOutputStream out=FileIOUtil.getObjectOutputStream(f,true);
        out.writeObject(mp3);

    }//end of method

    //read in serilaized file and add to arraylist and return arraylist
   public static ArrayList<Mp3File> readSerialized(File f) throws IOException
   {
        ArrayList <Mp3File> mp3List=new ArrayList <Mp3File>();
        Mp3File mp3;
        //get an OIS
        ObjectInputStream in=FileIOUtil.getObjectInputStream(f);

        try {
            while (true)
            {
                //read an object
                mp3 = (Mp3File) in.readObject();
                //print out for testing
                System.out.println(mp3.toString());
                //add to array List
                mp3List.add(mp3);
            }
        } 
        catch (EOFException eofe)
        {
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("Unable to create Objects");
        }

        //return array list
        return mp3List;

    }

}//end of file
