package com.tutorial.okadaakihito.androidtutorial.m2;

import android.util.DisplayMetrics;

/**
 * Created by okadaakihito on 2014/12/26.
 */
public class MetrixCache {
    private static DisplayMetrics metrics;

    //キャッシュより画像データを取得
    public static DisplayMetrics get() {
        //存在しない場合はNULLを返す
        return metrics;
    }

    //キャッシュに画像データを設定
    public static void setMetrix(DisplayMetrics metrics) {
        MetrixCache.metrics = metrics;
    }

    //キャッシュの初期化（リスト選択終了時に呼び出し、キャッシュで使用していたメモリを解放する）
    public static void clearMetrix(){
        metrics = null;
    }
}
