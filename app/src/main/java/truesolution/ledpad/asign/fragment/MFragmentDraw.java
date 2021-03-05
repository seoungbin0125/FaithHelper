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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;

import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.MainActivity;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.app.MAPP;

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

	String url ="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=ZvK%2BdJQ7edFGA6is2AVZ28YDx8Q6dWSWTrTKJjALh9U1Yr%2Fk5oNlcHidHMe3dcmKPVPCZBomkqPqirxeJyJ9Ng%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20210304&endCreateDt=20210304";
//	String url = "http://openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=ZvK%2BdJQ7edFGA6is2AVZ28YDx8Q6dWSWTrTKJjALh9U1Yr%2Fk5oNlcHidHMe3dcmKPVPCZBomkqPqirxeJyJ9Ng%3D%3D&ServiceKey=ZvK%2BdJQ7edFGA6is2AVZ28YDx8Q6dWSWTrTKJjALh9U1Yr%2Fk5oNlcHidHMe3dcmKPVPCZBomkqPqirxeJyJ9Ng%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20201218&endCreateDt=20201218";

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
//		new JsoupAsyncTask().execute();
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
				}
			} catch (Exception e) {
			}
		}
		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected Void doInBackground(Void... voids) {
			try {
				document = Jsoup.connect(url).get();
				System.out.println("system msg : " + document);
				Log.d("TAG", "Msg : " + document);
			} catch (IOException e) {
				System.out.println("error! ");
				e.printStackTrace();
			}
			return null;
		}
	}
}