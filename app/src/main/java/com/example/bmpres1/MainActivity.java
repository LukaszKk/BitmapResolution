package com.example.bmpres1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageSharp;
    private ImageView imageCircles;
    private Bitmap bitmapSharp;
    private Bitmap bitmapCircles;

    private static final int LENGTH = 8;
    private static final int INCREASE = 1;
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

        for (int i = 0; i < bitmapSharp.getWidth(); i += INCREASE) {
            for (int j = 0; j < bitmapSharp.getHeight(); j += INCREASE) {
                int color = bitmapSharp.getPixel(i, j);

                paint.setColor(color);
                paint.setStyle(STYLE);

                canvas.drawLine(i - length, j - length, i + length, j + length, paint);
                canvas.drawLine(i - length, j + length, i + length, j - length, paint);
            }
        }
    }

    private void paintCircles() {
        Canvas canvas = new Canvas(bitmapCircles);
        Paint paint = new Paint();

        for (int i = 0; i < bitmapCircles.getWidth(); i += INCREASE) {
            for (int j = 0; j < bitmapCircles.getHeight(); j += INCREASE) {
                int color = bitmapCircles.getPixel(i, j);

                paint.setColor(color);
                paint.setStyle(STYLE);

                canvas.drawCircle(i, j, LENGTH, paint);
            }
        }
    }
}