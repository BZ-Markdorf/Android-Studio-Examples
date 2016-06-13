package kasimirspeetzen.sensor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    AnimatedView animatedView;
    ShapeDrawable mDrawable = new ShapeDrawable();

    public static int x;
    public static int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //Aufrufen des Beschleunigungssensors
        animatedView = new AnimatedView(this);                                     //AnimatedView

        setContentView(animatedView);                                              //AnimatedView Layout

        sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_GAME); //startet Sensorabfrage
    }

    public class AnimatedView extends ImageView {
        static final int width = 50;                            //Breite des mDrawable
        static final int height = 50;                           //Höhe

        public AnimatedView(Context context) {
            super(context);
            mDrawable = new ShapeDrawable(new RectShape());     //mDrawable ist ein Rechteck
            mDrawable.getPaint().setColor(0xffffAC23);          //Farbe
            mDrawable.setBounds(x, y, x + width, y + height);   //Größe/Position
        }

        @Override
        protected void onDraw(Canvas canvas) {
            mDrawable.setBounds(x-width, y-height, x + width, y + height); //Größe/Position
            mDrawable.draw(canvas);                                        //Canvas
            invalidate();                                                  //Löschen von Canvas
        }
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        //Werden wir in diesem Beispiel nicht benutzen.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {    //Prüfen ob Beschleunigungssensor

            Display display = getWindowManager().getDefaultDisplay(); //Display des Geräts
            Point size = new Point();
            display.getSize(size);                                    //Größe des Displays
            int width_max = size.x;                                   //maximaler x-Wert
            int height_max = size.y;                                  //maximaler x-Wert

            x -= (int) event.values[0];                               //Ändert x-Wert mit SensorWert
            y += (int) event.values[1];                               //Ändert x-Wert mit SensorWert

            if(!(width_max-50>x)) {                                   //mDrawable über den Bildschirmrand hinaus
                x = width_max-50;                                     //setze mDrawable an Bildschirmrand zurück
            }
            if(!(height_max-100>y)) {
                y = height_max-100;
            }
            if(!(50<=x)){
                x =50;
            }
            if(!(50<=y )){
                y =50;
            }
        }
    }
}

