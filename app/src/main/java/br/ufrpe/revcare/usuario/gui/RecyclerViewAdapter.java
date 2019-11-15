package br.ufrpe.revcare.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufrpe.revcare.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private ArrayList<String> mNome = new ArrayList<>();
    private ArrayList<String> mEndereco = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    private ArrayList<String> mTelefone = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<Integer> mLikes = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> nome, ArrayList<String> endereco, ArrayList<String> telefone, ArrayList<String> email, ArrayList<String> descricao, ArrayList<Integer> likes) {
        this.mNome = nome;
        this.mEndereco = endereco;
        this.mTelefone = telefone;
        this.mEmail = email;
        this.mDescricao = descricao;
        this.mContext = context;
        this.mLikes = likes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nome.setText(mNome.get(position));
        holder.descricao.setText(mEndereco.get(position));
        holder.likes.setText(mLikes.get(position).toString());

        holder.btnVerMais.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PerfilProfissional.class);
                intent.putExtra("nome", mNome.get(position));
                intent.putExtra("endereco", mEndereco.get(position));
                intent.putExtra("telefone", mTelefone.get(position));
                intent.putExtra("email", mEmail.get(position));
                intent.putExtra("descricao", mDescricao.get(position));
                mContext.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return mNome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView descricao;
        RelativeLayout parentLayout;
        Button btnVerMais;
        TextView likes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            descricao = itemView.findViewById(R.id.localizacao);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            btnVerMais = itemView.findViewById(R.id.btnVerMais);
            likes = itemView.findViewById(R.id.Avaliacao);
        }
    }
}

