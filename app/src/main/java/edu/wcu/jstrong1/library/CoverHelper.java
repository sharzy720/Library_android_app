package edu.wcu.jstrong1.library;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

/**
 * Helper class for dealing with book cover images
 */
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

    /**
     * Get the image's bitmap from a byte array
     * @param imageBytes An image's byte array
     * @return An images bitmap
     */
    public Bitmap getImageBitmap(byte[] imageBytes) {
        Bitmap imageBitmap = null;
        if (imageBytes != null) {
            imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        }
//        Log.v("strong", imageBitmap.toString());
        return imageBitmap;
    }

    /**
     * Using a thread to get a byte array based off of an image
     * @param url Address to the image
     * @return Byte array of image
     * @throws InterruptedException When the thread is interrupted
     */
    public byte[] getImageFromUrl(String url) throws InterruptedException {
        Log.v("strong", "Getting image from: " + url);
        ImageBytesThread imageBytesThread = new ImageBytesThread();
        imageBytesThread.setImageAddress(url);
        Thread thread = new Thread(imageBytesThread);
        thread.start();
        thread.join();
        return imageBytesThread.getImageBytes();
    }

    /**
     * Gets the byte array for the substitute cover when a book is missing one
     * @param resources Resources to use when getting drawable
     * @return Missing cover drawable as byte array
     */
    public byte[] getMissingCover(Resources resources) {
        Log.v("strong", "Getting image from drawable");
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.missing_book_cover);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
}
