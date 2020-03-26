package com.example.smart_agriculture_deloitte.ui.send;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smart_agriculture_deloitte.R;
import com.example.smart_agriculture_deloitte.ui.home.HomeFragment;

public class SendFragment extends Fragment {


    private SendViewModel sendViewModel;
    private HomeFragment homeFragment;

    EditText input_number;
    Button input_number_button;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });




        input_number = root.findViewById(R.id.input_number);
        input_number.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(2)});
        input_number_button = root.findViewById(R.id.input_number_button);



        input_number_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeFragment.number_of_data = Integer.parseInt( input_number.getText().toString() );
                Toast.makeText(getActivity(), "Number of Input Data is set to "+homeFragment.number_of_data+"!", Toast.LENGTH_LONG).show();

            }
        });






        return root;
    }
}