package br.ufrpe.revcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder>{
    private static final String TAG = "UsuarioAdapter";

    private ArrayList<String> listTextViewnomes = new ArrayList<>();
    private ArrayList<String> listTextViewDescricao = new ArrayList<>();
    private Context mContext;

    public UsuarioAdapter(RecyclerViewUsuario listTextViewnomes, ArrayList<String> listTextViewDescricao, ArrayList<String> mContext) {
        this.listTextViewnomes = listTextViewnomes;
        this.listTextViewDescricao = listTextViewDescricao;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servico_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.textViewNome.setText(listTextViewnomes.get(position));
    holder.textViewDescricao.setText(listTextViewDescricao.get(position));
    }

    @Override
    public int getItemCount() {
        return listTextViewnomes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNome;
        TextView textViewDescricao;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.nome);
            textViewDescricao = itemView.findViewById(R.id.descricao);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
