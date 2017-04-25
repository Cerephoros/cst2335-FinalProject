package com.algonquincollege.njol0004.finalproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {


    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_menu_fragment, container, false);
        String[] menuItems={"GPS", "Lights", "radio", "temperature", "Info"};

        ListView listview = (ListView) view.findViewById(R.id.menulist);
        ArrayAdapter<String> lvadapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems);
        listview.setAdapter(lvadapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent = new Intent(getActivity(), MapsActivity.class );
                    startActivity(intent);
                } else if (position ==1){
                    Intent intent = new Intent(getActivity(), LightsActivity.class );
                    startActivity(intent);
                } else if (position ==2){
                    Intent intent = new Intent(getActivity(), Radio.class );
                    startActivity(intent);
                }
                else if (position ==4){
                    Toast.makeText(getActivity(), "Car Activity By: Sam Decker, V 2.3, Click on the appropriate listview item to control features in your car", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}

