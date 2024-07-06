package com.project.paradoxplatformer.view.graphics.sprites;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import java.util.function.Consumer;

public class SpriteAnimator<T> {
    
    private EnumMap<SpriteStatus, List<T>> mapSprite;
    private SpriteStatus prev;
    private int index, frames;

    public SpriteAnimator(Spriter<T> spriterSetter) {
        mapSprite = new EnumMap<SpriteStatus, List<T>>(Map.of(
            SpriteStatus.IDLE, spriterSetter.getIdleImage(),
            SpriteStatus.RUNNING, spriterSetter.runningImages()
        ));
    }

    public void selectFrame(SpriteStatus current, Consumer<T> imageAction) {
        
        //FIXX, GRAMES PROB ON SPRITE DTO
        if(this.prev == current) {
            frames += 1;
            if (frames % 5 == 0) {
                this.index += 1;
            }
            
        } else {
            this.index = 0;
            
        }
        var inf = mapSprite.get(current);
        
        imageAction.accept(mapSprite.get(current).get(index % inf.size()));
        this.prev = current;    

    }
}
