package com.example.smart_agriculture_deloitte.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smart_agriculture_deloitte.MainActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.example.smart_agriculture_deloitte.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.smart_agriculture_deloitte.R.array.field_data_names;

public class HomeFragment extends Fragment  {



    private HomeViewModel homeViewModel;

    FirebaseDatabase rootReference;
    DatabaseReference reference;

    DatabaseReference dateTimeReference, desirableCropsReference;                                                                         //string
    DatabaseReference airQualityReference, humidityReference, soilMoistureReference, soilphReference, temperatureReference;               //int
    DatabaseReference cameraDataAnalysisReference;
    DatabaseReference diseaseDetectionReference, weedpestDetectionReference, soilTextureReference, yieldPredictionReference;              //int

    public Spinner spinner;
    TextView data;




    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });




        spinner = root.findViewById(R.id.spinner);
        data = root.findViewById(R.id.data);

        rootReference = FirebaseDatabase.getInstance();
        reference = rootReference.getReference();

        dateTimeReference = reference.child("Date-Time");
        desirableCropsReference = reference.child("Desirable-Crop(s)");

        airQualityReference = reference.child("Air-Quality");
        humidityReference = reference.child("Humidity");
        soilMoistureReference = reference.child("Soil-Moisture");
        soilphReference = reference.child("Soil-pH");
        temperatureReference = reference.child("Temperature");

        cameraDataAnalysisReference = reference.child("Camera-Data-Analysis");

        diseaseDetectionReference = cameraDataAnalysisReference.child("Disease-Detection");
        weedpestDetectionReference = cameraDataAnalysisReference.child("Weed&PestsDetection");
        soilTextureReference = cameraDataAnalysisReference.child("Soil-Texture");
        yieldPredictionReference = cameraDataAnalysisReference.child("Yield-Prediction");





        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.field_data_names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(myAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItemText = (String) parentView.getItemAtPosition(position);
                data.setText(selectedItemText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });





        return root;
    }





}