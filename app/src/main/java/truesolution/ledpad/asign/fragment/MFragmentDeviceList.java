package truesolution.ledpad.asign.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import truesolution.ledpad.asign.MainActivity;
import truesolution.ledpad.asign.R;

/**
 * Created by TCH on 2020/07/07
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/07
 */
public class MFragmentDeviceList extends Fragment {
	private View mView;
	private MainActivity mActivity;

	private WebView mWebView;

	public MFragmentDeviceList(MainActivity _activity) {
		mActivity = _activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if(mView == null) {
			mView = inflater.inflate(R.layout.fragment_daliy_qt, container, false);

			mWebView = (WebView) mView.findViewById(R.id.webView);//xml 자바코드 연결
			mWebView.getSettings().setJavaScriptEnabled(true);//자바스크립트 허용

			mWebView.loadUrl("http://nh.kccc.org/qt.html");//웹뷰 실행
			mWebView.setWebChromeClient(new WebChromeClient());//웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 alert가 뜨지 않음
			mWebView.setWebViewClient(new WebViewClientClass());//새창열기 없이 웹뷰 내에서 다시 열기//페이지 이동 원활히 하기위해 사용
		}
		return mView;
	}

	private class WebViewClientClass extends WebViewClient {//페이지 이동
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}
