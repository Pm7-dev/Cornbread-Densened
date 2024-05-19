package me.pm7.cornbreaddensened.Objects;

import org.bukkit.entity.Player;

import java.util.List;

public class Student {
    public Player player;
    public String questionType;
    public List<Integer> data;
    public int attempts;

    public Student(Player p, String questionType, List<Integer> data) {
        this.player = p;
        this.questionType = questionType;
        this.data = data;
        this.attempts = 5;
    }
}
