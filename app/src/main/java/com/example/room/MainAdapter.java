package com.example.room;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
        List<UserInfo>list;
       private Activity context;
       private UserDataBase dataBase;

    public MainAdapter(List<UserInfo> list, Activity context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_text,parent,false);

        return new ViewHolder(view);
    }
    void addList(List<UserInfo> newList){
        this.list=newList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
UserInfo list_info = list.get(position);
dataBase = UserDataBase.getInstance(context);
    holder.textViewPrint.setText(list_info.getName());
    holder.btEdit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            UserInfo info = list.get(holder.getAdapterPosition());

            int id=  info.getID();
            String text = info.getName();

            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_update);
          int width = WindowManager.LayoutParams.MATCH_PARENT;
          int height = WindowManager.LayoutParams.MATCH_PARENT;
          dialog.getWindow().setLayout(width,height);
          dialog.show();
            EditText editTextUp = dialog.findViewById(R.id.edit_name);
            Button btUpdate=  dialog.findViewById(R.id.btUpdate);
            editTextUp.setText(text);
            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uName = editTextUp.getText().toString();
                      list.clear();
                //    dataBase.userDao().update(sID,uName);
                  dataBase.userDao().update(id,uName);
                   MainActivity.
                    list.addAll(dataBase.userDao().getAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    
                }
            });
        }
    });
    holder.btDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            UserInfo info = list.get(holder.getAdapterPosition());
            dataBase.userDao().delete(info);
            int position = holder.getAdapterPosition();
            list.remove(position);
            notifyDataSetChanged();
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
      TextView textViewPrint;
      ImageView btEdit , btDelete ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPrint = itemView.findViewById(R.id.txName);
            btEdit = itemView.findViewById(R.id.listEdit);
            btDelete = itemView.findViewById(R.id.listDelete);
        }
    }
}
