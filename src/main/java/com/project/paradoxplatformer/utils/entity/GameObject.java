package com.project.paradoxplatformer.utils.entity;

import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.Point;

public  interface GameObject {

    Point getPosition();

    Dimension getDimension();

}