package com.tutorial.okadaakihito.androidtutorial.m2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tutorial.okadaakihito.androidtutorial.R;
import com.tutorial.okadaakihito.androidtutorial.m2.dummy.DummyContent;
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
public class HeavyListviewFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = Constant.IMAGE_URL;
    private static final String ARG_PARAM2 = Constant.IMAGE_URL;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Map<String, Object>> mData;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static HeavyListviewFragment newInstance(String param1, String param2) {
        HeavyListviewFragment fragment = new HeavyListviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        ListTemplateAdapter adapter = null;        //独自のアダプタを作成

        String[] from_template = {"url","viewImage"};
        int[] to_template = {R.id.TextUrl, R.id.ImageThumb};

        //画面のリストビュー
        ListView list_group = (ListView) view.findViewById(android.R.id.list);

        //テンプレートのリスト取得
        mData = new ArrayList<Map<String, Object>>();
        Map<String, Object> mMap;

        //データの設定（仮・データ件数毎の設定）
        mMap = new HashMap<String, Object>();
        for (int i=0;i<1000;i++) {
            mMap.put("id", String.valueOf(i));
            mMap.put("url", ARG_PARAM1);
            mMap.put("viewImage", ARG_PARAM2);
            mData.add(mMap);
        }

        adapter = new ListTemplateAdapter(getActivity().getApplicationContext(), mData, R.layout.listview_template, from_template, to_template);

        if(mData != null && mData.size() > 0){
            adapter.setListData(mData);    //テンプレートにデータ内容を保持

            list_group.setFocusable(false);
            list_group.setAdapter(adapter);
            list_group.setScrollingCacheEnabled(false);
            list_group.setTextFilterEnabled(true);

            list_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //リスト選択時の処理
                    ImageCache.clearCache();
                    //終了処理
                }
            });

            list_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //リスト選択時の処理
                    ImageCache.clearCache();
                    //終了処理
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        ImageCache.clearCache();
        super.onDestroyView();
    }

    @Override
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
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }
}
