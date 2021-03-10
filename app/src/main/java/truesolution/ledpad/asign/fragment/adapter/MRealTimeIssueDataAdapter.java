package truesolution.ledpad.asign.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.fragment.str.STR_ISSUE;

public class MRealTimeIssueDataAdapter extends RecyclerView.Adapter<MRealTimeIssueDataAdapter.MainHolder> {
    private String[] mIssueRank, mIssueTitle, mIssueLink, mIssueRankChange;
    MainHolder mainHolder;

    // 생성자
    public MRealTimeIssueDataAdapter(String[] mIssueRank, String[] mIssueTitle, String[] mIssueLink, String[] mIssueRankChange) {
        this.mIssueRank = mIssueRank;
        this.mIssueTitle = mIssueTitle;
        this.mIssueLink = mIssueLink;
        this.mIssueRankChange = mIssueRankChange;
    }

    public static class MainHolder extends RecyclerView.ViewHolder {
        public TextView mIssueRank, mIssueTitle, mIssueLink, mIssueRankChange;

        public MainHolder(View view) {
            super(view);
            this.mIssueRank = view.findViewById(R.id.mIssueRank);
            this.mIssueTitle = view.findViewById(R.id.mIssueTitle);
            this.mIssueLink = view.findViewById(R.id.mIssueLink);
            this.mIssueRankChange = view.findViewById(R.id.mIssueRankChange);
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
        mainHolder.mIssueRank.setText("검색 순위 : " + this.mIssueRank[i]);
        mainHolder.mIssueTitle.setText("제목 : " + this.mIssueTitle[i]);
        mainHolder.mIssueLink.setText("링크 : " + this.mIssueLink[i]);
        mainHolder.mIssueRankChange.setText("순위 변동 : " + this.mIssueRankChange[i]);
    }

    @Override

    public int getItemCount() {
        return mIssueRank.length;
    }
}