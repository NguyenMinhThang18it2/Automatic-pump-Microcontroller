package thang.com.maybomnuoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDanhSachBom extends RecyclerView.Adapter<AdapterDanhSachBom.ViewHolder> {
    private ArrayList<danhsachmaybom> dsbom;
    private Context context;

    public AdapterDanhSachBom(ArrayList<danhsachmaybom> dsbom, Context context) {
        this.dsbom = dsbom;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDanhSachBom.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View iTemView = layoutInflater.inflate(R.layout.item_bom, parent, false);

        return new ViewHolder(iTemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDanhSachBom.ViewHolder holder, int position) {
        holder.txtThoigianbom.setText(dsbom.get(position).getTime());
        holder.txtSolanbomtrongngay.setText(dsbom.get(position).getSolanbom());
    }

    @Override
    public int getItemCount() {
        return dsbom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtThoigianbom, txtSolanbomtrongngay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtThoigianbom = (TextView) itemView.findViewById(R.id.txtThoigianbom);
            txtSolanbomtrongngay = (TextView) itemView.findViewById(R.id.txtSolanbomtrongngay);
        }
    }
}
