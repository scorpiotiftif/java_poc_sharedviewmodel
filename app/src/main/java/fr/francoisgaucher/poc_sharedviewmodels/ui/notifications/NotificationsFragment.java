package fr.francoisgaucher.poc_sharedviewmodels.ui.notifications;

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

import fr.francoisgaucher.poc_sharedviewmodels.databinding.FragmentNotificationsBinding;
import fr.francoisgaucher.poc_sharedviewmodels.ui.MainViewModel;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private MainViewModel mainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainViewModel.getToastMessage().observe(this.getViewLifecycleOwner(),message -> {
            Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show();
        });
        Log.d("Francois", "Instance n°"+mainViewModel.hashCode());

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
