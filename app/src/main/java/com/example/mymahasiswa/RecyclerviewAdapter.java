package com.example.mymahasiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mymahasiswa.Model.Mahasiswa;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {

    Context context;
    OnUserClickListener listener;
    List<Mahasiswa> listMahasiswaInfo;

    public RecyclerviewAdapter(Context context, List<Mahasiswa> listMahasiswaInfo, OnUserClickListener listener) {
        this.context = context;
        this.listMahasiswaInfo = listMahasiswaInfo;
        this.listener = listener;
    }

    public interface OnUserClickListener {
        void onUserClick(Mahasiswa currentPerson, String action);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_data_mahasiswa, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final Mahasiswa currentMahasiswa = listMahasiswaInfo.get(position);
        holder.ctxtName.setText(currentMahasiswa.getNama());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentMahasiswa, "Edit");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMahasiswaInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView ctxtName;
        ImageView imgEdit;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtName = itemView.findViewById(R.id.nama);
        }
    }
}
