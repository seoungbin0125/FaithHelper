package truesolution.ledpad.asign.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.MainActivity;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.app.MAPP;
import truesolution.ledpad.asign.async.EmoticonFavoriteDBDataAsyncTask;
import truesolution.ledpad.asign.db.MAppDatabase;
import truesolution.ledpad.asign.fd.FD_ASSETS;
import truesolution.ledpad.asign.fd.FD_DRAW;
import truesolution.ledpad.asign.fragment.adapter.MGalleryBaseAdapter;
import truesolution.ledpad.asign.fragment.str.STR_GalleryCell;

/**
 * Created by TCH on 2020/07/07
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/07
 */
public class MFragmentGallery extends Fragment {
	private static final int TAB_MENU_CATEGORY                  = 0;
	private static final int TAB_MENU_ALL                       = 1;
	private static final int TAB_MENU_FAVORITE                  = 2;
	private int mTabMenu;

	/**
	 * The Tv search box.
	 */
	public TextView tvSearchBox;
	/**
	 * The Tv btn search box.
	 */
	public TextView tvBtnSearchBox;
	/**
	 * The Et gallery search.
	 */
	public EditText etGallerySearch;

	/**
	 * The Btn tab menu category.
	 */
// Tab
	public TextView btnTabMenuCategory;
	/**
	 * The Btn tab menu all.
	 */
	public TextView btnTabMenuAll;
	/**
	 * The Btn tab menu favorite.
	 */
	public TextView btnTabMenuFavorite;
	/**
	 * The M is depth.
	 */
	public boolean mIsDepth                         = true;

	/**
	 * The Tv btn gallery add.
	 */
	public TextView tvBtnGalleryAdd;

	/**
	 * The Rl sub tab menu.
	 */
	public LinearLayout rlSubTabMenu;
	/**
	 * The Rl draw top.
	 */
	public RelativeLayout rlDrawTop;
	private View mView;
	private MainActivity mActivity;
	private GridView gvGalleryList;
	private List<STR_GalleryCell> mGalleryList;
	private List<STR_GalleryCell> mDisplayGalleryList = new ArrayList<>();
	private String mFileDirPath;

	/**
	 * Instantiates a new M fragment gallery.
	 *
	 * @param _activity the activity
	 * @param _path     the path
	 * @param _list     the list
	 */
	public MFragmentGallery(MainActivity _activity, String _path, List<STR_GalleryCell> _list) {
		mActivity = _activity;
		mGalleryList = _list;
		mFileDirPath = _path;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if(mView == null) {
			mView = inflater.inflate(R.layout.fragment_food_shop_info, container, false);
			mFindView(mView);
		}
		return mView;
	}

	/**
	 * Find View
	 *
	 * @param _view the view
	 */
	public void mFindView(View _view) {
		tvSearchBox = _view.findViewById(R.id.tvSearchBox);
		tvBtnSearchBox = _view.findViewById(R.id.tvBtnSearchBox);
		etGallerySearch = _view.findViewById(R.id.etGallerySearch);

		gvGalleryList = _view.findViewById(R.id.gvFoodList);
		gvGalleryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
			}
		});
	}
}
