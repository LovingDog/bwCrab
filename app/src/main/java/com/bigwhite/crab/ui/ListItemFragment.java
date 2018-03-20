package com.bigwhite.crab.ui;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.ui.dummy.DummyContent;
import com.bigwhite.crab.ui.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ListItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListItemFragment newInstance(int columnCount) {
        ListItemFragment fragment = new ListItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ShowListAdapter());
        }
        return view;
    }

    class ShowListAdapter extends RecyclerView.Adapter<ShowListAdapter.ViewHolder> {

        public ShowListAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_crab_detail, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ShowListAdapter.ViewHolder holder, int position) {
            holder.mPreview.setImageResource(R.drawable.crab1);
            holder.mDescription.setText(R.string.product_description);
            holder.mPrice.setText(R.string.product_price);
            holder.mPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
            holder.mIntegral.setText(R.string.product_integral);
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mPreview;
            TextView mDescription;
            TextView mPrice;
            TextView mIntegral;

            public ViewHolder(View view) {
                super(view);
                mPreview = (ImageView) view.findViewById(R.id.product_preview);
                mDescription = (TextView) view.findViewById(R.id.product_description);
                mPrice = (TextView) view.findViewById(R.id.product_price);
                mIntegral = (TextView) view.findViewById(R.id.product_integral);
            }
        }
    }

}
