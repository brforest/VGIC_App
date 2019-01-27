package net.mesoscopia.ap001;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewObj;
    Button staticTriggerButton;
    Button dynamicTriggerButton;
    int nPoints;
    static Bitmap bitmap;
    static MyPlottingClass myPlottingObj;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPlottingObj = new MyPlottingClass();
        imageViewObj = (ImageView) findViewById(R.id.imageView);
        staticTriggerButton = (Button) findViewById(R.id.staticTriggerXML);
        staticTriggerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawStaticPlot();
            }
        });
        dynamicTriggerButton = (Button) findViewById(R.id.dynamicTriggerXML);
        dynamicTriggerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawDynamicPlot();
            }
        });
    }

    public void drawStaticPlot() {
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

    public void drawDynamicPlot() {
        nPoints = 500;
        final double[] Y_Data = new double[nPoints];

        // Creating multiple threads for animated graphing
        // t1 is the calculation thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < nPoints; i++) {
                    double x = (double) i;
                    Y_Data[i] = 100.00 * Math.exp(-x/100.0)*Math.cos(x/4);
                    try {
                        Thread.sleep(30);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // t2 is the graphing thread
        Thread t2 = new Thread(new Runnable() {
                @Override
                public void run () {
                    for (int i = 0; i < nPoints; i++) {
                        try {
                            // to edit UI elements, we must run on UIThread
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    bitmap = myPlottingObj.DrawYPlot(Y_Data);
                                    imageViewObj.setImageBitmap(bitmap);
                                }
                            });
                            Thread.sleep(30);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
        });
        t1.start();
        t2.start();
    }

}
