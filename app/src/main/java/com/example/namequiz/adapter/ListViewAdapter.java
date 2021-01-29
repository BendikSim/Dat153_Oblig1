package com.example.namequiz.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namequiz.R;
import com.example.namequiz.model.Person;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<Person> personList;

    public ListViewAdapter(Context context, ArrayList<Person> personList) {
        super(context, R.layout.list_view);
        this.context = context;
        this.personList = personList;
    }

    public void remove(int position) {
        Person p = personList.get(position);
        personList.remove(position);
        notifyDataSetChanged();
        Toast.makeText(context.getApplicationContext(), "You successfully removed " + p.getName(), Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Make variables for use later on
        ViewHolder viewHolder = new ViewHolder();
        Person person = personList.get(position);
        String name = person.getName();

        //Check if convertView equals null
        if (convertView == null) {
            //Inflate the view/layout
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_view, parent, false);

            //connect image and text from view to variable
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.name = (TextView) convertView.findViewById(R.id.editTextName);
            viewHolder.delBtn = (ImageButton) convertView.findViewById(R.id.deleteBtn);

            //set tag for convertView
            convertView.setTag(viewHolder);

            //onClick listener
            viewHolder.delBtn.setOnClickListener(v -> remove(position));
        } else {
            //set holder variable to existing view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //change the text in the name field to the persons name
        viewHolder.name.setText(name);

        //change the empty imageView to the image connected to the person
        if (person.getImage() != null) {
            viewHolder.img.setImageBitmap(person.getImage());
        }

        return convertView;
    }

    static class ViewHolder{
        ImageView img;
        TextView name;
        ImageButton delBtn;
    }

}
