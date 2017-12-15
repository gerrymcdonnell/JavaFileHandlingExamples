/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.Utils;

import files.business.Mp3File;
import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerry
 */
public class FileIOUtil {


//write
    public static PrintWriter getPrintWriter(File file,boolean bAppend)
    {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file,bAppend)));
        } catch (IOException e) {

        }
        return out;
    }


///read
    public static BufferedReader getBufferedReader(File file) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (IOException e) {

        }
        return in;
    }

    //binary
    //create a Dataoutstream to write a binary file
    public static DataOutputStream getDataOutputStream(File file,boolean bAppend)
    {
        DataOutputStream out=null;
        try{
            out = new DataOutputStream(
                       new BufferedOutputStream(
                       new FileOutputStream(file,bAppend)));
        }
        catch(IOException e){
        }
        return out;
    }
    //read binary file
    public static DataInputStream getDataInputStream(File file)
    {
        DataInputStream in=null;
        try{
            in = new DataInputStream(
                     new BufferedInputStream(
                     new FileInputStream(file)));
        }
        catch(IOException e)
        {
        }
        return in;
    }

    //get a objectoutputstream for serialize writing
    public static ObjectOutputStream getObjectOutputStream(File f,boolean bAppend)
    {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(f,bAppend));
        } catch (IOException ioe) {
            System.out.println("ERROR");}
        return out;
    }


    //get an objectinputstream for reading in a serialised file
    public static ObjectInputStream getObjectInputStream(File f)
    {
    ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(f));

        } catch (IOException ioe)
        {
            System.out.println("ERROR");
        }
        return in;
    }



    //close
    public static void close(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (IOException e) {
        }
    }

    
    //delete a file if it exists
    public static void deleteFileIfExists(File myFile)
    {
        try{
        if(myFile.exists()==true)
            myFile.delete();
        }
        catch(Exception e)
        {
            System.out.println("Error Deleting File: " + myFile.toString());
        }
    }

    
    





}
