package com.tutorial.okadaakihito.androidtutorial.m2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.tutorial.okadaakihito.androidtutorial.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by okadaakihito on 2014/12/26.
 */
class ListTemplateAdapter extends SimpleAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Map<String, Object>> items;

    //コンストラクタ
    public ListTemplateAdapter(Context context, ArrayList<Map<String, Object>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        this.items = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.listview_template, null);
        }

        Map<String, Object> settingData = items.get(position);

        ImageView imageView = (ImageView)v.findViewById(R.id.ImageThumb);
        ProgressBar waitBar = (ProgressBar)v.findViewById(R.id.WaitBar);

        //画像を隠し、プログレスバーを表示
        waitBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);

        //リスト表示用画像のURLを取得
        String thumbUrl = settingData.get("url").toString();

        //仮の画像設定
        imageView.setImageDrawable(context.getResources().getDrawable(android.R.drawable.ic_menu_gallery));

        //画像読込
        try{
            imageView.setTag(thumbUrl);
            // AsyncTaskは１回しか実行できない為、毎回インスタンスを生成
            ImageGetTask task = new ImageGetTask(imageView, waitBar);
            task.execute(thumbUrl);
        }
        catch(Exception e){
            imageView.setImageDrawable(context.getResources().getDrawable(android.R.drawable.ic_notification_clear_all));
            waitBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }

        return v;
    }
}
