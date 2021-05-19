package com.example.bmpres1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageSharp;
    private ImageView imageCircles;
    private Bitmap bitmapSharp;
    private Bitmap bitmapCircles;

    private static final int LENGTH = 8;
    private static final int MAX_RANDOM = 2000;
    private static final Paint.Style STYLE = Paint.Style.FILL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtBitMap);
        imageSharp = findViewById(R.id.imgSharp);
        imageCircles = findViewById(R.id.imgCircles);
        Bitmap tmpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kwiat1);
        bitmapSharp = tmpBitmap.copy(Bitmap.Config.ARGB_8888, true);
        tmpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kwiat2);
        bitmapCircles = tmpBitmap.copy(Bitmap.Config.ARGB_8888, true);
    }

    public void onBitmap1(View view) {
        paintSharp();

        imageSharp.setImageBitmap(bitmapSharp);
        textView.setText(String.format(getString(R.string.IMG_INFO),
                imageSharp.getWidth(), imageSharp.getHeight(),
                bitmapSharp.getWidth(), bitmapSharp.getHeight()));
    }

    public void onBitmap2(View view) {
        paintCircles();

        imageCircles.setImageBitmap(bitmapCircles);
        textView.setText(String.format(getString(R.string.IMG_INFO),
                imageCircles.getWidth(), imageCircles.getHeight(),
                bitmapCircles.getWidth(), bitmapCircles.getHeight()));
    }

    private void paintSharp() {
        final int length = LENGTH / 2;
        Canvas canvas = new Canvas(bitmapSharp);
        Paint paint = new Paint();
        Random random = new Random();

        for (int i = 0; i < MAX_RANDOM; i++) {

            int x = random.nextInt(bitmapSharp.getWidth());
            int y = random.nextInt(bitmapSharp.getHeight());
            int color = bitmapSharp.getPixel(x, y);

            paint.setColor(color);
            paint.setStyle(STYLE);

            canvas.drawLine(x - length, y - length, x + length, y + length, paint);
            canvas.drawLine(x - length, y + length, x + length, y - length, paint);
        }
    }

    private void paintCircles() {
        Canvas canvas = new Canvas(bitmapCircles);
        Paint paint = new Paint();
        Random random = new Random();

        for (int i = 0; i < MAX_RANDOM; i++) {

            int x = random.nextInt(bitmapCircles.getWidth());
            int y = random.nextInt(bitmapCircles.getHeight());
            int color = bitmapCircles.getPixel(x, y);

            paint.setColor(color);
            paint.setStyle(STYLE);

            canvas.drawCircle(x, y, LENGTH, paint);
        }
    }

    public void onBitmap3(View view) {
        paintNegative();

        imageSharp.setImageBitmap(bitmapSharp);
        textView.setText(String.format(getString(R.string.IMG_INFO),
                imageSharp.getWidth(), imageSharp.getHeight(),
                bitmapSharp.getWidth(), bitmapSharp.getHeight()));
    }

    private void paintNegative() {
        int bitmapWidth = bitmapSharp.getWidth();
        int bitmapHeight = bitmapSharp.getHeight();

        for (int i = 0; i < bitmapWidth; i++) {
            for (int j = 0; j < bitmapHeight; j++) {

                int color = bitmapSharp.getPixel(i, j);
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);

                bitmapSharp.setPixel(i, j, Color.rgb(255 - r, 255 - g, 255 - b));
            }
        }
    }
}