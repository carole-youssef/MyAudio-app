// Name: Carole Youssef
// Student ID: 501156366
import java.util.ArrayList;

/*
 * A Podcast is a type of AudioContent. A Podcast has extra fields such as host and seasons
 */
public class Podcast extends AudioContent
{

    public static final String TYPENAME = "PODCAST";
    private String host;
    private ArrayList<Season> seasons;
    private int currentEpisode = 0;
    private int currentSeason = 0;

    public Podcast(String title, int year, String id, String type, String audioFile, int length, String host,
                   ArrayList<Season> seasons) {

        /* Make use of the constructor in the super class AudioContent.
         * Initialize additional Podcast instance variables. */
        super(title, year, id, type, audioFile, length);
        this.host = host;
        this.seasons = seasons;
    }

    public String getType() {
        return TYPENAME;
    }

    /* Print information about the podcast. First print the basic information of the AudioContent
     * by making use of the printInfo() method in superclass AudioContent and then print host, seasons */
    public void printInfo() {
        super.printInfo();
        System.out.println("Host: " + host + "\n" + "Seasons: " + seasons.size());
    }

    /* Play podcast by setting the audioFile to the current episode title for a specific season (from seasons arraylist)
     * followed by the current episode file for a specific season (from seasons arraylist)
     * Then make use of the play() method of the superclass */
    public void play() {
        String title = seasons.get(currentSeason).getEpisodeTitles().get(currentEpisode) + "." + "\n";
        String file = seasons.get(currentSeason).getEpisodeFiles().get(currentEpisode);
        setAudioFile(title + file);
        super.play();
    }

    // Print the table of contents of the podcast - i.e. the list of Episode titles for a specific season
    public void printTOC() {
        for (int i = 0; i < seasons.get(currentSeason).getNumberOfEpisodes(); i++){
            int index = i + 1;
            System.out.println("Episode " + index + ". " + seasons.get(currentSeason).getEpisodeTitles().get(i));
            System.out.println();
        }

    }

    /* Two songs are equal if their AudioContent information is equal and both the host and seasons are the same
     * Make use of the superclass equals() method */
    @Override
    public boolean equals(Object other) {

        Podcast otherPod = (Podcast) other;
        return super.equals(otherPod) && host.equals(otherPod.host) && seasons.equals(otherPod.seasons);
    }

    //Select a specific season to play
    public void selectSeason(int season)
    {
        if (season >= 1 && season <= seasons.size())
        {
            currentSeason = season - 1;
        }
    }

    //Select a specific episode to play
    public void selectEpisode(int episode)
    {
        if (episode >= 1 && episode <= seasons.get(currentSeason).getNumberOfEpisodes())
        {
            currentEpisode = episode - 1;
        }
    }

    //Getters and Setters for each variable
    public String getHost()
    {
        return this.host;
    }
    public void setHost(String host)
    {
        this.host = host;
    }
    public ArrayList<Season> getSeasons()
    {
        return seasons;
    }
    public void setSeasons(ArrayList<Season> seasons)
    {
        this.seasons = seasons;
    }



}