package android.bignerdranch.practice4.Class;

/** Album class */
public class Album {

    private String Artist;
    private String Album;
    private String Year;


    /** Constructor and empty constructor */
    public Album(String artist, String album, String year) {
        Artist = artist;
        Album = album;
        Year = year;
    }

    public Album() {
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }
}