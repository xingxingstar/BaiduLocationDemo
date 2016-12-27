package com.example.zhuwojia.baidulocationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CityActivity extends Activity {

    private ListView mCityLit;
    private TextView overlay;
    private QuicLocationBar mQuicLocationBar;
    private HashMap<String, Integer> alphaIndexer;
    private ArrayList<CityBean> mCityNames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        mQuicLocationBar = (QuicLocationBar) findViewById(R.id.city_loactionbar);
        mQuicLocationBar
                .setOnTouchLitterChangedListener(new LetterListViewListener());
        overlay = (TextView) findViewById(R.id.city_dialog);
        mCityLit = (ListView) findViewById(R.id.city_list);
        mQuicLocationBar.setTextDialog(overlay);
        initList();
    }

    private void initList() {
        mCityNames = getCityNames();
        CityAdapter adapter = new CityAdapter(CityActivity.this, mCityNames);
        mCityLit.setAdapter(adapter);
        alphaIndexer = adapter.getCityMap();
        mCityLit.setOnItemClickListener(new CityListOnItemClick());
    }

    private ArrayList<CityBean> getCityNames() {
        String word = "A";
        ArrayList<CityBean> names = new ArrayList<CityBean>();
        for (int i = 0; i < Cities.mCitiesStrings.length; i++) {

            if (Cities.mCitiesStrings[i].equals("A") || Cities.mCitiesStrings[i].equals("B") || Cities.mCitiesStrings[i].equals("C") ||
                    Cities.mCitiesStrings[i].equals("D") || Cities.mCitiesStrings[i].equals("E") || Cities.mCitiesStrings[i].equals("F") ||
                    Cities.mCitiesStrings[i].equals("G") || Cities.mCitiesStrings[i].equals("H") || Cities.mCitiesStrings[i].equals("I")
                    || Cities.mCitiesStrings[i].equals("J") || Cities.mCitiesStrings[i].equals("K") || Cities.mCitiesStrings[i].equals("L") ||
                    Cities.mCitiesStrings[i].equals("M") || Cities.mCitiesStrings[i].equals("N") || Cities.mCitiesStrings[i].equals("O") ||
                    Cities.mCitiesStrings[i].equals("P") || Cities.mCitiesStrings[i].equals("Q") || Cities.mCitiesStrings[i].equals("R") ||
                    Cities.mCitiesStrings[i].equals("S") || Cities.mCitiesStrings[i].equals("T") || Cities.mCitiesStrings[i].equals("U") ||
                    Cities.mCitiesStrings[i].equals("V") || Cities.mCitiesStrings[i].equals("W") || Cities.mCitiesStrings[i].equals("X") ||
                    Cities.mCitiesStrings[i].equals("Y") || Cities.mCitiesStrings[i].equals("Z")) {
                word = Cities.mCitiesStrings[i];
            } else {
                CityBean bean = new CityBean();
                bean.setNameSort(word);
                bean.setCityName(Cities.mCitiesStrings[i]);
                names.add(bean);
            }
        }
        return names;
    }

    private class CityListOnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                long arg3) {
            CityBean cityModel = (CityBean) mCityLit.getAdapter()
                    .getItem(pos);
            Toast.makeText(CityActivity.this, cityModel.getCityName(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    private class LetterListViewListener implements
            QuicLocationBar.OnTouchLetterChangedListener {

        @Override
        public void touchLetterChanged(String s) {
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
                mCityLit.setSelection(position);
            }
        }

    }

}
