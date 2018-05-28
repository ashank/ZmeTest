package com.funhotel.hmvp.ui.fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.adapter.ViewPaperAdapter;
import com.funhotel.hmvp.model.entity.NewType;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainNewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainNewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainNewFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  private Disposable dis;
  private List<NewType> list = new ArrayList<>();
  private Toolbar toolbar;
  private AppBarLayout appBarLayout;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  public MainNewFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment MainNewFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MainNewFragment newInstance(String param1, String param2) {
    MainNewFragment fragment = new MainNewFragment();
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
    View view=inflater.inflate(R.layout.fragment_main_new, container, false);
    initView(view);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    list = initNewTypeList();
    setupViewPager();
  }

  private void initView(View view) {
    appBarLayout = (AppBarLayout)view. findViewById(R.id.appbar);
    toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    tabLayout = (TabLayout)view. findViewById(R.id.tablayout);
    viewPager = (ViewPager)view. findViewById(R.id.viewpager);
  }


  private void setupViewPager() {

    List<NewFragment> fragmentList = new ArrayList<>();
    int lenght = list.size();
    for (int i = 0; i < lenght; i++) {
      fragmentList.add(NewFragment.newInstance(list.get(i).getCode()));
    }
    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    ViewPaperAdapter viewPagerAdapter = new ViewPaperAdapter(getActivity().getSupportFragmentManager(), fragmentList,
        list,null);
    viewPager.setAdapter(viewPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
  }


  private List<NewType> initNewTypeList() {

    List<NewType> list = new ArrayList<>();
    NewType newType1 = new NewType("top", "头条");
    NewType newType2 = new NewType("shehui", "社会");
    NewType newType3 = new NewType("guonei", "国内");
    NewType newType4 = new NewType("guoji", "国际");
    NewType newType5 = new NewType("yule", "娱乐");
    NewType newType6 = new NewType("tiyu", "体育");
    NewType newType7 = new NewType("junshi", "军事");
    NewType newType8 = new NewType("keji", "科技");
    NewType newType9 = new NewType("caijing", "财经");
    NewType newType10 = new NewType("shishang", "时尚");
    list.add(newType1);
    list.add(newType2);
    list.add(newType3);
    list.add(newType4);
    list.add(newType5);
    list.add(newType6);
    list.add(newType7);
    list.add(newType8);
    list.add(newType9);
    list.add(newType10);
    return list;
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
