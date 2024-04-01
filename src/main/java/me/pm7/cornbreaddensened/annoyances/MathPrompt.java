package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class MathPrompt implements Listener {
    Random random = new Random();
    List<String> students = new ArrayList<>();
    List<String> problems = new ArrayList<>();

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent e) {
        if(!students.contains(e.getPlayer().getName())) {
            if ((int) Math.floor(random.nextFloat() * (25000)) == 1) {
                students.add(e.getPlayer().getName());
                int t1 = (int)Math.floor((Math.random() * (9)) + 1);
                int t2 = (int)Math.floor((Math.random() * (9)) + 1);
                int t3 = (int)Math.floor((Math.random() * (9)) + 1);
                int t4 = (int)Math.floor((Math.random() * (9)) + 1);
                problems.add(t1+" "+t2+" "+t3+" "+t4);
                e.getPlayer().sendMessage(ChatColor.RED + "Multiply: ("+t1+"x + "+t2+")("+t3+"x + "+t4+") - Don't factor result");
                e.getPlayer().sendMessage(ChatColor.RED + "Use ^ to show exponents and put spaces between plus signs");
                e.getPlayer().sendMessage(ChatColor.RED + "Example: (2x + 3)(3x + 4) Answer: 6x^2 + 17x + 12");
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPLayerChat(AsyncPlayerChatEvent e) {
        if(students.contains(e.getPlayer().getName())) {
            String input = e.getMessage();

            String answer = problems.get(students.indexOf(e.getPlayer().getName()));
            String[] answers = answer.split(" ");
            int t1 = parseInt(answers[0]);
            int t2 = parseInt(answers[1]);
            int t3 = parseInt(answers[2]);
            int t4 = parseInt(answers[3]);

            int dumbThing = (t1*t4)+(t2*t3);
            if(input.equals(t1*t3+"x^2 + "+dumbThing+"x + "+(t2*t4)) || input.equals(t1*t3+"x^2+"+dumbThing+"x+"+(t2*t4)) ) {
                problems.remove(answer);
                students.remove(e.getPlayer().getName());
                e.getPlayer().sendMessage(ChatColor.GREEN + "Correct");
            } else {
                e.getPlayer().sendMessage(ChatColor.RED + "Incorrect");
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if(students.contains(p.getName())) {
            problems.remove(students.indexOf(p.getName()));
            students.remove(p.getName());
        }
    }
}
