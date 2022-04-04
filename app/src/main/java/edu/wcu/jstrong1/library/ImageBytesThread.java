package edu.wcu.jstrong1.library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class ImageBytesThread implements Runnable {
    private volatile byte[] imageBytes;
    private String imageAddress;

    @Override
    public void run() {
        try  {
            try {
                URL url = new URL(imageAddress);
                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imageBytes = stream.toByteArray();
                image.recycle();
            } catch(IOException e) {
                Log.v("strong", "ImageBytesThread error: " + e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setImageAddress(String url) {
        imageAddress = url;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

}
