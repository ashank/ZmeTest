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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.adapter.MulItemNewAdapter;
import com.funhotel.hmvp.model.entity.NewEntity.ResultEntity;
import com.funhotel.hmvp.model.viewmodel.NewViewModel;
import com.funhotel.hmvp.presenter.NewPresenterImp;
import com.funhotel.hmvp.ui.activity.AdvanceWebActivity;
import com.zme.zlibrary.widget.recycler.listener.OnItemClickListener;
import com.zme.zlibrary.widget.recycler.SuperBaseAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFragment extends Fragment implements OnRefreshListener, NewViewModel,
        OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TYPE = "type";

    // TODO: Rename and change types of parameters
    private String type;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SuperBaseAdapter adapter;
    private ResultEntity aNew;

    private NewPresenterImp presenterImp;
    int lastVisibleItem=0;

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

        if (TextUtils.isEmpty(type)) {
            type = "头条";
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
        super.onActivityCreated(savedInstanceState);
        setupRefreshLayout();
        setupRecyclerView();
        presenterImp = new NewPresenterImp(type);
        presenterImp.attachView(this);
    }

    @Override
    public void onResume() {
        swipeRefreshLayout.setRefreshing(true);
        super.onResume();
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
        recyclerView =  view.findViewById(R.id.rlview);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
    }

    private void setupRefreshLayout() {
        swipeRefreshLayout.setRefreshing(true);
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
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        //优化性能，设置ture 固定宽高，避免重新计算
        recyclerView.setHasFixedSize(true);

        //分隔符
        /*mRecyclerView.addItemDecoration(new Line(this,Line.VERTICAL_LIST));*/
    /*recyclerView.setiLoadMoreListener(new ILoadMoreListener() {
      @Override
      public void onLoadMore() {
        LogUtils.e(">>>>>>>load");
     }
    });*/

    }

    @Override
    public void onRefresh() {
        presenterImp.onStartHttp();
    }





    @Override
    public void bindData(ResultEntity aNew) {
        this.aNew = aNew;
        if (aNew == null) {
            return;
        }
        if (adapter == null) {
            adapter = new MulItemNewAdapter(getActivity(), aNew.getData());
            adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.resetData(aNew.getData());
            adapter.notifyItemRangeChanged(0, aNew.getData().size());
        }
        //显示加载进度条
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), AdvanceWebActivity.class);
        intent.putExtra("URL", aNew.getData().get(postion).getUrl());
        intent.putExtra("TITLE", aNew.getData().get(postion).getTitle());
        startActivity(intent);
    }

    @Override
    public void onStartHttp() {
        swipeRefreshLayout.setRefreshing(true);
    }

}



