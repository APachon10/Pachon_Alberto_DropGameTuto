package com.mygdx.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Drop extends ApplicationAdapter {
	//Texturas
	private Texture drop;
	private Texture bucket;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Rectangle bucket2;

	@Override
	public void create () {
		drop = new Texture(Gdx.files.internal("drop.png"));
		bucket = new Texture(Gdx.files.internal("bucket.png"));

		camera= new OrthographicCamera();
		camera.setToOrtho(false,800,400);

		batch = new SpriteBatch();

		// Situamos el Cubo en una posicion
		bucket2 = new Rectangle();
		bucket2.x = 800 / 2 - 64 / 2;
		bucket2.y = 20;
		bucket2.width = 64;
		bucket2.height = 64;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.begin();
		batch.draw(bucket, 0, 0);
		batch.end();

		//Hacer que el cubo se mueva con el raton
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket2.x = touchPos.x - 64 / 2;
		}
		//Hacer que el cubo se mueva con las techas
		/*if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket2.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket2.x += 200 * Gdx.graphics.getDeltaTime();*/

		//Con esto vigilamos que el cubo se quede en el limite de la pantalla
		if(bucket2.x < 0){
			bucket2.x = 0;
		}else if(bucket2.x > 800 - 64){
			bucket2.x = 800 - 64;
		}


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bucket.dispose();
	}
}
