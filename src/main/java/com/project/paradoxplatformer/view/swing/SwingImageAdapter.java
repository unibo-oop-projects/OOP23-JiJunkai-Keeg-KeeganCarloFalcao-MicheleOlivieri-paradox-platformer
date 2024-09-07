package com.project.paradoxplatformer.view.swing;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteAnimator;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteStatus;
import com.project.paradoxplatformer.view.graphics.sprites.Spriteable;

public class SwingImageAdapter extends AbstractSwingAdapter implements Spriteable<SpriteStatus>{

    private SpriteAnimator<BufferedImage> spriteAnimator;
    private JButton imgComponent;

    protected SwingImageAdapter(Dimension dimension, Coord2D coord, String imagePath, final int minFrames) {
        super(new JButton(), dimension, coord);
        if(this.uiComponent instanceof JButton jLabel) {
            this.imgComponent = jLabel;
            try {
                jLabel.setIcon(new ImageIcon(ImageLoader.AWTImage(imagePath)));
                this.spriteAnimator = new SpriteAnimator<BufferedImage>(
                    new SwingSpriteSetter(
                        imagePath, 
                        new Dimension(
                            jLabel.getIcon().getIconWidth(),
                            jLabel.getIcon().getIconWidth()
                        ),
                        dimension         
                    ),
                    minFrames
                ); 
            } catch (IOException | InvalidResourceException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
             
        }
    }

    @Override
    public void flip() {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'flip'");
        //Unflippable
    }

    @Override
    public void animate(SpriteStatus status) {
        this.spriteAnimator.selectFrame(status, i -> imgComponent.setIcon(new ImageIcon(i)));
    }

}
