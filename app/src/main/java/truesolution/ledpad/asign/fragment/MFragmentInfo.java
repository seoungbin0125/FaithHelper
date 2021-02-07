package truesolution.ledpad.asign.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.fragment.str.STR_GalleryCell;

/**
 * Created by TCH on 2020/07/07
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/07
 */
public class MFragmentInfo extends Fragment {
	private View mView;
	private Activity mActivity;
	
	public MFragmentInfo(Activity _activity) {
		mActivity = _activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_info, container, false);
		return mView;
	}
}
