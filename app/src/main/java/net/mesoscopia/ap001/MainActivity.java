package net.mesoscopia.ap001;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    static ImageView imageViewObj;
    Button APtriggerButtonObj;
    int nPoints;
    static Bitmap bitmap;
    static MyPlottingClass myPlottingObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPlottingObj = new MyPlottingClass();
        imageViewObj = (ImageView) findViewById(R.id.imageView);
        APtriggerButtonObj = (Button) findViewById(R.id.APtriggerButton);
        APtriggerButtonObj.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            drawExpSinYPlot();
        }});
    }

    public void drawExpSinYPlot() {
        nPoints=500;
        double[] Y_Data = new double[nPoints];
        double x;
        for (int ii = 0; ii < nPoints; ii++) {
            x = (double) ii;
            Y_Data[ii] = 100.00 * Math.exp(-x/100.0)*Math.cos(x/4);
        }
        bitmap = myPlottingObj.DrawYPlot(Y_Data);
        imageViewObj.setImageBitmap(bitmap);
    }
}
