/*
 * Main driver class for the command line version of the audio player.
 * Creates a playlist from the command line arguments and then loops
 * looking for simple commands to control playback.
 */

import edu.rit.se.swen383.audio.AudioSource ;
import java.util.*;

public class MP3Player {
    
    /*
     * Driver with a simple command language to control the
     * audio playback.
     */
    public static void main(String args[]) {
        
            Scanner scan = new Scanner (System.in);

        /*
         * We need at least one file to play.
         */
        if( args.length < 1 ) {
            println("Usage: MP3Player mp3file ...") ;
            return ;
        }

        /*
         * Make the play list.
        */
        
        Iterable<String> mp3names;
        
        if (args[0].equals("-f")) {
            String filename = args[1];
            mp3names = new FileSource(filename);
        } 
        else if(args[0].equals("-xm")) {        //Considering we don't know how to handle these 
            String xmlFile = args[1];           //types of objects in Java yet, the XML, Database,   
            mp3names = new XMLSource(xmlFile);  //and URL source classes have not been defined, but
        }                                       //would use the same methodology as FileSource and ArraySource
        else if(args[0].equals("-db")) {        //to read the mp3 names
            String database = args[1];
            mp3names = new DatabaseSource(database);
        }
        else if(args[0].equals("-url")) {
            String url = args[1];
            mp3names = new UrlSource(url);
        }
         else {
            mp3names = new ArraySource(args);
        }
        
        PlayList pl = new PlayList(mp3names);
        
        
        char command = ' ' ;
        while( true ) {
            System.exit(0);
            String s = scan.nextLine() + " " ;
            s.trim();
            
            String arguments[] = s.split("\\s+");
            String commands = arguments[0];
            
            if( commands.equals("+") || commands.equals("next") ) {
                int nextIndex = pl.getSourceIndex() + 1 ;
                /*
                 * Don't move beyond the last play list element.
                 */
                if( nextIndex < pl.size() ) {
                    pl.play(nextIndex) ;
                }
            }
            else if( commands.equals("-") || commands.equals("prevoius")) {
                int prevIndex = pl.getSourceIndex() - 1 ;
                /*
                 * Don't move before the first play list element.
                 */
                if( prevIndex >= 0 ) {
                    pl.play(prevIndex) ;
                }
            }
            else if( commands.equals("@")  ) {
                pl.play(pl.getSourceIndex()) ;
            }
            else if( commands.equals("h")  || commands.equals("H") || commands.equals("?")) {
                println("+ = Play the file after the current one.");
                println("- = Play the file before the current one.");
                println("@ = Replay the current file.") ;
                println("h or H or ? = Print this help screen.") ;
                println("i [n] = Print information on file #'n'") ;
                println("        (or the current file if 'n' is omitted).") ;
                println("p [n] = Terminate any playback and start playing") ;
                println("        AudioSource #'n' (default 0).") ;
                println("P = Pause playback if any.") ;
                println("R = Resume playback if any.") ;
                println("t = Print the playback position in seconds.") ;
                println("s = Print number of playlist entries.") ;
                println("q = Quit the player.") ;
            }
            else if( commands.equals("i") ) {
                AudioSource as = null ;
                int i = -1 ;

                try {
                    String iv = s.substring(1).trim() ;
                    i = Integer.parseInt(iv) ;
                } catch(Exception e) {
                    i = -1 ; // no integer argument.
                } ;
                if( i < 0 ) {
                    i = pl.getSourceIndex() ;
                }
                as = pl.getSource(i) ;

                if( i == (-1) ) {
                    println("Player is idle") ;
                } else if( as != null ) {
                    int duration = as.getDuration() ;
                    int secs = duration % 60 ;
                    int mins = duration / 60 ;

                    println("Index:    " + i) ;
                    println("File:     " + as.getFileName()) ;
                    println("Title:    " + as.getTitle()) ;
                    println("Artist:   " + as.getArtist()) ;
                    println("Album:    " + as.getAlbum()) ;
                    println("Genre:    " + as.getGenre()) ;
                    System.out.printf ("Duration: %d:%02d\n", mins, secs) ;
                }
            }
            else if( commands.equals("p") ) {
                int i = 0 ;
                try {
                    String iv = s.substring(1).trim() ;
                    i = Integer.parseInt(iv) ;
                } catch(Exception e) {i = 0 ; }
                pl.play(i) ;
            }
            else if( commands.equals("P") ) {
                pl.pause() ;
            }
            else if( commands.equals("R") ) {
                pl.resume() ;
            }
            else if( commands.equals("s") ) {
                println("Playlist size: " + pl.size()) ;
            }
            else if( commands.equals("t") ) {
                int position = pl.getPosition() / 1000 ; // remove milliseconds
                int secs = position % 60 ;
                int mins = position / 60 ;
                System.out.printf("Source position: %d:%02d\n", mins, secs) ;
            }
        }
        /*
         * System.exit(0) rather than return as there is another thread
         * running and a return would only terminate the main thread.
         */
        
        System.exit(0) ;
    }

    private static void println(String s) {
        System.out.println(s) ;
    }
}
