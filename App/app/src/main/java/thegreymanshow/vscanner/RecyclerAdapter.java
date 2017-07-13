package thegreymanshow.vscanner;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by akshay dangare on 4/6/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;
    ArrayList<Order> arrayList = new ArrayList<>();
    ArrayList<Order> checkedList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Order> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_HEAD)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_row,parent,false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,viewType);
            return recyclerViewHolder;
        }
        else if(viewType == TYPE_LIST){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,viewType);
            return recyclerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        if(holder.viewType == TYPE_LIST){
            Order order = arrayList.get(position-1);
            holder.name.setText(order.getName());
            holder.quant.setText(Integer.toString(order.getQuant()));
            holder.cost.setText(String.valueOf(order.getCost()));

            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, int pos) {
                    RadioButton chk = (RadioButton)v;

                    if(chk.isChecked()){
                        checkedList.add(arrayList.get(pos-1));
                    }
                    else if(!chk.isChecked()){
                        checkedList.remove(arrayList.get(pos-1));
                    }
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,quant,cost;
        int viewType;
        RadioButton chk;
        ItemClickListener itemClickListener;

        public RecyclerViewHolder(View view,int viewType) {
            super(view);

            if(viewType == TYPE_LIST) {
                chk = (RadioButton)view.findViewById(R.id.checkbox);
                name = (TextView) view.findViewById(R.id.name);
                quant = (TextView) view.findViewById(R.id.quant);
                cost = (TextView) view.findViewById(R.id.cost);

                chk.setOnClickListener(this);
                this.viewType = viewType;
            }
            else if(viewType == TYPE_HEAD){
                this.viewType = viewType;
            }
        }

        public void setItemClickListener(ItemClickListener icl){
            this.itemClickListener = icl;
        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }



    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEAD;
            return TYPE_LIST;
    }
}

