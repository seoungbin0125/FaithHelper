package truesolution.ledpad.asign.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.MainActivity;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.app.ListData;
import truesolution.ledpad.asign.app.MAPP;
import truesolution.ledpad.asign.fragment.adapter.MRealTimeIssueDataAdapter;
import truesolution.ledpad.asign.fragment.str.STR_ISSUE;
import truesolution.ledpad.asign.fragment.str.STR_Text;

/**
 * Created by TCH on 2020/07/07
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/07
 */
public class MFragmentDraw extends Fragment {

	private MainActivity mActivity;
	private View mView;
	Document document;


	RecyclerView recyclerView;
	RecyclerView.LayoutManager layoutManager;
	RecyclerView.Adapter adapter;

	ArrayList<STR_ISSUE> mList = new ArrayList<>();

	int mRenewCount = 0;
	Timer mTimer = new Timer();

	/**
	 * Instantiates a new M fragment draw.
	 *
	 * @param _activity the activity
	 */
	public MFragmentDraw(Activity _activity) {
		mActivity = (MainActivity) _activity;
	}

	public MFragmentDraw(Activity _activity, boolean logoSend) {
		mActivity = (MainActivity) _activity;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_draw, container, false);
		}
		recyclerView = mView.findViewById(R.id.recyclerView);
		layoutManager = new LinearLayoutManager(mActivity);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new MRealTimeIssueDataAdapter(mList);
		recyclerView.setAdapter(adapter);

//		new IssueData().execute();
		timer_crawling_issueData();
		return mView;
	}

	@Override
	public void onDestroy() {
		mTimer.cancel();
		super.onDestroy();
	}

	private class IssueData extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... voids) {


			try {
				/* Jsoup을 이용해 데이터 가져오기 */
				MDEBUG.debug("Jsoup을 이용해 데이터 가져오기 !!!");

				Document document = Jsoup.connect("https://www.nate.com/?f=auto_news").get();
				Elements doc = document.select("#olLiveIssueKeyword li");

				MDEBUG.debug("doc.size() : " + doc.size());
				mRenewCount++;
				if (mRenewCount >= 2) {
					mList.clear();
					mRenewCount = 0;
				}
				for(int i=0; i<doc.size(); i++) {
					STR_ISSUE issue_data = new STR_ISSUE();
					issue_data.mRank =  doc.get(i).select("li").get(0).select(".num_rank").text();
					issue_data.mUrl = doc.get(i).select("li").get(0).absUrl("href");
					issue_data.mIssueTitle = doc.get(i).select("li").get(0).select(".txt_rank").text();
					issue_data.mRankChange = doc.get(i).select("li").get(0).select(".nHide").text();
					MDEBUG.debug("rank : " + issue_data.mRank);
					MDEBUG.debug("url : " + issue_data.mUrl);
					MDEBUG.debug("issueText : " + issue_data.mIssueTitle);
					MDEBUG.debug("rankChange : " + issue_data.mRankChange);
					mList.add(issue_data);
				}

			} catch (Exception e) {
				MDEBUG.debug("접속 실패 error !!!");
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			MDEBUG.debug("recyclerView.getChildCount()" + recyclerView.getChildCount());
			adapter.notifyDataSetChanged();
		}
	}

	public void timer_crawling_issueData () {
		mTimer.schedule(addTask, 0, 5000);
	}

	TimerTask addTask = new TimerTask() {
		@Override
		public void run() {
			new IssueData().execute();
		}
	};
}