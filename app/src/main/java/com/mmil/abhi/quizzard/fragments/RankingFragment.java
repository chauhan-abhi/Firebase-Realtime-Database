package com.mmil.abhi.quizzard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmil.abhi.quizzard.R;

public class RankingFragment extends Fragment {

  public RankingFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_ranking, container, false);
  }

  public static RankingFragment newInstance() {
    RankingFragment fragment = new RankingFragment();
    return fragment;
  }

}
