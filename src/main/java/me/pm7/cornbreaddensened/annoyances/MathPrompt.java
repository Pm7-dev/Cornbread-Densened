package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.Objects.Student;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.sum;

public class MathPrompt implements Listener {
    Random random = new Random();
    static List<Student> students = new ArrayList<>();

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(isStudent(e.getPlayer())) { e.setCancelled(true); return; }
        if ((int) Math.floor(random.nextFloat() * (22500)) != 1) { return; } // 22500
        if(random.nextBoolean()) {
            // Multiplying Binomials

            int t1 = (int)Math.floor((random.nextFloat() * 9) + 1);
            int t2 = (int)Math.floor((random.nextFloat() * 9) + 1);
            int t3 = (int)Math.floor((random.nextFloat() * 9) + 1);
            int t4 = (int)Math.floor((random.nextFloat() * 9) + 1);
            students.add(new Student(e.getPlayer(), "binomial", Arrays.asList(t1, t2, t3, t4)));

            e.getPlayer().sendMessage(ChatColor.RED + "Expand the binomials: ("+t1+"x + "+t2+")("+t3+"x + "+t4+") - Send answer in chat");
            e.getPlayer().sendMessage(ChatColor.RED + "Use ^ to show exponents and put spaces between plus signs when formatting answer");
            e.getPlayer().sendMessage(ChatColor.RED + "Example problem: (2x + 3)(3x + 4) Solution: 6x^2 + 17x + 12");
            TextComponent component = new TextComponent(ChatColor.RED + "No clue how to do this? Either cry about it, or " + ChatColor.BOLD + "Click here");
            component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/watch?v=L9lxliZMfgU&t=31s"));
            e.getPlayer().spigot().sendMessage(component);

        } else {
            // Systems of Equations

            // this one is set up a little different because it has to be
            // (t1)x + t3 = (t2)x + t4
            int t1 = (int)Math.floor((random.nextFloat() * 9) + 2); // first x variable
            int t2 = (int)Math.floor(random.nextFloat() * (t1)); // second x variable
            if(t2==0) {t2++;} //hehehe
            int t3 = (t1-t2) * (int) Math.floor((random.nextFloat() * 13) + 1); // first number term
            int t4 = (t1-t2) * (int) Math.floor((random.nextFloat() * 13) + 1); // second number term
            students.add(new Student(e.getPlayer(), "equationSystem", Arrays.asList(t1, t2, t3, t4)));

            e.getPlayer().sendMessage(ChatColor.RED + "Solve the system of equations for y - Send answer in chat");
            e.getPlayer().sendMessage(ChatColor.RED + "y = " + t1 + "x + " + t3);
            e.getPlayer().sendMessage(ChatColor.RED + "y = " + t2 + "x + " + t4);
            e.getPlayer().sendMessage(ChatColor.RED + "Your answer should be one whole number. it should not be preceded by 'y =' or anything");
            e.getPlayer().sendMessage(ChatColor.RED + "Example problem: y = 8x + 18 && y = 5x + 12 Solution: 10");
            TextComponent component = new TextComponent(ChatColor.RED + "No clue how to do this? Either cry about it, or " + ChatColor.BOLD + "Click here");
            component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/watch?v=F5oLU9qjYIo"));
            e.getPlayer().spigot().sendMessage(component);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(!isStudent(e.getPlayer())) { return; }
        Student student = getStudent(e.getPlayer());
        String input = e.getMessage().toLowerCase();

        int t1 = student.data.get(0);
        int t2 = student.data.get(1);
        int t3 = student.data.get(2);
        int t4 = student.data.get(3);

        if(student.questionType.equals("binomial")) {
            int dumbThing = (t1*t4)+(t2*t3);
            if(input.equals(t1*t3+"x^2 + "+dumbThing+"x + "+(t2*t4)) || input.equals(t1*t3+"x^2+"+dumbThing+"x+"+(t2*t4)) ) {
                students.remove(student);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Correct");
            } else {
                student.attempts -= 1;
                e.getPlayer().sendMessage(ChatColor.RED + "Incorrect. " + student.attempts + " attempts remaining.");
                if(student.attempts <= 0) {
                    student.player.setHealth(0);
                    students.remove(student);
                }
            }
        }
        else if(student.questionType.equals("equationSystem")) {
            // I'm gonna work this out like a smelly human
            int correct = (t3-t4)/(t2-t1);
            correct = (correct*t1) + t3;
            int answer = parseInt(input);

            if(answer == correct) {
                students.remove(getStudent(e.getPlayer()));
                e.getPlayer().sendMessage(ChatColor.GREEN + "Correct");
            } else {
                student.attempts -= 1;
                e.getPlayer().sendMessage(ChatColor.RED + "Incorrect. " + student.attempts + " attempts remaining.");
                if(student.attempts <= 0) {
                    student.player.setHealth(0);
                    students.remove(student);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if(p.getGameMode() != GameMode.SURVIVAL) { return; }
        if(!isStudent(p)) { return; }
        students.remove(getStudent(p));
    }

    // TODO: PLAYER LEAVING KIL THEM ALL PLEAES UWU
    // I did it :D (maybe)
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if(isStudent(e.getPlayer())) {
            e.getPlayer().setHealth(0.0D);
            students.remove(getStudent(e.getPlayer()));
        }
    }

    public static boolean isStudent(Player p) {
        for(Student student : students) {
            if(student.player == p) {return true;}
        }
        return false;
    }

    public Student getStudent(Player p) {
        for(Student student : students) {
            if(student.player == p) { return student; }
        }
        return null;
    }
}