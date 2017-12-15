/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.business;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gerry
 */
public class Mp3File implements Comparable,Serializable{
    private String sArtist;
    private String sAlbum;
    private String sGenre;
    private int iYear;
    private String sTrack;

    /*possable additions and ideas
    rating,favorite etc*/


    public Mp3File()
    {
    
    }

    public Mp3File(String sArtist, String sAlbum, String sGenre, int iYear, String sTrack) {
        this.sArtist = sArtist;
        this.sAlbum = sAlbum;
        this.sGenre = sGenre;
        this.iYear = iYear;
        this.sTrack=sTrack;

        /*String sTempArray[];

        sTempArray=Utilities.ParseTrackList(sTrackListString);
        for(int x=0;x<sTempArray.length;x++)
        {
            sTrackList.add(sTempArray[x]);
        }

        this.iTrackNum=sTrackList.size();
        System.out.println("Found " + iTrackNum + " tracks.");*/
    }


    //Getters
    /*public int getiNumTracks() {
        return sTrackList.size();
    }*/

    public int getiYear() {
        return iYear;
    }

    public String getsAlbum() {
        return sAlbum;
    }

    public String getsArtist() {
        return sArtist;
    }

    public String getsGenre() {
        return sGenre;
    }

    public String getsTrack()
    {
        return sTrack;
    }


    public void setiYear(int iYear) {
        this.iYear = iYear;
    }

    public void setsAlbum(String sAlbum) {
        this.sAlbum = sAlbum;
    }

    public void setsArtist(String sArtist) {
        this.sArtist = sArtist;
    }

    public void setsGenre(String sGenre) {
        this.sGenre = sGenre;
    }
    
    public void setsTrack(String sTrack)
    {
        this.sTrack=sTrack;
    }


    //////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "Mp3File{" + 
                "\nsArtist=" + sArtist +
                "\nsAlbum=" + sAlbum +
                "\nsGenre=" + sGenre +
                "\niYear=" + iYear +
                "\nsTrack=" + sTrack + '}';
    }

    public void printMp3File()
    {
        System.out.println( "Mp3File:" +
                "\nsArtist=" + sArtist +
                "\nsAlbum=" + sAlbum +
                "\nsGenre=" + sGenre +
                "\niYear=" + iYear +
                "\niTrack=" + sTrack);
    }


    public int compareTo(Object otherMp3File)
    {
       Mp3File tempMp3File = (Mp3File)otherMp3File;
       return sArtist.compareToIgnoreCase(tempMp3File.sArtist);
    }






}//end of file
