package com.tutorial.okadaakihito.androidtutorial.m2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.tutorial.okadaakihito.androidtutorial.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by okadaakihito on 2014/12/26.
 */
class ListTemplateAdapter extends SimpleAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Map<String, Object>> items;

    //コンストラクタ
    public ListTemplateAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setListData(ArrayList<Map<String, Object>> data){
        //データ内容を保持しておく
        items = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.listview_template, null);
        }

        Map<String, Object> settingData = items.get(position);

        TextView textUrl = (TextView)v.findViewById(R.id.TextUrl);
        ImageView imageView = (ImageView)v.findViewById(R.id.ImageThumb);
        ProgressBar waitBar = (ProgressBar)v.findViewById(R.id.WaitBar);

        //画像を隠し、プログレスバーを表示
        waitBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);

        //実際に使用する画像のURLを保持
        textUrl.setText(settingData.get("url").toString());
        //リスト表示用画像のURLを取得
        String thumbUrl = settingData.get("viewImage").toString();

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
