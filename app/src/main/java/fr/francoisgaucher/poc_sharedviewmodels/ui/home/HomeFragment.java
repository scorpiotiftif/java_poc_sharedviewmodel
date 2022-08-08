package fr.francoisgaucher.poc_sharedviewmodels.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import fr.francoisgaucher.poc_sharedviewmodels.databinding.FragmentHomeBinding;
import fr.francoisgaucher.poc_sharedviewmodels.ui.MainViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private MainViewModel mainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mainViewModel.getToastMessage().observe(this.getViewLifecycleOwner(),message -> {
            Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show();
        });
        Log.d("Francois", "Instance n°"+mainViewModel.hashCode());

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainViewModel.fireToast();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
