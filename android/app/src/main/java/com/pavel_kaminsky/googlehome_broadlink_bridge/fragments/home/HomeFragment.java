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
        RMBridgeHttpAsync acCall = new RMBridgeHttpAsync("http://10.0.0.6:7474/?cmd=%7B%22api_id%22:1004,%22command%22:%22send_code%22,%22mac%22:%2234:ea:34:88:f8:61%22,%22data%22:%222600b20063833e4040403f2020201f211f211f211e4140403f211e2120201e221e221f2020201f221e211f211f201f221d221f211f201e221f221e413d2261833e413f403e221f20202020211e211f413f403e21202020201e221f20202020211e221e211f2020211e211e21202020211e221e211f40402160823e4140413e221e2020211f211d221f4040413e2020211f211e221e201f2120211e211f211f211f201f211f211f2020211f211d221f413f217f000d05000000000000%22%7D");
        acCall.execute();
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
