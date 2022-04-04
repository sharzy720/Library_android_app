package edu.wcu.jstrong1.library;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.sql.Blob;

public class CoverHelper extends AppCompatActivity {

    // Get image from database
//    public void buttonFetch(View view){
//        String stringQuery = "Select Image from ImageTable where Name=\"MyImage\"";
//        Cursor cursor = sqLiteDatabase.rawQuery(stringQuery, null);
//        try {
//            cursor.moveToFirst();
//            byte[] bytesImage = cursor.getBlob(0);
//            cursor.close();
//            Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
//            imageView.setImageBitmap(bitmapImage);
//            textViewStatus.setText("Fetch Successful");
//        }
//        catch (Exception e){
//            textViewStatus.setText("ERROR");
//        }
//    }

    // add image to database
//    public void buttonInsert(View view){
////        String stringFilePath = Environment.getExternalStorageDirectory().getPath()+"/Download/"+editTextFileName.getText().toString()+".jpeg";
//        Bitmap bitmap = BitmapFactory.decodeFile(stringFilePath);
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
//
//        byte[] bytesImage = byteArrayOutputStream.toByteArray();
//
//        contentValues.put("Image", bytesImage);
//    }

    public Bitmap getImageBitmap(byte[] imageBytes) {
//        Bitmap.Config configBmp = Bitmap.Config.valueOf(bitmap.getConfig().name());
//        Bitmap bitmap_tmp = Bitmap.createBitmap(width, height, configBmp);
//        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
//        bitmap_tmp.copyPixelsFromBuffer(buffer);
        Bitmap imageBitmap = null;
        if (imageBytes != null) {
            imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        } else {
            // TODO get bitmap of
//            getcontext
//            Drawable test = getDrawable(R.drawable.no_book_cover);
//            imageBitmap = ((BitmapDrawable) test).getBitmap();
//            Drawable image = Resources.getSystem().getDrawable(R.drawable.no_book_cover);
//            imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.no_book_cover);
//            imageBitmap =
        }
//        Log.v("strong", imageBitmap.toString());
        return imageBitmap;
    }

    public byte[] getImageFromUrl(String url) throws InterruptedException {
        Log.v("strong", "Getting image from: " + url);

        ImageBytesThread imageBytesThread = new ImageBytesThread();
        imageBytesThread.setImageAddress(url);
        Thread thread = new Thread(imageBytesThread);
        thread.start();
        thread.join();
        return imageBytesThread.getImageBytes();
    }

}
