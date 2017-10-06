package jirapinya58070014.kmitl.com.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jirapinya58070014.kmitl.com.mylazyinstagram.R;


class Holder extends RecyclerView.ViewHolder{

    public ImageView image;

    public Holder(View itemView){
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);  //เอา image ผูก holder
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder>{
    private Context context;   //Adapter ของ Holder

    //ข้อมูล
    String[] data = {
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n1.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n2.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n3.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n4.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n5.jpg"

    };


    public PostAdapter(Context context) {
        this.context=context;

    }


    //Holder item มาจากไหนเอา itemView ยัดใส่
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());
        View itemView =
                inflater.inflate(R.layout.post_item, null, false);

        Holder holder = new Holder(itemView); //วิธีนี้จะใช้ memory เท่าที่แสดงบนหน้าจอ ประหยัด!!!
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data[position]).into(image); //ยัดรูปลง Holder

    }

    //บอกว่าเรามีข้อมูลเท่าไหร่
    @Override
    public int getItemCount() {
        return data.length; //แสดงข้อมูลตามจำนวนใน list
    }

}
