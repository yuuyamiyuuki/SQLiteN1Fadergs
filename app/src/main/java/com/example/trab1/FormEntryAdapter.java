package com.example.trab1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class FormEntryAdapter extends RecyclerView.Adapter<FormEntryAdapter.ViewHolder> {

    public FormEntryAdapter(List<FormModel> entries, Context context) {
        this.entries = entries;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView formName;
            public TextView formNumber;
            public TextView formEmployed;
            public Button btnDelete;


            public ViewHolder(View itemView) {

                super(itemView);

                formName = (TextView) itemView.findViewById(R.id.formName);
                formNumber = (TextView) itemView.findViewById(R.id.formNumber);
                formEmployed = (TextView) itemView.findViewById(R.id.formEmployed);
                btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            }
        }

    private List<FormModel> entries;
    private Context context;

    @Override
    public FormEntryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_entry, viewGroup, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(FormEntryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.formName.setText(entries.get(position).getName());
        holder.formNumber.setText(entries.get(position).getNumber());
        holder.formEmployed.setText(holder.formEmployed.getText() + ":" + (entries.get(position).isEmployed() ? "Y" : "N"));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DAO dao = new DAO(context);
                    dao.deleteOne((entries.get(position).getId()));
                    entries.remove((entries.get(position)));
                    notifyItemRemoved(position);
                    Toast.makeText(context, "Deleted with success.", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(context, "Failure to delete.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

}
