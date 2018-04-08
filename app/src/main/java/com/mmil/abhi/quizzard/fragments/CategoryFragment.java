package com.mmil.abhi.quizzard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mmil.abhi.quizzard.R;
import com.mmil.abhi.quizzard.interfaces.OnItemListner;
import com.mmil.abhi.quizzard.model.Category;
import com.mmil.abhi.quizzard.viewholder.CategoryViewHolder;
import com.squareup.picasso.Picasso;

public class CategoryFragment extends Fragment {

  private RecyclerView listCategory;
  private RecyclerView.LayoutManager layoutManager;
  private FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

  private View myFrag;

  private FirebaseDatabase firebaseDatabase;
  private DatabaseReference categories;

  public CategoryFragment() {
    // Required empty public constructor
  }

  public static CategoryFragment newInstance() {
    return new CategoryFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    firebaseDatabase = FirebaseDatabase.getInstance();
    categories = firebaseDatabase.getReference("Category");
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    myFrag = inflater.inflate(R.layout.fragment_category, container, false);
    listCategory = myFrag.findViewById(R.id.listCategory);
    layoutManager = new LinearLayoutManager(container.getContext());
    listCategory.setLayoutManager(layoutManager);

    loadCategories();
    return myFrag;
  }

  private void loadCategories() {
    adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
        Category.class,
        R.layout.category_layout,
        CategoryViewHolder.class,
        categories
    ) {
      @Override protected void populateViewHolder(CategoryViewHolder viewHolder, final Category model,
          int position) {
        viewHolder.categoryName.setText(model.getName());
        Picasso.get().load(model.getImage()).into(viewHolder.categoryImageView);

        viewHolder.setOnItemListner(new OnItemListner() {
          @Override public void onClick(View view, int position, boolean isLongClick) {
            Toast.makeText(getContext(), String.format("%s|%s",adapter.getRef(position).getKey(),model.getName()), Toast.LENGTH_SHORT).show();
          }
        });

        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
      }
    };
  }
}
