package com.example.surfaceview;

import com.example.surfaceview.R;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class SurfaceViewActivity extends Activity implements SurfaceHolder.Callback {

	private SurfaceHolder sh;
	private SurfaceView sv;
	private Canvas canvas;
	private Paint paint;
	private boolean changeFlg = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_surfaceview);
		sv = (SurfaceView) findViewById(R.id.surfaceView1);
		sh = sv.getHolder();
		sh.addCallback(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_surfaceview, menu);
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		drawTest();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	public void drawTest() {
		canvas = sh.lockCanvas();

		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setTextSize(10);
		paint.setTextAlign(Align.CENTER);
		canvas.drawColor(Color.BLACK);
		canvas.drawText("Hello, SurfaceView!", sv.getWidth() / 2, sv.getHeight() / 2, paint);

		Resources res = getApplicationContext().getResources();
		Bitmap icon = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
		canvas.drawBitmap(icon, sv.getWidth() / 2 - icon.getWidth() / 2, 0, paint);

		sh.unlockCanvasAndPost(canvas);
	}

	public void backMain(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void changeImage(View view) {
		canvas = sh.lockCanvas();

		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setTextSize(10);
		paint.setTextAlign(Align.CENTER);
		canvas.drawColor(Color.BLACK);
		canvas.drawText("Hello, SurfaceView!", sv.getWidth() / 2, sv.getHeight() / 2, paint);

		Resources res = getApplicationContext().getResources();
		Bitmap icon;
		if (changeFlg) {
			icon = BitmapFactory.decodeResource(res, R.drawable.android);
			changeFlg = false;
		} else {
			icon = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
			changeFlg = true;
		}
		canvas.drawBitmap(icon, sv.getWidth() / 2 - icon.getWidth() / 2, 0, paint);

		sh.unlockCanvasAndPost(canvas);
	}

}
