package br.ufrpe.revcare.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import br.ufrpe.revcare.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private ArrayList<String> mNome = new ArrayList<>();
    private ArrayList<String> mCidade = new ArrayList<>();
    private ArrayList<String> mEstado = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    private ArrayList<String> mTelefone = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<Integer> mLikes = new ArrayList<>();
    private ArrayList<Integer> mDeslikes = new ArrayList<>();
    private ArrayList<Bitmap> mFotos = new ArrayList<>();
    private Float qtdLikes;
    private Float qtdDeslikes;
    private Float porcentagemLikes;
    private Float votosTotais;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> nome, ArrayList<String> cidade, ArrayList<String> telefone, ArrayList<String> email, ArrayList<String> descricao, ArrayList<Integer> likes, ArrayList<Integer> deslikes, ArrayList<String> estado, ArrayList<Bitmap> foto) {
        this.mNome = nome;
        this.mCidade = cidade;
        this.mTelefone = telefone;
        this.mEmail = email;
        this.mDescricao = descricao;
        this.mContext = context;
        this.mLikes = likes;
        this.mDeslikes = deslikes;
        this.mEstado = estado;
        this.mFotos = foto;
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
        qtdLikes = mLikes.get(position).floatValue();
        qtdDeslikes = mDeslikes.get(position).floatValue();
        votosTotais = qtdDeslikes + qtdLikes;
        porcentagemLikes = (qtdLikes * 100)/votosTotais;
        if (qtdLikes == 0){
            holder.likes.setText("Nenhuma");
        }else{
            DecimalFormat df = new DecimalFormat("0");
            holder.likes.setText(df.format(porcentagemLikes).toString() + "% positivas");}
        holder.nome.setText(mNome.get(position));
        holder.cidade.setText(mCidade.get(position));
        holder.estado.setText(mEstado.get((position)));
        if (mFotos.get(position) != null){

            holder.imagem.setImageBitmap(mFotos.get(position));
        }


        holder.btnVerMais.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PerfilProfissional.class);
                intent.putExtra("nome", mNome.get(position));
                intent.putExtra("endereco", mCidade.get(position));
                intent.putExtra("telefone", mTelefone.get(position));
                intent.putExtra("email", mEmail.get(position));
                intent.putExtra("cidade", mDescricao.get(position));
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
        TextView cidade;
        ImageView imagem;
        RelativeLayout parentLayout;
        Button btnVerMais;
        TextView likes;
        TextView estado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            cidade = itemView.findViewById(R.id.localizacao);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            btnVerMais = itemView.findViewById(R.id.btnVerMais);
            likes = itemView.findViewById(R.id.qtdLikes);
            estado = itemView.findViewById(R.id.estado);
            imagem = itemView.findViewById(R.id.image);
        }
    }
}

