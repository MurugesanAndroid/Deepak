package mis.tvscs.com.deepak.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mis.tvscs.com.deepak.R;
import mis.tvscs.com.deepak.model.Model;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Model model;
    private Context mContext;


    public RecyclerAdapter(Context mContext, Model model) {
        this.mContext = mContext;
        this.model = model;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_name.setText(model.getResults().get(position).getName().getFirst());
        holder.tv_age.setText(model.getResults().get(position).getDob().getAge());
        holder.tv_mobile.setText(model.getResults().get(position).getCell());
        Picasso.with(mContext).load(model.getResults().get(position).getPicture().getMedium()).resize(100, 100).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return model.getResults().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name, tv_age, tv_mobile;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            tv_name = view.findViewById(R.id.tv_name);
            tv_age = view.findViewById(R.id.tv_age);
            tv_mobile = view.findViewById(R.id.tv_mobile);
        }
    }
}