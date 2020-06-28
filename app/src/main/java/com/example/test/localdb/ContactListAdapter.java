package com.example.test.localdb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.RecyclerViewClickListener;
import com.example.test.ShowContactActivity;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView contactItemView;

        private ContactViewHolder(View itemView) {
            super(itemView);
            contactItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }


    private static RecyclerViewClickListener itemListener;
    private final LayoutInflater mInflater;
    private List<Contact> mContacts; // Cached copy of words

    public ContactListAdapter(Context context, RecyclerViewClickListener itemListener) {
        mInflater = LayoutInflater.from(context);
        this.itemListener = itemListener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item2, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if (mContacts != null) {
            Contact current = mContacts.get(position);
//            holder.contactItemView.setText(current.getContact());
            holder.contactItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.contactItemView.setText("No Contacts yet");
        }
    }

    public void setContacts(List<Contact> contacts){
        mContacts = contacts;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mContacts != null)
            return mContacts.size();
        else return 0;
    }
}
