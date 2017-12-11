package com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.setup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.pavel_kaminsky.googlehome_broadlink_bridge.R;
import com.pavel_kaminsky.googlehome_broadlink_bridge.models.ToastError;
import com.pavel_kaminsky.googlehome_broadlink_bridge.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SetupFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.pingButton)
    Button pingButton;

    @BindView(R.id.macTextField)
    EditText macTextField;

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;

    public SetupFragment() {
    }

    @OnClick(R.id.pingButton)
    public void pingDevice() {
        String mac = macTextField.getText().toString();
        boolean isValid = StringUtils.validateMAC(mac);

        if (!isValid) {
            macTextField.setError("Invalid mac");
            return;
        }

        try {
            showAnimation("scanning.json");
        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ToastError("Can't Connect To Device"));
        }
    }

    private void showAnimation(String filename) {
        animationView.setVisibility(View.VISIBLE);
        LottieComposition.Factory.fromAssetFileName(getContext(), filename, (composition) -> {
            assert composition != null;
            animationView.setComposition(composition);
            animationView.playAnimation();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup, container, false);
        ButterKnife.bind(this, view);
        macTextField.setText("34:ea:34:88:f8:61");
        animationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animationView.setVisibility(View.GONE);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
