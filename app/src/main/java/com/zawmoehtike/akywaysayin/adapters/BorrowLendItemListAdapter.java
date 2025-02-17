package com.zawmoehtike.akywaysayin.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zawmoehtike.akywaysayin.R;
import com.zawmoehtike.akywaysayin.data.entity.BorrowLendItem;

import java.util.List;

import me.myatminsoe.mdetect.MDetect;

public class BorrowLendItemListAdapter extends RecyclerView.Adapter<BorrowLendItemListAdapter.MyViewHolder> {

    private Context mContext;
    private List<BorrowLendItem> borrowLendItemList;

    private DeleteClickListener deleteClickListener;
    private EditClickListener editClickListener;

    public BorrowLendItemListAdapter(Context mContext, List<BorrowLendItem> borrowLendItemList, DeleteClickListener deleteClickListener, EditClickListener editClickListener) {
        this.mContext = mContext;
        this.borrowLendItemList = borrowLendItemList;

        this.deleteClickListener = deleteClickListener;
        this.editClickListener = editClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.borrow_lend_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BorrowLendItem borrowLendItem = borrowLendItemList.get(position);
        holder.bindView(borrowLendItem);
    }

    @Override
    public int getItemCount() {
        return this.borrowLendItemList.size();
    }

    public void addItems(List<BorrowLendItem> borrowLendItemList) {
        this.borrowLendItemList = borrowLendItemList;
        notifyDataSetChanged();
    }

    public interface EditClickListener {
        void onEditClickItem(BorrowLendItem borrowLendItem);
    }

    public interface DeleteClickListener {
        void onDeleteClickItem(BorrowLendItem borrowLendItem);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvItemName, tvPersonName, tvDate;
        private Button btnDelete;
        private Button btnEdit;
        private BorrowLendItem borrowLendItem;

        public MyViewHolder(View view) {
            super(view);

            tvItemName = view.findViewById(R.id.tvItemName);
            tvPersonName = view.findViewById(R.id.tvPersonName);
            tvDate = view.findViewById(R.id.tvDate);

            btnDelete = view.findViewById(R.id.btnDelete);
            btnEdit = view.findViewById(R.id.btnEdit);

            btnDelete.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
        }

        public void bindView(BorrowLendItem borrowLendItem){

            this.borrowLendItem = borrowLendItem;

            tvItemName.setText(MDetect.INSTANCE.getText(borrowLendItem.getItemName()));
            tvPersonName.setText(MDetect.INSTANCE.getText(borrowLendItem.getPersonName()));
            tvDate.setText(MDetect.INSTANCE.getText(borrowLendItem.getDate()));
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.btnDelete :
                    if (deleteClickListener != null) deleteClickListener.onDeleteClickItem(borrowLendItem);
                    break;

                case R.id.btnEdit:
                    editClickListener.onEditClickItem(borrowLendItem);
                    break;
            }
        }
    }
}
