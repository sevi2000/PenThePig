package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Box extends Actor {
    boolean up,left,right,bottom;
    Box upN,leftN,rightN,bottomN;
    Box(float x, float y){
        up = false;
        left = false;
        right = false;
        bottom = false;
        setSize(60,60);
        addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clickedddd");
                if(x > 10 && x < getWidth() - 10 && y > getHeight() - 10){
                    System.out.println("up");
                    up = true;
                    if (upN != null)
                        upN.bottom = true;
                }

                else if(x > 10 && x < getWidth() - 10 && y < 10){
                    System.out.println("bottom");
                    bottom = true;
                    if (bottomN != null)
                        bottomN.up = true;
                }
                else if (x < 10 && y > 10 && y < getHeight() - 10) {
                    System.out.println("left");
                    left = true;
                    if (leftN != null)
                        leftN.right = true;
                }
                else if (x > getWidth() - 10 && y > 10 && y < getHeight() - 10){
                    right = true;
                    if (rightN != null)
                        rightN.left = true;
                }
            }
        });
        setPosition(x,y);

    }
    @Override
    public void draw(Batch batch, float parentAlpha){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap); // remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
        ShapeDrawer sd = new ShapeDrawer(batch, region);
        //sd.setColor(Color.BLACK);
        //sd.filledRectangle(getX(),getY(),getWidth(),getHeight());
        sd.setColor(Color.RED);
        sd.filledRectangle(getX(),getY(),10,10);
        sd.filledRectangle(getX()+50,getY(),10,10);
        sd.filledRectangle(getX(),getY()+50,10,10);
        sd.filledRectangle(getX()+50,getY()+50,10,10);
        if (up){
            sd.setColor(Color.GREEN);
            sd.filledRectangle(getX()+10,getY()+54,40,3);
        }
        if (left){
            sd.setColor(Color.RED);
            sd.filledRectangle(getX()+3,getY()+10,3,40);
        }
        if (bottom){
            sd.setColor(Color.BLUE);
            sd.filledRectangle(getX()+10,getY()+5,40,3);
        }
        if (right){
            sd.setColor(Color.YELLOW);
            sd.filledRectangle(getX()+54,getY()+10,3,40);
        }
        if(penThePig()){
            sd.setColor(Color.PINK);
            sd.circle(getX(Align.center),getY(Align.center),5);
        }
    }
    boolean penThePig() {

         return up && bottom && left && right;
    }

}
