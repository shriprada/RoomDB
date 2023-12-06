package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdbexample.Details;
import com.example.roomdbexample.R;
import com.example.roomdbexample.UpdateDetails;

import java.util.List;

import db.EntityFile;

public class RegAdapter extends RecyclerView.Adapter<RegAdapter.MyViewHolder> {
    private Context context;
    private List<EntityFile> detailentity;

    public RegAdapter(Context context,List<EntityFile> detailentity){
        this.context = context;
        this.detailentity=detailentity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listadapter,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            EntityFile entityFile = detailentity.get(position);
            holder.tname.setText(String.format("Name: %s", entityFile.getName()));
            holder.taddress.setText("Address: " + entityFile.getAddress());
            holder.temail.setText("Email: " + entityFile.getEmail());
            holder.tphoneno.setText("Phone no: " + entityFile.getPhonenumber());
            holder.tusername.setText("User name:" + entityFile.getUsername());
            holder.tpassword.setText("Password: "+entityFile.getPassword());
    }

    @Override
    public int getItemCount() { return detailentity.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tname, taddress,temail,tusername,tphoneno,tpassword;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.rvName);
            taddress =itemView.findViewById(R.id.rvAddress);
            temail = itemView.findViewById(R.id.rvEmail);
            tusername = itemView.findViewById(R.id.rvUsername);
            tphoneno =itemView.findViewById(R.id.rvphoneNumber);
            tpassword = itemView.findViewById(R.id.rvPassword);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EntityFile entityFile = detailentity.get(getAdapterPosition());
                    Intent intent = new Intent(context, UpdateDetails.class);
                    intent.putExtra("Details", entityFile);
                    context.startActivity(intent);
                }
            });
        }
    }
}
