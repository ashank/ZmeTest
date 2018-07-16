package com.ashank.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PropertyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertyFragment extends Fragment {

    private static final String TAG = "ActivityAnamition";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;

    Unbinder unbinder;

    private int[] startPosition = new int[2];
    private int[] targetPosition = new int[2];

    private float x,y;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PropertyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityAnamitionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PropertyFragment newInstance(String param1, String param2) {
        PropertyFragment fragment = new PropertyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property, container, false);
        unbinder = ButterKnife.bind(this, view);
        button3 = view.findViewById(R.id.button4);
        button8 = view.findViewById(R.id.button8);

        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimatorSet animatorSet =new AnimatorSet();
                ObjectAnimator  objectAnimator = ObjectAnimator. ofFloat(button3,"translationY",0,
                        targetPosition[1]- startPosition[1]);
                ObjectAnimator  objectAnimator1 = ObjectAnimator. ofFloat(button3,"scaleX",1.0f,
                        0.2f);
                ObjectAnimator  objectAnimator2 = ObjectAnimator. ofFloat(button3,"scaleY",1.0f,
                        0.2f);
                objectAnimator.addListener(new AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        Log.e(TAG, "run2: x="+button3.getX() + "  y = "+ button3.getY()  );
                        button3.setVisibility(View.VISIBLE);
                        ObjectAnimator  objectAnimator1 = ObjectAnimator. ofFloat(button3,"scaleX",0.2f,
                                1.0f);
                        ObjectAnimator  objectAnimator2 = ObjectAnimator. ofFloat(button3,"scaleY",0.2f,
                                1.0f);
                        objectAnimator1.start();
                        objectAnimator2.start();
                        button3.setX(x);
                        button3.setY(y);
                        Log.e(TAG, "onAnimationEnd: " );
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorSet.play(objectAnimator).with(objectAnimator1).with(objectAnimator2);
                animatorSet.setDuration(15000);
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.start();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        button3.post(new Runnable() {
            @Override
            public void run() {
                button3.getLocationInWindow(startPosition);
                button8.getLocationInWindow(targetPosition);
                x = startPosition[0];
                y= startPosition[1];

                x = button3.getX();
                y = button3.getY();
                Log.e(TAG, "run: x="+x + "  y = "+y  );
                Log.e(TAG, "run1: x="+button3.getX() + "  y = "+ button3.getY()  );
                Log.e(TAG, "run1111111: x="+targetPosition[0] + "  y = "+ targetPosition[1]  );


            }
        });

    }




    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView: ");
        super.onDestroyView();
        unbinder.unbind();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @OnClick({R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
        }

        Log.e(TAG, "onViewClicked: ");
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
