package cn.edu.hebtu.software.peace.breathe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.edu.hebtu.software.peace.breathe.utils.SelectMinute_PickView;
import cn.edu.hebtu.software.peace.breathe.utils.WaveFunctionView;

import cn.edu.hebtu.software.peace.R;


public class Notice_item1Fragment extends Fragment {

   private SelectMinute_PickView minute_pv;

    private RelativeLayout middleText;
    private RelativeLayout selectTime;

    WaveFunctionView bezierView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.breathe_notice_item1, container, false);
        return view;
      /*  minute_pv = view.findViewById(R.id.minute_pv);
        middleText = view.findViewById(R.id.middle_text);
        selectTime = view.findViewById(R.id.select_minute);

        middleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                middleText.setVisibility(View.GONE);
                selectTime.setVisibility(View.VISIBLE);

            }
        });
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                middleText.setVisibility(View.VISIBLE);
                selectTime.setVisibility(View.GONE);
            }
        });

        List<String> data = new ArrayList<String>();
        final List<String> seconds = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            data.add("0" + i);
        }
        for (int i = 0; i < 60; i++)
        {
            seconds.add(i < 10 ? "0" + i : "" + i);
        }
        minute_pv.setData(data);
        minute_pv.setOnSelectListener(new SelectMinute_PickView.onSelectListener()
        {
            @Override
            public void onSelect(String text)
            {
                Toast.makeText(getActivity(),"选择了 " + text + " 分",Toast.LENGTH_LONG).show();
            }
        });*/

    }
   /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }*/


}



