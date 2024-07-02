package com.project.paradoxplatformer.view.fxcomponents;

import com.project.paradoxplatformer.view.fxcomponents.api.SpriteStatus;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import java.util.function.Consumer;

public class SpriteAnimator {
    
    private EnumMap<SpriteStatus, List<Image>> mapSprite;
    private SpriteStatus prev;
    private int index, frames;

    public SpriteAnimator(SpriterSetter spriterSetter) {
        mapSprite = new EnumMap<SpriteStatus, List<Image>>(Map.of(
            SpriteStatus.IDLE, spriterSetter.getIdleImage(),
            SpriteStatus.RUNNING, spriterSetter.runningImages(),
            SpriteStatus.JUMPING, spriterSetter.jumpingImages(),
            SpriteStatus.FALLING, spriterSetter.fallingImages()
        ));
    }

    public void selectFrame(SpriteStatus current, Consumer<Image> imageAction) {
        
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
