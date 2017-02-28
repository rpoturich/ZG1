/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rachelpoturich
 */
public class HelpCommand implements Command {

    private PlayList pl;

    public HelpCommand(PlayList pl) {
        this.pl = pl;
    }

    public void execute() {
        System.out.println("+ = Play the file after the current one.");
        System.out.println("- = Play the file before the current one.");
        System.out.println("@ = Replay the current file.");
        System.out.println("h or H or ? = Print this help screen.");
        System.out.println("i [n] = Print information on file #'n'");
        System.out.println("        (or the current file if 'n' is omitted).");
        System.out.println("p [n] = Terminate any playback and start playing");
        System.out.println("        AudioSource #'n' (default 0).");
        System.out.println("P = Pause playback if any.");
        System.out.println("R = Resume playback if any.");
        System.out.println("t = Print the playback position in seconds.");
        System.out.println("s = Print number of playlist entries.");
        System.out.println("q = Quit the player.");
    }

}
