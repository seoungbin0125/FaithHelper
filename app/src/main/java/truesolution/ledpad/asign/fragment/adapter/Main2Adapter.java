package truesolution.ledpad.asign.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import truesolution.ledpad.asign.R;

public class Main2Adapter extends RecyclerView.Adapter<Main2Adapter.MainHolder> {
    private String[] main_text, main_text2;
    MainHolder mainHolder;

    // 생성자
    public Main2Adapter(String[] main_text, String[] main_text2) {
        this.main_text = main_text;
        this.main_text2 = main_text2;
    }


    public static class MainHolder extends RecyclerView.ViewHolder {
        public TextView main_text, main_text2;

        public MainHolder(View view) {
            super(view);
            this.main_text = view.findViewById(R.id.text1);
            this.main_text2 = view.findViewById(R.id.text2);
        }
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_holder_view, parent, false);
        mainHolder = new MainHolder(holderView);
        return mainHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        mainHolder.main_text.setText(this.main_text[i]);
        mainHolder.main_text2.setText(this.main_text2[i]);

    }

    @Override

    public int getItemCount() {

        return main_text.length;

    }
}