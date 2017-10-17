package jirapinya58070014.kmitl.com.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jirapinya58070014.kmitl.com.mylazyinstagram.R;
import jirapinya58070014.kmitl.com.mylazyinstagram.model.PostModel;


class Holder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView like;
    public TextView comment;
    public View frame;

    public Holder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);  //เอา image ผูก holder
        like = (TextView) itemView.findViewById(R.id.like);
        comment = (TextView) itemView.findViewById(R.id.comment);
        frame = itemView.findViewById(R.id.frame);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder> {
    private Context context;   //Adapter ของ Holder
    private List<PostModel> data;
    private boolean gridLayout;

    public PostAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<PostModel> data, boolean gridLayout) {
        this.data = data;
        this.gridLayout = gridLayout;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (gridLayout) {
            View itemView = inflater.inflate(R.layout.post_item, null, false);
            Holder holder = new Holder(itemView);
            return holder;
        } else {
            View itemView = inflater.inflate(R.layout.post_item2, null, false);
            Holder holder = new Holder(itemView);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        //image
        ImageView image = holder.image;
        String imageUrl = data.get(position).getUrl();
        Glide.with(context).load(imageUrl).into(image); //ยัดรูปลง Holder

        //like
        TextView like = holder.like;
        int numLike = data.get(position).getLike();
        like.setText(Integer.toString(numLike));

        //comment
        TextView comment = holder.comment;
        int numComment = data.get(position).getComment();
        comment.setText(Integer.toString(numComment));

        //frame
        View frame = holder.frame;


    }

    //บอกว่าเรามีข้อมูลเท่าไหร่
    @Override
    public int getItemCount() {
        return data.size(); //แสดงข้อมูลตามจำนวนใน list
    }

}

