package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isaac.flickrcopy.R;
import java.util.ArrayList;
import model.CategoryRow;

/**
 * Created by isaac on 8/29/17.
 */

public class CategoryAdapter extends ArrayAdapter{

    public CategoryAdapter(@NonNull Context context, ArrayList<CategoryRow> categories) {
        super(context, R.layout.category_row, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.category_row, parent, false);
        CategoryRow category = (CategoryRow) getItem(position);

        TextView tvCategory = (TextView) customView.findViewById(R.id.categoryText);
        tvCategory.setText(category.getCategoryTag());

        ImageView imIcon = (ImageView) customView.findViewById(R.id.categoryIcon);
        imIcon.setImageDrawable(category.getImage());
        return customView;
    }
}
