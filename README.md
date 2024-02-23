# MyAudio-app
In this program, I write parts of a simple audio application program (i.e. an app) called MyAudio. The MyAudio app stores and manages audio content of various types (e.g. Songs, Audio Books, Podcasts) in a library. Audio content can be downloaded from a simulated online store. The audio content in the library can be played (i.e. in this app, playing a song means printing its lyrics). Playlists can be created from library content. A playlist can store songs or even a mixture of different types of audio content. 

1. Library: this class contains the bulk of the logic for the app. It maintains an array list of songs, an array list of audiobooks, an array list of podcasts and an array list of playlists. These lists are initially empty. Some methods have been written for you. Fill in the code for the other methods based on the comments.
  
2. MyAudioUI: This class has the main() method and the user interface (UI). Some UI skeleton code has been provided for you with some comments. In a while loop, a scanner reads a line of input from the user. The input lines contain words (Strings) which represent actions (e.g. songs, store, download etc). Some actions require parameters to be input by the user. The parameters may be strings or an integer. The code should prompt the user by printing out what parameter should be entered. See the video and look at the example code provided for you for action PLAYSONG in MyAudio.java.

3. AudioContent: class AudioContent is a general superclass containing several instance variables (see the skeleton code) that model audio content. This class can be extended to model more specific types of audio content (e.g. song, podcast, audiobook). It has instance variables: title (String), year (int), id (String), type (String), audioFile (String), length (int).

4. AudioContentStore: class AudioContentStore simulates an online store. The MyAudio app can download content (e.g. a song, an audiobook) from this store to your library. When the user types the action STORE then a method is called to list the store contents. The contents are numbered (1, 2, 3 etc). Each content has a type (Song, AudioBook, Podcast). The user can type the DOWNLOAD action and then specify the number of the content they want stored in their library.

5. Song: class Song is a subclass of Audio Content (i.e. a song is a type of audio content). In addition to the AudioContent information, a song has fields to store the artist name, the composer name, the genre and the lyrics.

6. AudioBook: This class is also a subclass of AudioContent that contains extra information. An AudioBook contains the book’s author and AudioBook’s narrator. It also contains two array lists of Strings: one that contains the chapter titles and one that contains the chapter contents. The class has a method to select a specific chapter to play.

7. Playlist: class Playlist stores a list of audio content (e.g. songs or audiobooks or podcasts or even a mixture of these). A Playlist is given a title (e.g. “Workout” “Dinner Music” “Dance Mix”). There are a series of user actions to make a playlist, add content to a playlist etc. All the content can be played (i.e. playAll()) or a specific audio content can be played (e.g. song 5)

8. Podcast: Design a class which is also a subclass of AudioContent that contains extra information. A podcast is essentially a talk radio series on demand. Podcasts tend to be focused on a theme or topic. Podcasts should have a host (String) as well as a list of Seasons. Each Season should consist of a list of episodes (strings representing the "audiofiles") a list of episode titles (strings) and a list of episode lengths (in minutes). See the video of an example of podcast content.  Provide the ability to play a specific episode of a season. You may want to create a class Season to hold the episode information for a season. Add code to MyAudio.java for actions PODCASTS, PLAYPOD.

UPDATED VERSION:

In myAudioUI: 

- SEARCH: Add an action called SEARCH to myAudioUI as well as necessary code to class AudioContentStore that searches the store for an audio content with the specified title. That is, the user types SEARCH then is prompted to enter a title string. If the audio content with this title is found in the store then print the index of this content and the info for this content. A separate part of this new functionality is to use a Map in class AudioContentStore that maps a title (string) to an integer value. The integer value represents an index into the contents array list.

- SEARCHA: Add an action called SEARCHA to myAudioUI as well as necessary code to class AudioContentStore that searches the store for an audio content with the specified artist name. That is, the user types SEARCHA then is prompted to enter an artist string. If the audio content with this artist name is found in the store then print the indices and info of all audio content with this artist (use author string for audio books). A separate part of this new functionality is to use a Map in class AudioContentStore that maps an artist string to an array list of integer. The integers in the array list represent indices into the contents array list.

- SEARCHG: Add an action called SEARCHG to myAudioUI as well as necessary code to class AudioContentStore that searches the store for a song with the specified genre (“POP” “ROCK” etc). That is, the user types SEARCHG then is prompted to enter a genre string. If the song with this genre is found in the store then print the indices and info of all songs with this genre. A separate part of this new functionality is to use a Map in class AudioContentStore that maps a genre string to an array list of integer. The integers in the array list represent indices into the contents array list.

- SEARCHP. This action supports the ability to search (and print) all audio content that partially matches a target string. The target string could appear anywhere in the audio content (in the title, artist, lyrics, chapter etc etc).

- DOWNLOAD: Modify the download action so that it takes two store indices instead of one store index as parameters – i.e. a fromIndex and a toIndex. That is, you should now be able to download a range of songs/books etc. from the store (e.g. from song 2 to song 6, inclusive). If some of the songs are already in the library, then and error message for each of these songs should be printed.

- DOWNLOADA: Create a new download action that takes an artist string as parameter and downloads all audio content with this artist name (use author for audio books) from the store.

- DOWNLOADG: Create a new download action that takes a genre string as parameter and downloads all songs in this genre from the store. 













