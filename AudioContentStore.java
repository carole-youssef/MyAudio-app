// Name: Carole Youssef
// Student ID: 501156366
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents;
		private Map<String,Integer> TitleMap;
		private Map<String, ArrayList<Integer>> ArtistMap;
		private Map<String,ArrayList<Integer>> GenreMap;
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>();
			TitleMap = new HashMap<String,Integer>();
			ArtistMap = new HashMap<String,ArrayList<Integer>>();
			GenreMap = new HashMap<String,ArrayList<Integer>>();

			// reads file "store.txt"
			// if it is type song
			// collects all the information of a song and creates a song object - add to contents arraylist

			// if it is type audiobook
			// collects all the information of an audiobook and creates an audiobook object - add to contents arraylist
			// goes through chapter titles and chapter content with a for loop

			//catches IO exception and prints msg
			try {
				File inputFile = new File("store.txt");
				Scanner scanner = new Scanner(inputFile);

				while (scanner.hasNextLine()) {
					String type = scanner.nextLine();
					if(type.equals("SONG"))
					{
						String id = scanner.nextLine();
						String title = scanner.nextLine();
						int year = scanner.nextInt();
						scanner.nextLine(); // consume nl character - necessary for all integers

						int length = scanner.nextInt();
						scanner.nextLine();

						String artist = scanner.nextLine();
						String composer = scanner.nextLine();
						String genre = scanner.nextLine();

						int numLines = scanner.nextInt();
						scanner.nextLine();

						String lyrics = ""; // go through the lyrics of the song
						for (int i = 0; i < numLines; i++) {
							lyrics += scanner.nextLine();
							if (i < numLines - 1) {
								lyrics += "\n";
							}
						}
						AudioContent song = new Song(title, year, id,"SONG", "", length,artist,
								composer,Song.Genre.valueOf(genre),lyrics);
						contents.add(song);
					}
					if(type.equals("AUDIOBOOK"))
					{
						String id = scanner.nextLine();
						String title = scanner.nextLine();
						int year = scanner.nextInt();
						scanner.nextLine();

						int length = scanner.nextInt();
						scanner.nextLine();

						String author = scanner.nextLine();
						String narrator = scanner.nextLine();

						int numChapters = scanner.nextInt();
						scanner.nextLine();

						ArrayList<String> ChapterTitles = new ArrayList<>();
						ArrayList<String> Chapters = new ArrayList<>();

						for (int i =0; i < numChapters; i++) { // go through chapter titles of audiobook
							ChapterTitles.add(scanner.nextLine());
						}

						// go through the chapter content corresponding to each chapter title
						for (int i = 0; i < numChapters; i++) {
							int numChapterLines = scanner.nextInt();
							scanner.nextLine();
							String text = "";

							for (int j = 0; j < numChapterLines; j++) {
								text += scanner.nextLine() + "\n";
							}
							Chapters.add(text);
						}
						AudioContent audiobook = new AudioBook(title, year, id,"AUDIOBOOK", "", length,
								author, narrator, ChapterTitles, Chapters);
						contents.add(audiobook);
					}
				}
				scanner.close();

			} catch (
					IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}

		// loops through the contents array list until
		// the content's title matches the input title
		// and maps the title to the index value it is found
		public void MapTitleToIndex(String title)
		{
			for (int i = 0; i < contents.size(); i++)
			{
				AudioContent content = contents.get(i);
				if(content.getTitle().equals(title))
				{
					TitleMap.put(content.getTitle(), i);
				}
			}
		}

		// if the map contains the title string key
		// get the index and content info and
		// print the index and the content info correspondent to the title
		// if title is not found, throws exception
		public void searchTitle(String title) throws SearchNotFoundException
		{
			if (TitleMap.containsKey(title))
			{
				int index = TitleMap.get(title);
				AudioContent content = contents.get(index);

				System.out.print(index + 1 + ". ");
				content.printInfo();
			}
			else {throw new SearchNotFoundException("No matches for " + title); }
		}

		// initializes a new indices array list and loops through contents array
		// checks if the content is a song or audiobook
		// then checks if the artist is equivalent to artist/ author in current content
		// maps the artist to array list of indices with content with that artist
		public void MapArtistToIndex(String artist)
		{
			ArrayList<Integer> Indices = new ArrayList<Integer>();
			for (int i = 0; i < contents.size(); i++)
			{
				AudioContent content = contents.get(i);
				if(content.getType().equals("SONG")) {
					Song song = (Song) content;
					if(song.getArtist().equals(artist)) {
						Indices.add(i);
					}
				}

				else if(content.getType().equals("AUDIOBOOK")) {
					AudioBook audioBook = (AudioBook) content;
					if(audioBook.getAuthor().equals(artist)) {
						Indices.add(i);
					}
				}
			}
			if(!Indices.isEmpty()) { ArtistMap.put(artist, Indices); }
		}

		// if map contains the artist key
		// get the correspondent indices and content info for that artist
		// loop through indices array list and print this
		// if artist is not found, throws exception
		public void searchArtist(String artist) throws SearchNotFoundException
		{
			if (ArtistMap.containsKey(artist))
			{
				ArrayList <Integer> indices = ArtistMap.get(artist);
				for (Integer i: indices)
				{
					AudioContent content = contents.get(i);
					System.out.print(i + 1 + ". ");
					content.printInfo();
					System.out.println();
				}
			}
			else {throw new SearchNotFoundException("No matches for " + artist);}
		}

		// initializes a new indices array list and loops through contents array
		// checks if the content is a song
		// if it is, checks if the genre is equivalent to genre in current content
		// maps the genre to array list of indices with content of that genre
		public void MapGenreToIndex(String genre)
		{
			ArrayList<Integer> Indices = new ArrayList<Integer>();
			for (int i = 0; i < contents.size(); i++)
			{
				AudioContent content = contents.get(i);
				if(content.getType().equals("SONG"))
				{
					Song song = (Song) content;
					if(song.getGenre().toString().equals(genre)) { // make them the same type to compare
						Indices.add(i);
					}
				}
			}
			if(!Indices.isEmpty()) { GenreMap.put(genre, Indices); }
		}

		// if map contains the genre key
		// get the correspondent indices and content info for that genre
		// loop through indices array list and print this
		// if genre is not found, throws exception
		public void searchGenre(String genre) throws SearchNotFoundException
		{
			if (GenreMap.containsKey(genre))
			{
				ArrayList <Integer> indices = GenreMap.get(genre);
				for (Integer i: indices)
				{
					AudioContent content = contents.get(i);
					System.out.print(i + 1 + ". ");
					content.printInfo();
					System.out.println();
				}
			} else { throw new SearchNotFoundException("No matches for " + genre); }
		}

		// searches for a partial string in the store
		// goes through contents array list and checks if current audio content is a song or audiobook
		// then checks each variable of type string if it has the partial string in it
		// if it does, prints the audio content
		// if nothing is found, throws an exception
		public void searchPartial(String partial) throws SearchNotFoundException
		{
			boolean found = false;

			for (int i = 0; i < contents.size(); i++) {
				AudioContent content = contents.get(i); // get the content

				if (content.getType().equals("SONG")) { // if it's a song, compare with all possible strings
					Song song = (Song) content;

					if (song.getTitle().contains(partial) || song.getArtist().contains(partial) ||
						song.getAudioFile().contains(partial) || song.getComposer().contains(partial)
						|| song.getLyrics().contains(partial)) {

						// print the info corresponding to the partial string
						System.out.print(i + 1 + ". ");
						content.printInfo();
						System.out.println();
						found = true;
					}
				} else if (content.getType().equals("AUDIOBOOK")) { // if it's an audiobook, compare with all possible strings
					AudioBook audiobook = (AudioBook) content;

					if (audiobook.getTitle().contains(partial) || audiobook.getAuthor().contains(partial)
						|| audiobook.getNarrator().contains(partial) || audiobook.getAudioFile().contains(partial)) {

						// print the audio content corresponding to the partial string
						System.out.print(i + 1 + ". ");
						content.printInfo();
						System.out.println();
						found = true;

					// if it is still not found, compare with chapter titles and chapter content
					} else {
						ArrayList<String> chapterTitles = audiobook.getChapterTitles();
						ArrayList<String> chapters = audiobook.getChapters();

						// have to go through the list of chapter titles to check if partial string is there
						for (String chapterTitle : chapterTitles) {
							if (chapterTitle.contains(partial)) {

								// print the audio content corresponding to the partial string
								System.out.print(i + 1 + ". ");
								content.printInfo();
								System.out.println();
								found = true;
							}
						}
						// have to go through the list of chapters to check if partial string is there
						for (String chapter : chapters) {
							if (chapter.contains(partial)) {

								// print the audio content corresponding to the partial string
								System.out.print(i + 1 + ". ");
								content.printInfo();
								System.out.println();
								found = true;
							}
						}
					}
				}
			}

			// if still not found, throws exception for no matches
			if (!found) { throw new SearchNotFoundException("No matches for " + partial); }
		}

		// get the indices list of the artist by using the ArtistMap
		// if ArtistMap doesn't have any matches, throws exception
		public ArrayList<Integer> getArtistIndices(String artist) throws ArtistNotFoundException {
			if (ArtistMap.containsKey(artist)) {
				return ArtistMap.get(artist);
			}
			else{
				throw new ArtistNotFoundException("Artist " + artist + " not found in store");
			}
		}

		// get the indices list of the genre by using the GenreMap
		// if GenreMap doesn't have any matches, throws exception
		public ArrayList<Integer>getGenreIndices(String genre) throws GenreNotFoundException{
			if (GenreMap.containsKey(genre)) {
				return GenreMap.get(genre);
			}
			else{
				throw new GenreNotFoundException("Genre " + genre + " not found in store");
			}
		}
}
	/* custom exceptions for the methods created are shown below - specific to AudioContentStore */
	// exception for search not found
	class SearchNotFoundException extends RuntimeException {
		public SearchNotFoundException(String message){
			super(message);
		}
	}

	// exception for artist not found
	class ArtistNotFoundException extends RuntimeException {
		public ArtistNotFoundException(String message){
			super(message);
		}
	}

	// exception for genre not found
	class GenreNotFoundException extends RuntimeException {
		public GenreNotFoundException(String message){
			super(message);
		}
	}

