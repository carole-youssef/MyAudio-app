// Name: Carole Youssef
// Student ID: 501156366

import java.util.*;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine()) {
			String action = scanner.nextLine();

			if (action == null || action.equals("")) {
				System.out.print("\n>");
				continue;
			} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("STORE"))    // List all songs
			{
				store.listAll();
			} else if (action.equalsIgnoreCase("SONGS"))    // List all songs
			{
				mylibrary.listAllSongs();
			} else if (action.equalsIgnoreCase("BOOKS"))    // List all songs
			{
				mylibrary.listAllAudioBooks();
			} else if (action.equalsIgnoreCase("PODCASTS"))    // List all songs
			{
				mylibrary.listAllPodcasts();
			} else if (action.equalsIgnoreCase("ARTISTS"))    // List all songs
			{
				mylibrary.listAllArtists();
			} else if (action.equalsIgnoreCase("PLAYLISTS"))    // List all play lists
			{
				mylibrary.listAllPlaylists();
			}

			// Download audio content (song/audiobook/podcast) from the store
			// Specify the starting index and ending index of the content
			try {
				if (action.equalsIgnoreCase("DOWNLOAD")) {
					int FromIndex = 0; // starting index - first content to download
					int ToIndex = 0; // ending index - last content to download

					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt()) {
						FromIndex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

						System.out.print("To Store Content #: ");
						if (scanner.hasNextInt()) {
							ToIndex = scanner.nextInt();
							scanner.nextLine();
						}
					}

					// looping through the starting and ending index - INCLUSIVE
					// downloading them to library if it exists
					// if it is already downloaded, or not found in the store, catches exception and prints error msg
					for (int i = FromIndex; i <= ToIndex; i++) {
						try {
							AudioContent content = store.getContent(i);
							mylibrary.download(content);
							System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");

						} catch(AudioContentDownloadedException e){
							System.out.println((e.getMessage()));
						}

					}
				}
			} catch (AudioContentDownloadedException | AudioContentNotFoundException e) {
				System.out.println(e.getMessage());
			}

			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song
			try {
				if (action.equalsIgnoreCase("PLAYSONG")) {
					System.out.print("Song Number: ");
					if (scanner.hasNextInt()) {
						int songNum = scanner.nextInt();
						scanner.nextLine();

						mylibrary.playSong(songNum);
					}
				}
			} catch (AudioContentNotFoundException e) {
				System.out.println((e.getMessage()));
			}

			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			try {
				if (action.equalsIgnoreCase("BOOKTOC")) {
					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt()) {
						int bookNum = scanner.nextInt();
						scanner.nextLine();

						mylibrary.printAudioBookTOC(bookNum);
					}
				}
			} catch (AudioContentNotFoundException e) {
				System.out.println((e.getMessage()));
			}

			// Similar to playsong above except for audiobook
			// In addition to the book index, read the chapter
			// number from the keyboard - see class Library
			try {
				if (action.equalsIgnoreCase("PLAYBOOK")) {
					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt()) {
						int bookNum = scanner.nextInt();
						scanner.nextLine();

						System.out.print("Chapter: ");
						if (scanner.hasNextInt()) {
							int ch = scanner.nextInt();
							scanner.nextLine();

							mylibrary.playAudioBook(bookNum, ch);
						}
					}
				}
			} catch (AudioContentNotFoundException | ChapterNotFoundException e) {
				System.out.println((e.getMessage()));
			}

			// Specify a playlist title (string)
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist
			// see class Library for the method to call
			try {
				if (action.equalsIgnoreCase("PLAYALLPL")) {
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						String title = scanner.next();
						scanner.nextLine();

						mylibrary.playPlaylist(title);
					}
				}
				// Specify a playlist title (string)
				// Read the index of a song/audiobook/podcast in the playist from the keyboard
				// Play all the audio content
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) {
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						String title = scanner.next();
						scanner.nextLine();

						System.out.print("Content number: ");
						if (scanner.hasNextInt()) {
							int index = scanner.nextInt();
							scanner.nextLine();

							mylibrary.playPlaylist(title, index);
						}
					}
				}
			} catch (AudioContentNotFoundException | PlaylistNotFoundException e){
				System.out.println(e.getMessage());}

			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			try {
				if (action.equalsIgnoreCase("DELSONG")) {
					System.out.print("Library Song #: ");
					if (scanner.hasNextInt()) {
						int songNum = scanner.nextInt();
						scanner.nextLine();

						mylibrary.deleteSong(songNum);
					}
				}
			} catch (AudioContentNotFoundException e){
				System.out.println(e.getMessage()); }

			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			try {
				if (action.equalsIgnoreCase("MAKEPL")) {
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						String title = scanner.next();
						scanner.nextLine();

						mylibrary.makePlaylist(title);
					}
				}
			} catch (PlaylistAlreadyExistsException e){
				System.out.println(e.getMessage());}

			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
			// see class Library for the method to call
			try {
				if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					System.out.print("Playlist Title: ");
					if(scanner.hasNext()) {
						String title = scanner.next();
						scanner.nextLine();

						mylibrary.printPlaylist(title);
					}
				}

				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL"))
				{
					System.out.print("Playlist Title: ");
					if(scanner.hasNext()) {
						String title = scanner.next();
						scanner.nextLine();

						System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
						if(scanner.hasNext()) {
							String type = scanner.next().toUpperCase();
							scanner.nextLine();

							System.out.print("Library Content #: ");
							if (scanner.hasNextInt()) {
								int index = scanner.nextInt();
								scanner.nextLine();

								mylibrary.addContentToPlaylist(type, index, title);
							}
						}
					}
				}

				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
				// see class Library for the method to call

				else if (action.equalsIgnoreCase("DELFROMPL"))
				{
					System.out.print("Playlist Title: ");
					if(scanner.hasNext()) {
						String title = scanner.next();
						scanner.nextLine();

						System.out.print("Playlist Content #: ");
						if(scanner.hasNextInt()) {
							int index = scanner.nextInt();
							scanner.nextLine();

							mylibrary.delContentFromPlaylist(index, title);
						}
					}
				}

			// catches all the exceptions and prints error message
			} catch (AudioContentNotFoundException | PlaylistNotFoundException e){
				System.out.println(e.getMessage()); }

			if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			// Assignment 2 additional functions
			try {
				// SEARCH method

				// searches through the store to find the audio content with inputted title
				// using functions from library class
				// if not found, catches exception and prints message
				if (action.equalsIgnoreCase("SEARCH")) {
					System.out.print("Title: ");
					if (scanner.hasNextLine()) {
						String title = scanner.nextLine();

						store.MapTitleToIndex(title);
						store.searchTitle(title);
					}
				}

				// SEARCHA method

				// searches through the store to find the songs by inputted artist
				// using functions from library class
				// if not found, catches exception and prints message
				else if (action.equalsIgnoreCase("SEARCHA")) {
					System.out.print("Artist: ");
					if (scanner.hasNextLine()) {
						String artist = scanner.nextLine();

						store.MapArtistToIndex(artist);
						store.searchArtist(artist);
					}
				}

				// SEARCHG method

				// searches through the store to find the songs of inputted genre
				// using functions from library class
				// if not found, catches exception and prints message
				else if (action.equalsIgnoreCase("SEARCHG")) {
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					if (scanner.hasNextLine()) {
						String genre = scanner.nextLine();

						store.MapGenreToIndex(genre);
						store.searchGenre(genre);
					}
				}

				//SEARCHP method

				// searches through the store to find the audio contents with partial string
				// using function from library class
				// if not found, catches exception and prints message
				else if (action.equalsIgnoreCase("SEARCHP")) {
					System.out.print("Partial String: ");
					if (scanner.hasNextLine()) {
						String partial = scanner.nextLine();

						store.searchPartial(partial);
					}
				}
			} catch (SearchNotFoundException e){
				System.out.println(e.getMessage());
			}

			// DOWNLOAD method modified at the top of the program

			// DOWNLOADA method
			// downloads all the songs with the inputted artist
			// if not found or already downloaded, catches exception and prints message
			try {
				if (action.equalsIgnoreCase("DOWNLOADA")) {
					System.out.print("Artist Name: ");
					if (scanner.hasNextLine()) {
						String artist = scanner.nextLine();

						store.MapArtistToIndex(artist);
						ArrayList<Integer> indices = store.getArtistIndices(artist);

						// using try ... catch in a for loop going through indices
						for (int i : indices) {
								AudioContent content = store.getContent(i + 1);
							try {
								mylibrary.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");

							} catch (AudioContentDownloadedException e){
								System.out.println(e.getMessage());
							}
						}
					}
				}
			} catch (ArtistNotFoundException e){
				System.out.println(e.getMessage());
			}

			// DOWNLOADG method
			// downloads all the songs with the inputted genre
			// if not found or already downloaded, catches exception and prints message
			try {
				if (action.equalsIgnoreCase("DOWNLOADG")) {
					System.out.print("Genre: ");
					if (scanner.hasNextLine()) {
						String genre = scanner.nextLine();

						store.MapGenreToIndex(genre);
						ArrayList<Integer> indices = store.getGenreIndices(genre);

						// using try ... catch in a for loop going through indices
						for (int i : indices) {
								AudioContent content = store.getContent(i + 1);
							try {
								mylibrary.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");

							} catch (AudioContentDownloadedException e){
								System.out.println(e.getMessage());
							}
						}
					}
				}
			} catch (GenreNotFoundException e){
					System.out.println(e.getMessage());
			}
			System.out.print("\n>");
		}
	}
}
