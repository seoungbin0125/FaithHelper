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

	ArrayList<STR_ISSUE> _list = new ArrayList<>();

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


//		new IssueData().execute();
		timer_crawling_issueData();
		return mView;
	}

	private class IssueData extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... voids) {


			try {
				/* Jsoup을 이용해 데이터 가져오기 */
				MDEBUG.debug("Jsoup을 이용해 데이터 가져오기 !!!");

				Document document = Jsoup.connect("https://www.nate.com/?f=auto_news").get();
				Elements doc = document.select("#olLiveIssueKeyword li");
				String url2;


//				text = doc.select("h4").text();
				MDEBUG.debug("doc.size() : " + doc.size());

				for(int i=0; i<doc.size(); i++) {
					STR_ISSUE issue_data = new STR_ISSUE();

//					rank[i] = doc.get(i).select("li").get(0).select(".num_rank").text();
					issue_data.mRank =  doc.get(i).select("li").get(0).select(".num_rank").text();
//					url[i] = doc.get(i).select("li").get(0).select(".num_rank").get(0).absUrl("href");
//					url2 = doc.get(i).select("li").get(0).absUrl("href");
					issue_data.mUrl = doc.get(i).select("li").get(0).absUrl("href");

//					url = doc.get(i).select("li").get(0).select("a").get(0).absUrl("href");
//					totalUrl = doc.get(i).select("li").get(0).select("a").text();

//					issueText = doc.get(i).select("li").get(0).select(".txt_rank").text();
					issue_data.mIssueTitle = doc.get(i).select("li").get(0).select(".txt_rank").text();
//					rankChange = doc.get(i).select("li").get(0).select(".nHide").text();

					issue_data.mRankChange = doc.get(i).select("li").get(0).select(".nHide").text();

					MDEBUG.debug("rank : " + issue_data.mRank);
					MDEBUG.debug("url : " + issue_data.mUrl);
					MDEBUG.debug("issueText : " + issue_data.mIssueTitle);
					MDEBUG.debug("rankChange : " + issue_data.mRankChange);
					_list.add(issue_data);
				}

			} catch (Exception e) {
				MDEBUG.debug("접속 실패 error !!!");
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			String[] rank = new String[_list.size()];
			String[] url= new String[_list.size()];
			String[] issueTitle = new String[_list.size()];
			String[] rankChange = new String[_list.size()];

			for (int i = 0; i < _list.size(); i++) {
				rank[i] = _list.get(i).mRank;
				url[i] = _list.get(i).mUrl;
				issueTitle[i] = _list.get(i).mIssueTitle;
				rankChange[i] = _list.get(i).mRankChange;

				MDEBUG.debug("rank[i] : " + rank[i]);
				MDEBUG.debug("IssueTitle[i] : " + issueTitle[i]);
			}
			adapter = new MRealTimeIssueDataAdapter(rank, issueTitle, url, rankChange);
			adapter.notifyDataSetChanged();
//			if(	recyclerView.getChildCount() >= 10) {
//				recyclerView.removeAllViewsInLayout();
//				adapter.notifyDataSetChanged();
//				MDEBUG.debug("@@@@@@@@@@@@@@recyclerView.getChildCount()" + recyclerView.getChildCount());
//				recyclerView.notifyItem
//			}
			MDEBUG.debug("recyclerView.getChildCount()" + recyclerView.getChildCount());
			recyclerView.setAdapter(adapter);
			recyclerView.addItemDecoration(new DividerItemDecoration(mView.getContext(), 1));
		}
	}

	public void timer_crawling_issueData () {
		Timer timer = new Timer();
		timer.schedule(addTask, 0, 5000);
	}

	TimerTask addTask = new TimerTask() {
		@Override
		public void run() {
			new IssueData().execute();
		}
	};
}