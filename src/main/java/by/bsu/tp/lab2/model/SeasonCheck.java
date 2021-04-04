package by.bsu.tp.lab2.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class SeasonCheck {

    private final boolean summer;
    private final boolean fall;
    private final boolean winter;
    private final boolean spring;

    public SeasonCheck(String[] seasons) {
        if(seasons == null) {
            summer = fall = winter = spring = false;
        } else {
            List<String> seasonList = Arrays.asList(seasons);
            summer = seasonList.contains("summer");
            fall = seasonList.contains("fall");
            winter = seasonList.contains("winter");
            spring = seasonList.contains("spring");
        }
    }
}
