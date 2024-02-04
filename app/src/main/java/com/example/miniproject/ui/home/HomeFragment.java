package com.example.miniproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.miniproject.R;
import com.example.miniproject.databinding.FragmentHomeBinding;
import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment {

//    private static final Object FF6200EE = ;
    private FragmentHomeBinding binding;
    private boolean connected = false;
    private View root; // Declare root as a class-level variable

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot(); // Assign the root view

        final TextView textView = binding.connectButton;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Assuming you have a button with the ID "connectButton"
        binding.connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!connected) {
                    // Display a toast message when the button is clicked
                    showToast("App connected");

                    // Change the UI to a button and a dial
                    changeUI();
                    connected = true;
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Helper method to show a toast message
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void changeUI() {
        // Remove the existing button
        binding.connectButton.setVisibility(View.GONE);

        // Create a new MaterialButton with styling
        MaterialButton stylishButton = new MaterialButton(requireContext());
        stylishButton.setText("On / Off");
        stylishButton.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.purple_500));
        stylishButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        stylishButton.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        stylishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("On and Off");
            }
        });

        // Create four more buttons (total of five buttons)
        for (int i = 1; i <= 4; i++) {
            MaterialButton button = new MaterialButton(requireContext());
            button.setText("Mode " + i);
            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 1:
                            performButtonClick1();
                            break;
                        case 2:
                            performButtonClick2();
                            break;
                        case 3:
                            performButtonClick3();
                            break;
                        case 4:
                            performButtonClick4();
                            break;
                    }
                }
            });

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL); // Center horizontally

            // Set margins for buttons below each other
            int marginTop = getResources().getDimensionPixelSize(R.dimen.button_margin_top);
            layoutParams.topMargin = i * marginTop;

            button.setLayoutParams(layoutParams);

            // Add the buttons to the layout
            RelativeLayout layout = root.findViewById(R.id.home); // replace with your actual parent layout ID
            layout.addView(button);
        }

        // Add the stylish button to the layout
        RelativeLayout.LayoutParams stylishLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        stylishLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        stylishButton.setLayoutParams(stylishLayoutParams);

        RelativeLayout layout = root.findViewById(R.id.home); // replace with your actual parent layout ID
        layout.addView(stylishButton);
    }

    private void performButtonClick1() {
        showToast("Mode 1");

    }
    private void performButtonClick2() {
        showToast("Mode 2");

    }
    private void performButtonClick3() {
        showToast("Mode 3");

    }
    private void performButtonClick4() {
        showToast("Mode 4");

    }
    private void performStylishButtonClick() {
        showToast("On and Off");

    }
}
