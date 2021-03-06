/*
 * Main driver class for the command line version of the audio player.
 * Creates a playlist from the command line arguments and then loops
 * looking for simple commands to control playback.
 */

import edu.rit.se.swen383.audio.AudioSource;
import java.util.*;

public class MP3Player {

    static String playMode = "";
    /*
     * Driver with a simple command language to control the
     * audio playback.
     */
    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        
        /*
         * We need at least one file to play.
         */
        if (args.length < 1) {
            println("Usage: MP3Player mp3file ...");
            return;
        }

        /*
         * Make the play list.
         */
        Iterable<String> mp3names;

        if (args[0].equals("-f")) {
            String filename = args[1];
            mp3names = new FileSource(filename);
        } else if (args[0].equals("-xm")) {        //Considering we don't know how to handle these 
            String xmlFile = args[1];           //types of objects in Java yet, the XML, Database,   
            mp3names = new XMLSource(xmlFile);  //and URL source classes have not been defined, but
        } //would use the same methodology as FileSource and ArraySource
        else if (args[0].equals("-db")) {        //to read the mp3 names
            String database = args[1];
            mp3names = new DatabaseSource(database);
        } else if (args[0].equals("-url")) {
            String url = args[1];
            mp3names = new UrlSource(url);
        } else {
            mp3names = new ArraySource(args);
        }

        PlayList pl = new PlayList(mp3names);
        
        Command cmd;
        Map<String, Command> commandTable = buildMap(pl);
        

        String command = "";
        while (true) {
            //System.exit(0);
            String s = scan.nextLine() + " ";
            s.trim();

            String arguments[] = s.split("\\s+");
            command = arguments[0];

            cmd = commandTable.get(command);
            if(cmd != null){
                cmd.execute(arguments);
            }
            
        }
        /*
         * System.exit(0) rather than return as there is another thread
         * running and a return would only terminate the main thread.
         */

        //System.exit(0) ;
    }

    private static void println(String s) {
        System.out.println(s);
    }


    private static Map<String, Command> buildMap(PlayList playlist) {
            Command c;
            Map<String, Command> result = new HashMap<String, Command>();

            c = new QuitCommand();
            result.put("q", c);
            result.put("quit", c);

            c = new PlayNextCommand(playlist);
            result.put("+", c);
            result.put("next", c);

            c = new PlayPreviousCommand(playlist);
            result.put("-", c);
            result.put("prev", c);

            c = new AgainCommand(playlist);
            result.put("@", c);
            result.put("again", c);
            
            c = new HelpCommand(playlist);
            result.put("h", c);
            result.put("help", c);

            c = new InfoCommand(playlist);
            result.put("i", c);
            result.put("info", c);

            c = new PlayCommand(playlist);
            result.put("p", c);
            result.put("play", c);

            c = new PauseCommand(playlist);
            result.put("P", c);
            result.put("pause", c);

            c = new ResumeCommand(playlist);
            result.put("R", c);
            result.put("resume", c);

            /*
            c = new GetSizeCommand(playlist);
            result.put("s", c);
            result.put("size", c);

            c = new GetTimeCommand(playlist);
            result.put("t", c);
            result.put("time", c);*/
            
            return result;

        }
    }


