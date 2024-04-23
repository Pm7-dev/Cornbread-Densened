package me.pm7.cornbreaddensened.Objects;

import org.bukkit.entity.Player;

import java.util.List;

public class Student {
    public Player player;
    public String questionType;
    public List<Integer> data;

    public Student(Player p, String questionType, List<Integer> data) {
        this.player = p;
        this.questionType = questionType;
        this.data = data;
    }
}
