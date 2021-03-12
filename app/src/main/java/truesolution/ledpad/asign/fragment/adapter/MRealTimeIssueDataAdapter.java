package truesolution.ledpad.asign.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.fragment.str.STR_ISSUE;

public class MRealTimeIssueDataAdapter extends RecyclerView.Adapter<MRealTimeIssueDataAdapter.MainHolder> {

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    MainHolder mainHolder;

    private List<STR_ISSUE> mIssueList = new ArrayList<>();
    public int test;

    // 생성자
    public MRealTimeIssueDataAdapter(List<STR_ISSUE> mIssueList) {
        this.mIssueList = mIssueList;
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        public TextView mIssueRank, mIssueTitle, mIssueLink, mIssueRankChange;

        public MainHolder(View view) {
            super(view);
            this.mIssueRank = view.findViewById(R.id.mIssueRank);
            this.mIssueTitle = view.findViewById(R.id.mIssueTitle);
            this.mIssueLink = view.findViewById(R.id.mIssueLink);
            this.mIssueRankChange = view.findViewById(R.id.mIssueRankChange);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_data_holder_view, parent, false);
        mainHolder = new MainHolder(holderView);
        return mainHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        mainHolder.mIssueRank.setText(this.mIssueList.get(i).mRank);
        mainHolder.mIssueTitle.setText(this.mIssueList.get(i).mIssueTitle);
        mainHolder.mIssueLink.setText("링크 : " + this.mIssueList.get(i).mUrl);
        mainHolder.mIssueRankChange.setText("순위 변동 : " + this.mIssueList.get(i).mRankChange);
    }

    @Override
    public int getItemCount() {
        return mIssueList.size();
    }
}