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
public class BinaryIO {

    //Binary read and write,boolean  value for append
    public static void writeBin(Mp3File mp3,File myBinFile,boolean bAppend)
    {
        DataOutputStream outBin=FileIOUtil.getDataOutputStream(myBinFile, bAppend);
        //to write we need to use the UTF functions
        try{
            outBin.writeUTF(mp3.getsArtist());
            outBin.writeUTF(mp3.getsAlbum());
            outBin.writeInt(mp3.getiYear());
            outBin.writeUTF(mp3.getsGenre());
            outBin.writeUTF(mp3.getsTrack());

        }
        catch(Exception e){}

        //close
        FileIOUtil.close(outBin);
    }



    //write the contents of an arraylist to a binary file
    public static void writeArrayListToBinFile(ArrayList<Mp3File> mp3Files,File myBinFile,boolean bAppend)
    {
        DataOutputStream outBin=FileIOUtil.getDataOutputStream(myBinFile, bAppend);
        //to write we need to use the UTF functions
        try{
            for(int x=0;x<mp3Files.size();x++)
            {
                outBin.writeUTF(mp3Files.get(x).getsArtist());
                outBin.writeUTF(mp3Files.get(x).getsAlbum());
                outBin.writeInt(mp3Files.get(x).getiYear());
                outBin.writeUTF(mp3Files.get(x).getsGenre());
                outBin.writeUTF(mp3Files.get(x).getsTrack());
            }

        }
        catch(Exception e){}

        //close
        FileIOUtil.close(outBin);
    }


    //read a binary file in and return contents to an arraylist
    public static ArrayList<Mp3File> readAllBin(File myBinFile) throws IOException
    {
        ArrayList<Mp3File> mp3Files = new ArrayList<Mp3File>();

        DataInputStream inBin=FileIOUtil.getDataInputStream(myBinFile);

        String sArtist="";
        String sAlbum="";
        String sGenre="";
        int iYear=0;
        String sTrack="";

        try
        {
            while(true)
            {
                sArtist=inBin.readUTF();
                sAlbum=inBin.readUTF();
                iYear=inBin.readInt();
                sGenre=inBin.readUTF();
                sTrack=inBin.readUTF();

                Mp3File mp3bin=new Mp3File(sArtist,sAlbum,sGenre,iYear,sTrack);

                mp3Files.add(mp3bin);
            }
        }
        catch(EOFException e){}

        //close
        FileIOUtil.close(inBin);

        return mp3Files;

    }

}
