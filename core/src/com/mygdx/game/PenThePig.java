package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class PenThePig extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Stage stg;
	private ArrayList<ArrayList<Box>> boxes;
	@Override
	public void create () {
		boxes = new ArrayList<>();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		stg = new Stage();
		Gdx.input.setInputProcessor(stg);
		for (int i = 0; i < 5; i++) {
			boxes.add(new ArrayList<Box>());
			for (int j = 0; j < 5; j++) {
				Box b = new Box(15+j*50,400-i*50);
				stg.addActor(b);
				boxes.get(i).add(b);
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				try {
					boxes.get(i).get(j).bottomN = boxes.get(i + 1).get(j);
					boxes.get(i).get(j).upN = boxes.get(i - 1).get(j);
					boxes.get(i).get(j).leftN = boxes.get(i).get(j - 1);
					boxes.get(i).get(j).rightN = boxes.get(i).get(j + 1);
				}catch (IndexOutOfBoundsException e){}
			}
		}
		stg.act();
		//stg.draw();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		stg.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
