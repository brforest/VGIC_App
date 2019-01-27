package net.mesoscopia.ap001;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyPlottingClass {
    private Bitmap bitmap;
    private Paint paint;
    private Canvas canvas;
    private int startx = 250;
    private int starty = 250;
    private int endx = 750;
    private int endy = 750;
    private int bitmapHeight = 1200;
    private int bitmapWidth = 1200;
    private final double MAX_Y = 100.00;
    private final double MIN_Y = -100.00;
    private int leftMargin;
    private int topMargin;
    private int plottableWidth;
    private int getPlottableHeight;
    private int nPoints;

    MyPlottingClass(){
        topMargin = bitmapHeight/10;
        leftMargin = bitmapWidth/10;
        plottableWidth = bitmapWidth*8/10;
        getPlottableHeight = bitmapHeight*8/10;
        bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        paint = new Paint();
        canvas = new Canvas(bitmap);
    }

    public Bitmap DrawYPlot(double[] Y){
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        nPoints = Y.length;
        double delta_i = (double) plottableWidth/nPoints;
        startx = leftMargin;
        starty = (int) ((0.500-(Y[0]/(MAX_Y-MIN_Y)))*getPlottableHeight) + topMargin;
        for(int ii=1;ii<nPoints;ii++) {
            endx = (int)(delta_i*ii)+leftMargin;
            endy = (int) ((0.500-(Y[ii]/(MAX_Y-MIN_Y)))*getPlottableHeight) + topMargin;
            canvas.drawLine(startx, starty, endx, endy, paint);
            startx=endx;
            starty=endy;
        }
        return bitmap;
    }

}
