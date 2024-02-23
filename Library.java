// Name: Carole Youssef
// Student ID: 501156366
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			 songs;
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	 playlists;
    private ArrayList<Podcast> 	      podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions

	/* String errorMsg = ""; public String getErrorMessage() {return errorMsg;} */

	// instead of error msg, throws appropriate exceptions for each function in class library
	public Library()
	{
		songs 		= new ArrayList<Song>();
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
		podcasts	= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content) throws AudioContentDownloadedException, AudioContentNotFoundException
	{
		if (content == null){ // throws exception if content is null
			throw new AudioContentNotFoundException("Content not found in store");
		}
		if (content.getType().equals(Song.TYPENAME)){ // check if audio content is a song
			Song song = (Song) content;

			if(!songs.contains(song)){ // if the song hasn't been downloaded already
				songs.add(song); // add it to the list of songs

			}
			else {
				throw new AudioContentDownloadedException("Song " + song.getTitle() + " already downloaded");
			}
		}

		else if (content.getType().equals(AudioBook.TYPENAME)){ // check if audio content is an audioBook
			AudioBook audiobook = (AudioBook) content;

			if(!audiobooks.contains(audiobook)){ // if the audiobook hasn't been downloaded already
				audiobooks.add(audiobook); // add it to the list of audiobooks

			}
			else {
				throw new AudioContentDownloadedException("AudioBook " + audiobook.getTitle() + " already downloaded");
			}
		}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		for (int i = 0; i < podcasts.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			podcasts.get(i).printInfo();
			System.out.println();
		}
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			System.out.print(playlists.get(i).getTitle());
			System.out.println();
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> newList = new ArrayList<>();
		for (int i = 0; i < songs.size(); i++){
			if(!newList.contains(songs.get(i).getArtist())){
				newList.add(songs.get(i).getArtist());
			}
		}

		for (int i = 0; i < newList.size(); i++){
			int index = i + 1;
			System.out.print("" + index + ". ");
			System.out.println(newList.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index) throws AudioContentNotFoundException
	{
		if (index < 1 || index > songs.size()) {
			throw new AudioContentNotFoundException("Song not found");
		}

		Song song = songs.remove(index-1);

		// Search all playlists
		for (int i = 0; i < playlists.size(); i++)
		{
			Playlist pl = playlists.get(i);
			if (pl.getContent().contains(song))
				pl.getContent().remove(song);
		}
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort()
		Collections.sort(songs, new SongYearComparator());

	}

	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator <Song> {

	  public int compare(Song s1, Song s2) {
		  if(s1.getYear() < s2.getYear()) return -1;
		  if(s1.getYear() > s2.getYear()) return 1;
		  return 0;
	  }
  	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort()
		Collections.sort(songs, new SongLengthComparator());
	}

	// Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator <Song> {

	  public int compare(Song s1, Song s2) {
		  if (s1.getLength() < s2.getLength()) return -1;
		  if (s1.getLength() > s2.getLength()) return 1;
		  return 0;
	  }
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	// make sure the index is valid
	public void playSong(int index) throws AudioContentNotFoundException
	{
		if (index < 1 || index > songs.size())
		{
			throw new AudioContentNotFoundException ("Song not found");
		}
		songs.get(index-1).play();
	}

	// Play a chapter of an audiobook from list of audiobooks
	// make sure the index is valid
	public void playAudioBook(int index, int chapter) throws AudioContentNotFoundException, ChapterNotFoundException
	{

		if (index < 1 || index > audiobooks.size()) {
			throw new AudioContentNotFoundException ("Audiobook not found");
		}

		AudioBook audiobook = audiobooks.get(index-1); //get the current audiobook
		if(chapter < 1 || chapter > audiobook.getNumberOfChapters()) {
			throw new ChapterNotFoundException ("Chapter not found");
		}

		audiobook.selectChapter(chapter);
		audiobook.play();
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	// make sure the index is valid
	public void printAudioBookTOC(int index) throws AudioContentNotFoundException
	{
		if (index < 1 || index > audiobooks.size()) {
			throw new AudioContentNotFoundException ("Audiobook not found");
		}

		AudioBook audiobook = audiobooks.get(index-1);
		audiobook.printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) throws PlaylistAlreadyExistsException
	{
		for(Playlist p: playlists) {
			if (p.getTitle().equals(title)) {
				throw new PlaylistAlreadyExistsException("Playlist " + title + " already exists");
			}
		}

		Playlist playlist = new Playlist(title);
		playlists.add(playlist);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title) throws PlaylistNotFoundException
	{
		boolean found = false;
		for(Playlist p: playlists) {
			if (p.getTitle().equals(title)) {
				p.printContents();
				found = true;
			}
		} if (!found) throw new PlaylistNotFoundException("Playlist " + title + " not found");
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) throws PlaylistNotFoundException
	{
		boolean found = false;
		for(Playlist p: playlists) {
			if (p.getTitle().equals(playlistTitle)) {
				p.playAll();
				found = true;
			}
		} if (!found) throw new PlaylistNotFoundException("Playlist " + playlistTitle + " not found");
	}
	
	// Play a specific song/audiobook/podcast in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
			throws AudioContentNotFoundException, PlaylistNotFoundException
	{
		boolean found = false;
		for(Playlist p: playlists) {
			if (p.getTitle().equals(playlistTitle)) {
				if (indexInPL < 1 || indexInPL > p.getContent().size()) {
					throw new AudioContentNotFoundException ("Audio content not found");
				}
				p.play(indexInPL);
				found = true;
			}
		} if (!found) throw new PlaylistNotFoundException("Playlist " + playlistTitle + " not found");
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
			throws AudioContentNotFoundException, PlaylistNotFoundException
	{
		boolean found = false;
		for(Playlist p: playlists){
			if(p.getTitle().equals(playlistTitle)){

				if (type.equals(Song.TYPENAME)){ // check if audio content is a song
					if (index < 1 || index > songs.size()) { // check if index is valid
						throw new AudioContentNotFoundException( "Song Not Found");
					}
					p.addContent(songs.get(index-1)); // add song
					found = true;
				}
				if (type.equals(AudioBook.TYPENAME)){ // check if audio content is an audioBook
					if (index < 1 || index > audiobooks.size()) { // check if index is valid
						throw new AudioContentNotFoundException("AudioBook Not Found");
					}
					p.addContent(audiobooks.get(index-1)); // add audioBook
					found = true;
				}
			}
		} if(!found) throw new PlaylistNotFoundException("Playlist " + playlistTitle + " not found");
	}

    // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
			throws AudioContentNotFoundException, PlaylistNotFoundException
	{
		boolean found = false;
		for(Playlist p: playlists) {
			if (p.getTitle().equals(title)) {
				if (index < 1 || index > p.getContent().size()) // check if index is valid
				{
					throw new AudioContentNotFoundException("Audio content not found");
				}
				else {
					p.deleteContent(index); // delete the content
					found = true;
				}
			}
		} if (!found) throw new PlaylistNotFoundException("Playlist not found");
	}
}
	/*custom exceptions for the methods created are shown below - specific to Library */
	// exception for audio content not found e.g. song, audiobook
	class AudioContentNotFoundException extends RuntimeException {
		public AudioContentNotFoundException(String message){
			super(message);
		}
	}

	// exception for chapter not found
	class ChapterNotFoundException extends RuntimeException {
		public ChapterNotFoundException(String message){
			super(message);
		}
	}

	// exception for playlist not found
	class PlaylistNotFoundException extends RuntimeException{
		public PlaylistNotFoundException(String message){
			super(message);
		}
	}

	// exception for playlist already existing
	class PlaylistAlreadyExistsException extends RuntimeException {
		public PlaylistAlreadyExistsException(String message){
			super(message);
		}
	}

	// exception for audio content already downloaded e.g. song, audiobook
	class AudioContentDownloadedException extends RuntimeException {
		public AudioContentDownloadedException (String message){
			super(message);
		}
	}



