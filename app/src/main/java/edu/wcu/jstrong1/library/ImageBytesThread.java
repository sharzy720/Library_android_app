package edu.wcu.jstrong1.library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

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
//            Bitmap bm = null;
//            URL imageUrl = new URL(imageAddress);
//            URLConnection urlConnection = imageUrl.openConnection();
//            InputStream is = urlConnection.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            bm = BitmapFactory.decodeStream(bis);
////            byte[] imageArray = getBytes(bm);
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
////                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
////            } else {
////                bm.compress(Bitmap.CompressFormat.WEBP, 100, stream);
////            }
////            imageBytes = stream.toByteArray();
////            bm.recycle();
//
//            int size = bm.getRowBytes() * bm.getHeight();
//            ByteBuffer byteBuffer = ByteBuffer.allocate(size);
//            bm.copyPixelsToBuffer(byteBuffer);
//            imageBytes = byteBuffer.array();

//            URL imageUrl = new URL(imageAddress);
//            URLConnection urlConnection = imageUrl.openConnection();
//
//            InputStream inputStream = urlConnection.getInputStream();
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//
//            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//            byte[] data = new byte[500];
//
//            int current = 0;
//            while ((current = bufferedInputStream.read()) != -1)
//            {
//                buffer.write(data, 0, current);
//            }
//
//            imageBytes = buffer.toByteArray();
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
