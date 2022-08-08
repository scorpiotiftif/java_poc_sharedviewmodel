package fr.francoisgaucher.poc_sharedviewmodels.ui.dashboard;

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

import fr.francoisgaucher.poc_sharedviewmodels.databinding.FragmentDashboardBinding;
import fr.francoisgaucher.poc_sharedviewmodels.ui.MainViewModel;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private MainViewModel mainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mainViewModel.getToastMessage().observe(this.getViewLifecycleOwner(),message -> {
            Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show();
        });
        Log.d("Francois", "Instance n°"+mainViewModel.hashCode());

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
