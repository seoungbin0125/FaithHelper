package truesolution.ledpad.asign.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.MainActivity;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.fragment.adapter.Main2Adapter;

/**
 * Created by TCH on 2020/07/07
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/07
 */
public class MFragmentText extends Fragment {

	private View mView;
	private MainActivity mActivity;

	Date currentTime = Calendar.getInstance().getTime();
	SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.getDefault());
	SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
	SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

	String weekDay = weekdayFormat.format(currentTime);
	String year = yearFormat.format(currentTime);
	String month = monthFormat.format(currentTime);
	String day = dayFormat.format(currentTime);

	String totalDay = year + month + day;

	RecyclerView recyclerView;
	RecyclerView.LayoutManager layoutManager;

	RecyclerView.Adapter adapter;
	Document document;

	String url ="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=ZvK%2BdJQ7edFGA6is2AVZ28YDx8Q6dWSWTrTKJjALh9U1Yr%2Fk5oNlcHidHMe3dcmKPVPCZBomkqPqirxeJyJ9Ng%3D%3D&pageNo=1&numOfRows=10&startCreateDt=" + totalDay +" &endCreateDt=" +
			totalDay;

	String TodayPatient = "";

	String[] main_text =  {"금일 확진자 : "};
	String[] main_text2 =  {"test"};

	public MFragmentText() {
//		mActivity = _activity;
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		Object obj;
		if(getActivity() != null && getActivity() instanceof MainActivity) {
			obj = ((MainActivity)getActivity()).getData();
			this.mActivity = (MainActivity)obj;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_api_info, container, false);
		}
		recyclerView = mView.findViewById(R.id.recyclerView);
		layoutManager = new LinearLayoutManager(mActivity);
		recyclerView.setLayoutManager(layoutManager);

		MDEBUG.debug("webnautes" + year + "년 " + month + "월 " + day + "일 " + weekDay + "요일");
		MDEBUG.debug("날짜 : " + totalDay);
		mActivity.print("test ok");
		new JsoupAsyncTask().execute();

		return mView;

	}

	public class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void aVoid) {

			try {
				Elements fields = document.select("item");
				for(int i=1; i<fields.size(); i++) {
					MDEBUG.debug("i : " + i + fields.get(i).select("gubun").text());
					if (fields.get(i).select("gubun").text().equals("합계")) {
						TodayPatient = fields.get(i).select("incDec").text();
						MDEBUG.debug("TodayPatient : " + TodayPatient);
						main_text2[0] = TodayPatient;

					}
				}
			} catch (Exception e) {
			}
			adapter = new Main2Adapter(main_text, main_text2);
			recyclerView.setAdapter(adapter);
			recyclerView.addItemDecoration(new DividerItemDecoration(mView.getContext(), 1));
		}
		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected Void doInBackground(Void... voids) {
			try {
				document = Jsoup.connect(url).get();
//				System.out.println("system msg : " + document);
//				Log.d("TAG", "Msg : " + document);
			} catch (IOException e) {
				System.out.println("error! ");
				e.printStackTrace();
			}
			return null;
		}
	}
}