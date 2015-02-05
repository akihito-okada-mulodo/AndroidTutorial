package com.tutorial.okadaakihito.androidtutorial.m2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tutorial.okadaakihito.androidtutorial.model.ApplicationController;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by okadaakihito on 2014/12/26.
 */
class ImageGetTask extends AsyncTask<String,Void,Bitmap> {
    private ImageView image;
    private ProgressBar progress;
    private String tag;
    private Context context;

    public ImageGetTask(ImageView _image, ProgressBar _progress) {
        //対象の項目を保持しておく
        image = _image;
        progress = _progress;
        tag = image.getTag().toString();
        context = ApplicationController.getInstance().getApplicationContext();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        // ここでHttp経由で画像を取得します。取得後Bitmapで返します。
        synchronized (context){
            try {
                //キャッシュより画像データを取得
                Bitmap image = ImageCache.getImage(params[0]);
                if (image == null) {
                    //キャッシュにデータが存在しない場合はwebより画像データを取得
                    URL imageUrl = new URL(params[0]);
                    image = getImage(imageUrl);
                    //取得した画像データをキャッシュに保持
                    ImageCache.setImage(params[0], image);
                }
                return image;
            } catch (MalformedURLException e) {
                return null;
            }
        }
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        // Tagが同じものか確認して、同じであれば画像を設定する
        // （Tagの設定をしないと別の行に画像が表示されてしまう）
        if(tag.equals(image.getTag())){
            if(result!=null){
                //画像の設定
                image.setImageBitmap(result);
            }
            else{
                //エラーの場合は×印を表示
                image.setImageDrawable(context.getResources().getDrawable(android.R.drawable.ic_notification_clear_all));
            }
            //プログレスバーを隠し、取得した画像を表示
            progress.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
        }
    }

    private Bitmap getImage(URL imageUrl) {

        InputStream inputStream = null;
        if (imageUrl != null) {
            try {
                inputStream = (InputStream) imageUrl.getContent();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        DisplayMetrics metrics = MetrixCache.get();

        if (metrics != null) {
            // 画像サイズ情報を取得する
            Log.v("image", "Original Image Size: " + bitmap.getWidth() + " x " + bitmap.getHeight());

            float scale = (float) bitmap.getWidth() / metrics.widthPixels;

            if (scale > 1) {
                int imageWidth = (int) (bitmap.getWidth() / scale);
                int imageHeight = (int) (bitmap.getHeight() / scale);

                bitmap = Bitmap.createScaledBitmap(
                        bitmap,
                        imageWidth,
                        imageHeight,
                        false);
            }
        }
        return bitmap;
    }
}
