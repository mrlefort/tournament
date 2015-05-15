/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tournament.impl.simpleui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tournament.impl.MatchInfo;
import tournament.impl.ParticipantInfo;
import tournament.ui.TournamentUI;

/**
 *
 * @author Tobias
 */
public class TextTournamentUI implements TournamentUI
{
    private static Random rnd = new Random();
    private InputStreamReader input;
    private MatchInfo[] matches;

    public TextTournamentUI()
    {
        input = new InputStreamReader(System.in);
    }
    
    @Override
    public synchronized void tournamentReady(String gameName, ParticipantInfo[] participants, MatchInfo[] matches)
    {
        this.matches = matches;
        System.out.println(gameName + " Tournament!");
        System.out.println("****************");
        System.out.println("* Participants *");
        System.out.println("****************");
        for(ParticipantInfo pi : participants)
        {
            System.out.println(pi.getID() + " - " + pi.getName());
        }
        System.out.println("****************\n");
        System.out.print("Press ENTER to start tournament...");
        waitForKeyPress();
    }

    @Override
    public synchronized void matchStarted(int matchIndex)
    {
        MatchInfo mi = matches[matchIndex];
        System.out.println("Match started: ");
        System.out.println( mi.getParticipantA().getID() + " - " + mi.getParticipantA().getName() + " vs " +
                            mi.getParticipantB().getID() + " - " + mi.getParticipantB().getName() + "\n");
    }

    @Override
    public synchronized void matchFinished(int matchIndex)
    {
        MatchInfo mi = matches[matchIndex];
        System.out.println("Match finished: ");
        System.out.println( mi.getParticipantA().getID() + " - " + mi.getParticipantA().getName() + " vs " +
                            mi.getParticipantB().getID() + " - " + mi.getParticipantB().getName() + "\n");
        //System.out.println("Result: " + mi.getResult());
        
    }

    @Override
    public synchronized void tournamentFinished(List<ParticipantInfo> sortedParticipants)
    {
        for(int i = 0; i < 100; ++i)
        {
            System.out.println("");
        }
        System.out.println("Tournament finished! Press ENTER to see rankings...");
        int place = sortedParticipants.size();
        int index = 0;
        while(place > 2)
        {
            ParticipantInfo p = sortedParticipants.get(index);
            waitForKeyPress();
            System.out.print("Place " + place + ": " + p.getID() + " - " + p.getName() + " : " + p.getMajorPoints() + " / " + p.getMinorPoints());
            --place;
            ++index;
        }
        ParticipantInfo p2 = sortedParticipants.get(index);
        ++index;
        ParticipantInfo p1 = sortedParticipants.get(index);
        ParticipantInfo s1 = p1;
        ParticipantInfo s2 = p2;
        if(rnd.nextBoolean())
        {
            s1 = p2;
            s2 = p1;
        }
        waitForKeyPress();
        System.out.println("\n\n*** Now we have only two left: (in random order) ***\n");
        System.out.println(s1.getID() + " - " + s1.getName());
        System.out.println("\n" + s2.getID() + " - " + s2.getName());
        System.out.print("\n\n*** Press ENTER to see who wins!!! ***");
        waitForKeyPress();
        for(int i = 0; i < 100; ++i)
        {
            System.out.println("");
        }
        System.out.println("First place: " + p1.getID() + " - " + p1.getName() + " : " + p1.getMajorPoints() + " / " + p1.getMinorPoints());
        System.out.println("\nSecond place: " + p2.getID() + " - " + p2.getName() + " : " + p2.getMajorPoints() + " / " + p2.getMinorPoints());
        System.out.print("\n\n\n\nThank you for using the tournament system. Press ENTER to continue...");
        waitForKeyPress();
        for(int i = 0; i < 100; ++i)
        {
            System.out.println("");
        }
    }
    
    private void waitForKeyPress()
    {
        try
        {
            while(input.read() != '\n'){}
        } catch (IOException ex)
        {
            Logger.getLogger(TextTournamentUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
