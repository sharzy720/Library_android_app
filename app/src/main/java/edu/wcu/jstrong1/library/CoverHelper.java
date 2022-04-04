package edu.wcu.jstrong1.library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

/**
 *
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
     *
     * @param imageBytes
     * @return
     */
    public Bitmap getImageBitmap(byte[] imageBytes) {
        Bitmap imageBitmap = null;
        if (imageBytes != null) {
            imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        } else {

        }
//        Log.v("strong", imageBitmap.toString());
        return imageBitmap;
    }

    /**
     *
     * @param url
     * @return
     * @throws InterruptedException
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

}
