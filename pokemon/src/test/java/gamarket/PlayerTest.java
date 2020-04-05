package gamarket;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;

public class PlayerTest {
    
    @Test
    public void save_Load_test () {    
        Player player = new Player(true, "joe", "password");
        player.setEmail("joe@email.com");
        player.saveData();
        
        Player testPlayer = new Player(false, "joe", "password");
        Player nPlayer = new Player(true, "testguy", "pasword");
        
        assertEquals("joe", testPlayer.getName());

        player = new Player(false, "david", "password");

        int badgeAmt = player.getBadges();
        badgeAmt--;
        player.setBadges(badgeAmt);

        player.saveData();
    }
}