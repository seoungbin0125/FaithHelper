package truesolution.ledpad.asign.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class MFragmentGallery extends Fragment {

	private View mView;
	private MainActivity mActivity;

	RecyclerView recyclerView;
	RecyclerView.LayoutManager layoutManager;

	RecyclerView.Adapter adapter;

	public MFragmentGallery(MainActivity _activity) {
		mActivity = _activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_gallery, container, false);
		}
		recyclerView = mView.findViewById(R.id.recyclerView);
		layoutManager = new LinearLayoutManager(mActivity);
		recyclerView.setLayoutManager(layoutManager);

		String[] main_text =  {"코딩","하루"};
		String[] main_text2 =  {"test","test1"};
		adapter = new Main2Adapter(main_text, main_text2);
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new DividerItemDecoration(mView.getContext(), 1));

		return mView;

	}
}