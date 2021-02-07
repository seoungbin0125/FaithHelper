package truesolution.ledpad.asign.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.MainActivity;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.app.MAPP;
import truesolution.ledpad.asign.fd.FD_BT;
import truesolution.ledpad.asign.utils.MBluetoothUtils;

/**
 * Created by TCH on 2020/07/07
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/07
 */
public class MFragmentShowMode extends Fragment {
	private View mView;
	private MainActivity mActivity;


	public MFragmentShowMode(MainActivity _activity) {
		mActivity = _activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if(mView == null) {
			mView = inflater.inflate(R.layout.fragment_show_mode, container, false);
		}
		return mView;
	}
}
