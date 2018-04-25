/*
 *   Copyright (C) 2018  ZME
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.funhotel.hmvp.ui.fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.adapter.NewListAdapter;
import com.funhotel.hmvp.model.entity.New;
import com.funhotel.hmvp.model.entity.NewEntity.ResultEntity;
import com.funhotel.hmvp.model.viewmodel.NewViewModel;
import com.funhotel.hmvp.presenter.NewPresenterImp;
import com.zme.zlibrary.utils.LogUtils;
import com.zme.zlibrary.widget.AutoLoadMoreRecylerView;
import com.zme.zlibrary.widget.AutoLoadMoreRecylerView.ILoadMoreListener;
import com.zme.zlibrary.widget.recycler.OnItemClickListner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFragment extends Fragment implements OnRefreshListener,NewViewModel {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String TYPE = "type";

  // TODO: Rename and change types of parameters
  private String type;

  private OnFragmentInteractionListener mListener;

  private AutoLoadMoreRecylerView recyclerView;
  private LinearLayoutManager linearLayoutManager;
  private SwipeRefreshLayout swipeRefreshLayout;
  private NewListAdapter recyclerViewAdapter;
  private New aNew;

  private NewPresenterImp presenterImp;

  public NewFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param type 新闻类型
   * @return A new instance of fragment NewFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static NewFragment newInstance(String type) {
    NewFragment fragment = new NewFragment();
    Bundle args = new Bundle();
    args.putString(TYPE, type);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      type = getArguments().getString(TYPE);
    }
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_new, container, false);
    initView(view);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    setupRefreshLayout();
    setupRecyclerView();

    presenterImp=new NewPresenterImp(type);
    presenterImp.attachView(this);

    /*HttpManager httpManager = HttpManager.getInstance(HttpConstant.BASE_URL);
//    httpManager.getNews(type, new NewResourceSubscriber());
    httpManager.getNewA(type, new Callback<NewEntity>() {
      @Override
      public void onResponse(Call<NewEntity> call, Response<NewEntity> response) {

        NewEntity entity = response.body();
        if (null == entity) {
          LogUtils.e("entity为空");
          return;
        }
        LogUtils.e("entity==" + entity.getReason() + ">>>>>" + entity.getError_code());
        NewEntity.ResultEntity anew = entity.getResult();
        if (null == anew) {
          LogUtils.e("New为空");
          return;
        }
        recyclerViewAdapter = new NewListAdapter(getActivity(),
            anew, new OnItemClickListner() {
          @Override
          public void onItemClick(View view, int postion) {
            LogUtils.e(">>>onItemClick>>>>"+postion);
          }
        });
        //优化性能，设置ture 固定宽高，避免重新计算
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);
      }

      @Override
      public void onFailure(Call<NewEntity> call, Throwable t) {

        LogUtils.e("fail----" + t.toString());
      }
    });*/

    super.onActivityCreated(savedInstanceState);
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


  private void initView(View view) {
    recyclerView = (AutoLoadMoreRecylerView) view.findViewById(R.id.rlview);
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
  }

  private void setupRefreshLayout() {
    swipeRefreshLayout.setOnRefreshListener(this);
    swipeRefreshLayout
        .setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),
            getResources().getColor(android.R.color.holo_green_light),
            getResources().getColor(android.R.color.holo_orange_light),
            getResources().getColor(android.R.color.holo_red_light));
  }

  private void setupRecyclerView() {
    linearLayoutManager = new LinearLayoutManager(getContext());
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    //布局从头部还是底部开始布局显示，默认从头部
    linearLayoutManager.setReverseLayout(false);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setiLoadMoreListener(new ILoadMoreListener() {
      @Override
      public void onLoadMore() {
        LogUtils.e(">>>>>>>load");

     }
    });
    //分隔符
    /*mRecyclerView.addItemDecoration(new Line(this,Line.VERTICAL_LIST));*/
  }

  @Override
  public void onRefresh() {

  }

  @Override
  public void bindData(ResultEntity aNew) {
    if (aNew==null){
      return;
    }
    recyclerViewAdapter = new NewListAdapter(getActivity(),
        aNew, new OnItemClickListner() {
      @Override
      public void onItemClick(View view, int postion) {
        LogUtils.e(">>>onItemClick>>>>"+postion);
      }
    });
    //优化性能，设置ture 固定宽高，避免重新计算
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(recyclerViewAdapter);
  }

  /*private class NewResourceSubscriber extends ResourceSubscriber<New> {

    @Override
    public void onNext(New aNew) {
      LogUtils.e("onNext===" + aNew.toString());
     *//* recyclerViewAdapter.setaNew(aNew);
      recyclerView.postInvalidateOnAnimation();*//*
    }

    @Override
    public void onError(Throwable t) {

      LogUtils.e("onError===" + t.toString());
    }

    @Override
    public void onComplete() {
      LogUtils.e("onComplete===");
    }

    @Override
    protected void onStart() {
      LogUtils.e("onStart===");
      super.onStart();
    }

  }*/
}
