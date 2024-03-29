package com.example.bloodbankmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbankmanagement.R;
import com.example.bloodbankmanagement.viewmodels.CustomUserData;

import java.util.List;

public class BloodRequestAdapter extends RecyclerView.Adapter<BloodRequestAdapter.PostHolder> {


    private List<CustomUserData> postLists;
    Context mContext;

    public class PostHolder extends RecyclerView.ViewHolder
    {
        TextView name,division,posted_date,blood_amount,contact,posted_time,hospital,message,blood_group;
        ImageButton img_btn_call;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.reqstUser);
            division = itemView.findViewById(R.id.division);
            posted_date = itemView.findViewById(R.id.posted_date);
            blood_amount = itemView.findViewById(R.id.blood_amount);
            contact = itemView.findViewById(R.id.targetCN);
            posted_time = itemView.findViewById(R.id.posted_time);
            hospital = itemView.findViewById(R.id.hospital);
            message = itemView.findViewById(R.id.msg);
            blood_group = itemView.findViewById(R.id.targetBG);
            img_btn_call = itemView.findViewById(R.id.image_btn_call);


        }
    }

    public BloodRequestAdapter(List<CustomUserData> postLists,Context mContext)
    {
        this.mContext = mContext;
        this.postLists = postLists;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View listitem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.request_list_item, viewGroup, false);

        return new PostHolder(listitem);
    }

    @Override
    public void onBindViewHolder(PostHolder postHolder, int i) {

        /*if(i%2==0)
        {
            postHolder.itemView.setBackgroundColor(Color.parseColor("#C13F31"));
        }
        else
        {
            postHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }*/

        CustomUserData customUserData = postLists.get(i);
        postHolder.name.setText(customUserData.getName());
        postHolder.division.setText(customUserData.getDivision());
        postHolder.posted_date.setText(customUserData.getDate());
        postHolder.blood_amount.setText(customUserData.getAmount());
        postHolder.contact.setText(customUserData.getContact());
        postHolder.posted_time.setText("Status Time : "+customUserData.getTime());
        postHolder.hospital.setText(customUserData.getHospitalName());
        postHolder.message.setText(customUserData.getMessage());
        postHolder.blood_group.setText(customUserData.getBloodGroup());


        postHolder.img_btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = customUserData.getContact();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phone));
                mContext.startActivity(callIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postLists.size();
    }
}
