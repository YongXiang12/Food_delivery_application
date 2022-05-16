package fcu.mp110.food_delivery_app.ui.review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.restaurant.DishesCustomizationItem;

public class ReviewAdapter extends ArrayAdapter<ReviewItem> {

    private Context context;
    private List<ReviewItem> reviewItemList;

    public ReviewAdapter(@NonNull Context context, int resource, List<ReviewItem> reviewItemList) {
        super(context, resource, reviewItemList);
        this.context = context;
        this.reviewItemList = reviewItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        RelativeLayout itemLayout = null;

        if (convertView == null) {
            itemLayout = (RelativeLayout) inflater.inflate(R.layout.review_listitem, null);
        } else {
            itemLayout = (RelativeLayout) convertView;
        }

        ReviewItem item = reviewItemList.get(position);
        ImageView ivDescribe = itemLayout.findViewById(R.id.imv_user_icon);
        ivDescribe.setImageResource(item.getImgResId());
        TextView tvUserName = itemLayout.findViewById(R.id.txv_user_name);
        tvUserName.setText(item.getUserName());
        TextView tvReview = itemLayout.findViewById(R.id.txv_user_review);
        tvReview.setText(item.getReview());
        TextView tvDate = itemLayout.findViewById(R.id.txv_date);
        tvDate.setText(item.getDate());
        return itemLayout;
    }
}
