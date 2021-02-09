package com.example.namequiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.namequiz.R;
import com.example.namequiz.database.DAO;
import com.example.namequiz.database.PersonDatabase;
import com.example.namequiz.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Person> {
    private Context context;
    private int resource;
    private PersonDatabase db;
    private List<Person> personList;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Person> personList) {
        super(context, resource, personList);
        this.context = context;
        this.resource = resource;
        this.personList = personList;
        this.db = PersonDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(resource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.image);
        imageView.setImageBitmap(getItem(position).getImage());

        TextView textName = convertView.findViewById(R.id.name);
        textName.setText(getItem(position).getName());

        ImageButton deleteBtn = convertView.findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(x ->
            removePerson(position));
        return convertView;
    }

    public void removePerson(int pos){
        Person person = getItem(pos);
        remove(person);
        db.personDAO().removePerson(person);
        Toast.makeText(context.getApplicationContext(), "Successfully removed " + person.getName(), Toast.LENGTH_SHORT).show();
    }
}
