package com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pavel_kaminsky.googlehome_broadlink_bridge.R;
import com.pavel_kaminsky.googlehome_broadlink_bridge.utils.RMBridgeHttpAsync;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.testButton)
    Button testButton;

    public HomeFragment() {
    }

    @OnClick(R.id.testButton)
    public void test() {
        RMBridgeHttpAsync call = new RMBridgeHttpAsync("http://localhost:7474/?cmd=%7B%22api_id%22:1004,%22command%22:%22send_code%22,%22mac%22:%2234:ea:34:88:f8:61%22,%22data%22:%22260058000001259412131114113812131113121311131213123712371213123711381238113812371213121213121138121313111213111411381237123712131237123811381237120005200001244c12000c470001244c11000d05%22%7D");
        call.execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
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
