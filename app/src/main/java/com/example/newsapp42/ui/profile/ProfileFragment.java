package com.example.newsapp42.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.newsapp42.MainActivity;
import com.example.newsapp42.R;
import com.example.newsapp42.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Uri uri;
    private NavController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imageView.setOnClickListener(view1 -> openGallery());
        binding.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String url = String.valueOf(uri);
                controller.navigate(ProfileFragmentDirections.actionNavigationProfileToTransitionFragment(url));
                return true;
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        addPhoto.launch(intent);
    }
    ActivityResultLauncher<Intent> addPhoto=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== Activity.RESULT_OK){
                    Intent data = result.getData();
//                    System.out.println("-1-2---"+data);
                    assert data != null;
                    uri=data.getData();
//                    System.out.println("____"+uri.toString());
                    //binding.imageView.setImageURI(uri);
                    Glide.with(requireContext()).load(uri).circleCrop().into(binding.imageView);
                    MainActivity.prefs.saveImage(String.valueOf(uri));
                }
            }
        });

//    ActivityResultLauncher<Intent> addPhoto = registerForActivityResult(
//         new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//             @Override
//             public void onActivityResult(ActivityResult result) {
//                 if(result.getResultCode()==Activity.RESULT_OK){
//                     Intent intent = result.getData();
////                     System.out.println(intent+"----------");
//                     uri = intent.getData();
//                     System.out.println("___333___"+uri);
//                 }
//             }
//         });
    @Override
    public void onStart() {
        super.onStart();
        if(MainActivity.prefs.getImage()!=null){
            uri= Uri.parse(MainActivity.prefs.getImage());
            Glide.with(requireContext()).load(uri).circleCrop().into(binding.imageView);
        }
    }
}
