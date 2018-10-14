package com.mmil.abhi.quizzard.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mmil.abhi.quizzard.R;
import com.mmil.abhi.quizzard.interfaces.OnItemListner;
import org.w3c.dom.Text;

/**
 * Created by abhi on 8/4/18.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  public ImageView categoryImageView;
  public TextView categoryName;

  private OnItemListner onItemListner;

  public CategoryViewHolder(View itemView) {
    super(itemView);
    categoryImageView = itemView.findViewById(R.id.category_image);
    categoryName = itemView.findViewById(R.id.category_name);
    itemView.setOnClickListener(this);
  }

  public void setOnItemListner(OnItemListner onItemListner) {
    this.onItemListner = onItemListner;
  }

  @Override public void onClick(View view) {
    onItemListner.onClick(view, getAdapterPosition(), false);
  }
}
