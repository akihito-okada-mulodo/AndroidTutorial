package com.tutorial.okadaakihito.androidtutorial.m2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tutorial.okadaakihito.androidtutorial.R;
import com.tutorial.okadaakihito.androidtutorial.model.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class HeavyListviewFragment extends Fragment implements ListView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = Constant.IMAGE_URL1;
    private static final String ARG_PARAM2 = Constant.IMAGE_URL2;

    private String mParam1;
    private String mParam2;
    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipeRefreshLayout mSwipeRefreshEmptyViewLayout;
    private OnFragmentInteractionListener mListener;
    private ListTemplateAdapter mAdapter = null;

    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HeavyListviewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Save metrix
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        MetrixCache.setMetrix(displayMetrics);

        // SwipeRefreshLayoutの設定
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefreshEmptyViewLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshEmptyView);

        setSwipeRefreshLayout(mSwipeRefreshLayout);
        setSwipeRefreshLayout(mSwipeRefreshEmptyViewLayout);

        String[] from_template = {"url","viewImage"};
        int[] to_template = {R.id.TextUrl, R.id.ImageThumb};

        mAdapter = new ListTemplateAdapter(getActivity().getApplicationContext(), mData, R.layout.listview_template, from_template, to_template);
        refreshListViewData();

        //画面のリストビュー
        mListView = (ListView) view.findViewById(R.id.listView);
        mListView.setEmptyView(mSwipeRefreshEmptyViewLayout);
        mListView.setFocusable(false);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageCache.clearCache();
            }
        });

        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImageCache.clearCache();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        swipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue, R.color.yellow);
    }

    @Override
    public void onDestroyView() {
        ImageCache.clearCache();
        MetrixCache.clearMetrix();
        super.onDestroyView();
    }
    
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction((String) mData.get(position).get("id"));
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageCache.clearCache();
                    refreshListViewData1();
                    mAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                    mSwipeRefreshEmptyViewLayout.setRefreshing(false);
                }
            }, 3000);
        }
    };

    private void refreshListViewData() {
        //テンプレートのリスト取得
        Map<String, Object> mMap;
        mData.clear();
        //データの設定（仮・データ件数毎の設定）
        mMap = new HashMap<String, Object>();
        for (int i=0;i<1000;i++) {
            mMap.put("id", String.valueOf(i));
            mMap.put("url", ARG_PARAM1);
            mData.add(mMap);
        }
    }

    private void refreshListViewData1() {
        //テンプレートのリスト取得
        Map<String, Object> mMap;
        mData.clear();
        //データの設定（仮・データ件数毎の設定）
        mMap = new HashMap<String, Object>();
        for (int i=0;i<1000;i++) {
            mMap.put("id", String.valueOf(i));
            mMap.put("url", ARG_PARAM2);
            mData.add(mMap);
        }
    }
}
