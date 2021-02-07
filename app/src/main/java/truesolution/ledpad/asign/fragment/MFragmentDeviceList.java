package truesolution.ledpad.asign.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import truesolution.ledpad.asign.MDEBUG;
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

	public MFragmentDeviceList(MainActivity _activity) {
		mActivity = _activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if(mView == null) {
			mView = inflater.inflate(R.layout.fragment_device_list, container, false);
		}
		return mView;
	}
}
