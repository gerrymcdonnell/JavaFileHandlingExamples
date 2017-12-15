/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.Utils;

import files.business.Mp3File;
import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import files.Utils.ExtensionFilter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


/**
 *
 * @author gerry
 */
public class Utilities {

    //public static final String TRACKSPLITCHAR=",";

    private final static String regex = ":";

    /*public static String[] ParseTrackList(String sTrackList)
    {
    
        String sTemp[]=sTrackList.split(TRACKSPLITCHAR);
        //System.out.println(sTemp.length);
        for(int i=0;i<sTemp.length;i++)
        {
            //System.out.println(sTemp[i]);
        }
    return sTemp;
    }*/



    public static String makeLine(Mp3File mp3)
    {

        String sArtist=mp3.getsArtist();
        String sAlbum=mp3.getsAlbum();
        String sGenre=mp3.getsGenre();
        int iYear=mp3.getiYear();
        String sTrack=mp3.getsTrack();


        String mp3fileStr = sArtist + regex + sAlbum + regex+
                sGenre +regex+
                String.valueOf(iYear) + regex + sTrack;

        return mp3fileStr;
    }

    //for use with table
    public static String makeLineCommaDelimited(Mp3File mp3)
    {

        String sArtist=mp3.getsArtist();
        String sAlbum=mp3.getsAlbum();
        String sGenre=mp3.getsGenre();
        int iYear=mp3.getiYear();
        String sTrack=mp3.getsTrack();


        String mp3fileStr = sArtist + "," + sAlbum + ","+
                sGenre +","+
                String.valueOf(iYear) + "," + sTrack;

        return mp3fileStr;
    }


//take a string with the contents of a main class delimted by : and create
//a mp3 file and return it
     public static Mp3File splitLine(String line) {
        String[] parts = line.split(regex);
        //int playerId = Integer.valueOf(parts[0]);
        String sArtist = parts[0];
        String sAlbum = parts[1];
        String sGenre=parts[2];
        int iYear = Integer.valueOf(parts[3]);

        String sTrack=parts[4];
        Mp3File mp3 = new Mp3File(sArtist, sAlbum,sGenre,iYear,sTrack);
        return mp3;
    }

    //print an arraylist of mp3file objects
    public static void printMp3ArrayList(ArrayList<Mp3File> mp3Files)
    {
        for(int x=0;x<mp3Files.size();x++)
        {
            System.out.println("\nRecord: [" + x+ "]");
            mp3Files.get(x).printMp3File();
        }
    }

    //search array list and return arraylist of results
    //artist
    public static ArrayList<Mp3File> searchArrayListForArtist(ArrayList<Mp3File> mp3Files,String sVal)
    {
        ArrayList<Mp3File> mp3FileResults = new ArrayList<Mp3File>();

        for(int x=0;x<mp3Files.size();x++)
        {
            if(mp3Files.get(x).getsArtist().toLowerCase().contains(sVal.toLowerCase()))
                mp3FileResults.add(mp3Files.get(x));
        }
        return mp3FileResults;
    }


    //album
    public static ArrayList<Mp3File> searchArrayListForAlbum(ArrayList<Mp3File> mp3Files,String sVal)
    {
        ArrayList<Mp3File> mp3FileResults = new ArrayList<Mp3File>();

        for(int x=0;x<mp3Files.size();x++)
        {
            if(mp3Files.get(x).getsAlbum().toLowerCase().contains(sVal.toLowerCase()))
                mp3FileResults.add(mp3Files.get(x));
        }
        return mp3FileResults;
    }

    //track
    public static ArrayList<Mp3File> searchArrayListForTrack(ArrayList<Mp3File> mp3Files,String sVal)
    {
        ArrayList<Mp3File> mp3FileResults = new ArrayList<Mp3File>();

        for(int x=0;x<mp3Files.size();x++)
        {
            if(mp3Files.get(x).getsTrack().toLowerCase().contains(sVal.toLowerCase()))
                mp3FileResults.add(mp3Files.get(x));
        }
        return mp3FileResults;
    }

    //genre
    public static ArrayList<Mp3File> searchArrayListForGenre(ArrayList<Mp3File> mp3Files,String sVal)
    {
        ArrayList<Mp3File> mp3FileResults = new ArrayList<Mp3File>();

        for(int x=0;x<mp3Files.size();x++)
        {
            if(mp3Files.get(x).getsGenre().toLowerCase().contains(sVal.toLowerCase()))
                mp3FileResults.add(mp3Files.get(x));
        }
        return mp3FileResults;
    }

    //year
    public static ArrayList<Mp3File> searchArrayListForYear(ArrayList<Mp3File> mp3Files,int iVal)
    {
        ArrayList<Mp3File> mp3FileResults = new ArrayList<Mp3File>();

        for(int x=0;x<mp3Files.size();x++)
        {
            if(iVal==mp3Files.get(x).getiYear())
                mp3FileResults.add(mp3Files.get(x));
        }
        return mp3FileResults;
    }

    //message box
    public static void showMsgBox(String s)
    {
        JOptionPane.showMessageDialog(null, s);
    }


    public static String saveFileDialog() {
        JFileChooser fc;
        fc = new JFileChooser();

        FileFilter filterXmlFiles = new ExtensionFilter("Album files", new String[]{".xml"});
        fc.setFileFilter(filterXmlFiles);
        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would save the file.
            System.out.println("Saving: " + file.toString());
            return file.toString();
        } else {
            System.out.println("Save command cancelled by user.");
            return "";
        }
    }//end

    //load the file open dialog
    public static String openFileDialog()//flag for saying we want to have the filter for album files or just images
    {
        JFileChooser fc;
        fc = new JFileChooser();

        FileFilter filterImgFiles = new ExtensionFilter("Image files", new String[]{".jpg", ".gif", "jpeg"});
        FileFilter filterXmlFiles = new ExtensionFilter("Album files", new String[]{".xml"});

        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

       fc.addChoosableFileFilter(filterXmlFiles);



        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //System.out.println("get path " + file.getAbsoluteFile());
            //This is where a real application would open the file.
            System.out.println("File " + file.toString());
            return file.toString();//returns file name eg G:\wallpapers\AG-PhotoCollection-04_www.softarchive.net\AG-PhotoCollection-04 (1).jpg
        } else {
            return "";
        }

    }//end open dialog


}//end of class
