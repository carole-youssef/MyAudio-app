// Name: Carole Youssef
// Student ID: 501156366
import java.util.ArrayList;
/*
* A Season stores all the information about an episode, including the episode titles, files and lengths
* this allows the current episode information to be retrieved depending on the current season
*/
public class Season {

    public ArrayList<String> episodeFiles;
    public ArrayList<String> episodeTitles;
    public ArrayList<Integer> episodeLengths;

    public Season(){

        /* Initialize instance variables as new, empty arraylists
         * holds the episode information for a specific season - titles, files and lengths */
        this.episodeFiles = new ArrayList<>();
        this.episodeTitles =  new ArrayList<>();
        this.episodeLengths = new ArrayList<>();
    }

    //Getters and Setters for each variable
    public ArrayList<String> getEpisodeTitles() { return episodeTitles; }
    public void setEpisodeTitles(ArrayList<String> episodeTitles) { this.episodeTitles = episodeTitles; }

    public ArrayList<String> getEpisodeFiles() { return episodeFiles; }
    public void setEpisodeFiles(ArrayList<String> episodeFiles) { this.episodeFiles = episodeFiles; }

    public ArrayList<Integer> getEpisodeLengths() { return episodeLengths; }
    public void setEpisodeLengths(ArrayList<Integer> episodeLengths) { this.episodeLengths = episodeLengths; }

    //finding the number of episodes in a season by checking the size of the episode files arraylist
    public int getNumberOfEpisodes(){ return episodeFiles.size();}





}
