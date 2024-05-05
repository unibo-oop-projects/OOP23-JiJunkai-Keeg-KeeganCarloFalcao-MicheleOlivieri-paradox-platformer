package com.project.paradoxplatformer.view.player;

import com.project.paradoxplatformer.utils.world.Point;
import javafx.scene.image.Image;

public class PlayerView {

    private Point position;
    //public Image playerImg;

    public PlayerView(Point position){
        this.position = position;
        // this.playerImg = ...;
    }

    public void display(){
        System.out.println(position.toString());
    }

    public void updateState(Point newPosition){
        this.position = newPosition;
    }
}
