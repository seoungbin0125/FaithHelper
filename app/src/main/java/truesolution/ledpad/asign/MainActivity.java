package truesolution.ledpad.asign;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import truesolution.ledpad.asign.utils.AppManager;
import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import truesolution.ledpad.asign.app.MAPP;
import truesolution.ledpad.asign.async.CategoryAddDBDataAsyncTask;
import truesolution.ledpad.asign.async.CategoryDeleteDBDataAsyncTask;
import truesolution.ledpad.asign.async.EmoticonDeleteDBDataAsyncTask;
import truesolution.ledpad.asign.async.LoadRoomDBDataAsyncTask;
import truesolution.ledpad.asign.db.MAppDatabase;
import truesolution.ledpad.asign.db.MDB;
import truesolution.ledpad.asign.db.MDB_FD;
import truesolution.ledpad.asign.db.MD_Category;
import truesolution.ledpad.asign.db.MD_Emoticon;
import truesolution.ledpad.asign.dialog.MCategoryAddDialog;
import truesolution.ledpad.asign.dialog.MEmoticonAddDialog;
import truesolution.ledpad.asign.fd.FD_BT;
import truesolution.ledpad.asign.fd.FD_DELAY;
import truesolution.ledpad.asign.fd.FD_DRAW;
import truesolution.ledpad.asign.fd.FD_FILE;
import truesolution.ledpad.asign.fd.FD_MENU;
import truesolution.ledpad.asign.fd.FD_REQ;
import truesolution.ledpad.asign.fragment.MFragmentDeviceList;
import truesolution.ledpad.asign.fragment.MFragmentDraw;
import truesolution.ledpad.asign.fragment.MFragmentGallery;
import truesolution.ledpad.asign.fragment.MFragmentShowMode;
import truesolution.ledpad.asign.fragment.MFragmentText;
import truesolution.ledpad.asign.fragment.adapter.MPagerAdapter;
import truesolution.ledpad.asign.fragment.str.STR_BluetoothDevice;
import truesolution.ledpad.asign.fragment.str.STR_GalleryCell;
import truesolution.ledpad.asign.share.EventBusPost;
import truesolution.ledpad.asign.share.EventBusProvider;
import truesolution.ledpad.asign.share.MShared;
import truesolution.ledpad.asign.utils.LocationServicesRequset;
import truesolution.ledpad.asign.utils.MBluetoothUtils;
import truesolution.ledpad.asign.utils.Utils;
import truesolution.ledpad.asign.view.MSwipeViewPager;
import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * The type Main activity.
 */
public class MainActivity extends MBaseActivity {
	/**
	 * The Btn bottom menu device list.
	 */
	@BindView(R.id.tvBtnBottomMenuDeviceList)
	TextView btnBottomMenuDeviceList;
	/**
	 * The Btn bottom menu gallery.
	 */
	@BindView(R.id.tvBtnBottomMenuGallery)
	TextView btnBottomMenuGallery;
	/**
	 * The Btn bottom menu draw.
	 */
	@BindView(R.id.tvBtnBottomMenuDraw)
	TextView btnBottomMenuDraw;
	/**
	 * The Btn bottom menu show mode.
	 */
	@BindView(R.id.tvBtnBottomMenuShowMode)
	TextView btnBottomMenuShowMode;

	/**
	 * The Tv btn bottom menu text.
	 */
	@BindView(R.id.tvBtnBottomMenuText)
	TextView tvBtnBottomMenuText;
	/**
	 * The Vp main.
	 */

	@BindView(R.id.vpMain)
	MSwipeViewPager vpMain;

	/**
	 * Gallery setion
	 */

	private List<MD_Category> mCategoryList = new ArrayList<>();
	private List<MD_Emoticon> mEmoticonList = new ArrayList<>();
	private List<STR_GalleryCell> mStrGalleryList = new ArrayList<>();
	private String mFileDirPath;


	public MMCategoryAddDialog mCategoryAddDialog;


	public class MMCategoryAddDialog extends MCategoryAddDialog {
		/**
		 * Instantiates a new Mm category add dialog.
		 *
		 * @param _activity the activity
		 */
		public MMCategoryAddDialog(MainActivity _activity) {
			super(_activity);
		}

		@Override
		public void mBtnYes(String _name) {
			MDEBUG.debug("MMCategoryAddDialog mBtnYes _name : " + _name);

			MD_Category _md = new MD_Category();
			_md.mName = _name;
			_md.mResID = R.drawable.cate_default;
			_md.mSubIdx = MAPP.ERROR_;
			_md.mIsImgDefault = true;

			new MCategoryAddDBDataAsyncTask(MainActivity.this, MAPP.mAppDatabase, _md).execute();
		}

		@Override
		public void mBtnNo() {
			MDEBUG.debug("mBtnNo!");
		}
	}

	private class MCategoryAddDBDataAsyncTask extends CategoryAddDBDataAsyncTask {
		/**
		 * Instantiates a new M category add db data async task.
		 *
		 * @param _activity the activity
		 * @param _mad      the mad
		 * @param _str      the str
		 */
		public MCategoryAddDBDataAsyncTask(Activity _activity, MAppDatabase _mad, MD_Category _str) {
			super(_activity, _mad, _str);
			mShowProgress();
		}

		@Override
		public void mResult() {
			new MLoadRoomDBDataAsyncTask(MainActivity.this, MAPP.mAppDatabase).execute();
		}
	}

	private class MLoadRoomDBDataAsyncTask extends LoadRoomDBDataAsyncTask {
		/**
		 * Instantiates a new M load room db data async task.
		 *
		 * @param _activity the activity
		 * @param _md       the md
		 */
		public MLoadRoomDBDataAsyncTask(Activity _activity, MAppDatabase _md) {
			super(_activity, _md);
			mCategoryList.clear();
			mEmoticonList.clear();
			mStrGalleryList.clear();
		}

		@Override
		public void mResult(List<MD_Category> _c_list, List<MD_Emoticon> _e_list) {
			mCancelProgress();
			mSetGalleryListData(_c_list, _e_list);
			((MFragmentText)mFragmentText).mRefreshGallery();
		}
	}

	private void mSetGalleryListData(List<MD_Category> _c_list, List<MD_Emoticon> _e_list) {
		mCategoryList.addAll(_c_list);
		mEmoticonList.addAll(_e_list);

		// Set Category Data
		for(int i = 0; i < mCategoryList.size(); i++) {
			STR_GalleryCell _str = new STR_GalleryCell();
			_str.mIsCategory = true;
			MD_Category _md = mCategoryList.get(i);
			_str.mSetCategoryData(_md.idx_, _md.mSubIdx, _md.mName, "", _md.mResID);
			mStrGalleryList.add(_str);
		}

		// Set Emoticon Data
		MDEBUG.debug("mEmoticonList.size() : " + mEmoticonList.size());
		for(int i = 0; i < mEmoticonList.size(); i++) {
			MDEBUG.debug("emoticon list i : " + i);
			STR_GalleryCell _str = new STR_GalleryCell();
			_str.mIsCategory = false;
			MD_Emoticon _md = mEmoticonList.get(i);

			MDEBUG.debug("_md.mW : " + _md.mW + ", _md.mH : " + _md.mH);
			_str.mSetEmoticonData(_md.idx_, _md.mName,
					_md.mCatergoryIdx, _md.mCatergoryName,
					_md.mW, _md.mH,
					_md.mIsOneEmoticon, _md.mEmoticonFilesPath, _md.mImageResID,
					_md.mIsLocalData, _md.mIsFavorite, _md.mDescription, _md.mDate);
			mStrGalleryList.add(_str);
		}
	}

	public void mDeleteEmoticon(int _idx) {
		MD_Emoticon _str_emoticon = null;
		int _c_idx = MAPP.ERROR_;
		for(int i = 0; i < mEmoticonList.size(); i++) {
			MD_Emoticon _str = mEmoticonList.get(i);
			if(_idx == _str.idx_) {
				_c_idx = i;
				_str_emoticon = _str;
				break;
			}
		}

		if(_str_emoticon != null)
			new MEmoticonDeleteDBDataAsyncTask(MAPP.mAppDatabase, _str_emoticon, _c_idx).execute();
	}

	private class MEmoticonDeleteDBDataAsyncTask extends EmoticonDeleteDBDataAsyncTask {
		/**
		 * The M idx.
		 */
		int mIDX;

		/**
		 * Instantiates a new M emoticon delete db data async task.
		 *
		 * @param _mad the mad
		 * @param _str the str
		 * @param _idx the idx
		 */
		public MEmoticonDeleteDBDataAsyncTask(MAppDatabase _mad, MD_Emoticon _str, int _idx) {
			super(_mad, _str);
			mShowProgress();
			mIDX = _idx;
		}

		@Override
		public void mResult() {
			mCancelProgress();
			mEmoticonList.remove(mIDX);
			((MFragmentText)mFragmentText).mUpdateList();
		}
	}

	public void mDeleteCategory(int _idx) {
		MD_Category _str_category = null;
		for(int i = 0; i < mCategoryList.size(); i++) {
			MD_Category _str = mCategoryList.get(i);
			if(_idx == _str.idx_) {
				_str_category = _str;
				break;
			}
		}

		if(_str_category != null)
			new MCategoryDeleteDBDataAsyncTask(MAPP.mAppDatabase, _str_category).execute();
	}

	private class MCategoryDeleteDBDataAsyncTask extends CategoryDeleteDBDataAsyncTask {
		/**
		 * Instantiates a new M category delete db data async task.
		 *
		 * @param _mad the mad
		 * @param _str the str
		 */
		public MCategoryDeleteDBDataAsyncTask(MAppDatabase _mad, MD_Category _str) {
			super(_mad, _str);
			mShowProgress();
		}

		@Override
		public void mResult() {
			new MLoadRoomDBDataAsyncTask(MainActivity.this, MAPP.mAppDatabase).execute();
		}
	}

	/**
	 * Gallery setion ++++++++++
	 */



	// ViewPager
	private MPagerAdapter mPagerAdapter;
	/**
	 * The M fragment device list.
	 */
	public Fragment mFragmentDeviceList,
	/**
	 * The M fragment gallery.
	 */
	mFragmentGallery,
	/**
	 * The M fragment draw.
	 */
	mFragmentDraw,
	/**
	 * The M fragment text.
	 */
	mFragmentText,
	/**
	 * The M fragment info.
	 */
	mFragmentInfo,
	/**
	 * The M fragment show mode.
	 */
	mFragmentShowMode;
	/**
	 * Activity Stack Management Class
	 */
	AppManager appManager;


	// Context
	static public Context mContext;


	/**
	 * The constant POPUP_COLOR_SETTING_DRAW.
	 */
	public static final int POPUP_COLOR_SETTING_DRAW = 0;
	/**
	 * The constant POPUP_COLOR_SETTING_TEXT.
	 */
	public static final int POPUP_COLOR_SETTING_TEXT = 1;
	/**
	 * The constant POPUP_COLOR_SETTING_TEXT_BG.
	 */
	public static final int POPUP_COLOR_SETTING_TEXT_BG = 2;


	@Override
	public String mGetErrorMessage() {
		return null;
	}

	/**
	 * On Confirm
	 */
	@Override
	public void mOnConfirm() {
		MDEBUG.debug("mOnConfirm!");
	}

	/**
	 * On Yes
	 */
	@Override
	public void mOnYes() {
		MDEBUG.debug("mOnYes!");
		MainActivity.this.finish();
	}

	/**
	 * On Cancel
	 */
	@Override
	public void mOnCancel() {
		MDEBUG.debug("mOnCancel!");
	}

	/**
	 * Initialize
	 */
	@Override
	public void mInit() {
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		mContext = this;
		mPagerAdapter = new MMPagerAdapter(getSupportFragmentManager(), 0, this);
		vpMain.setAdapter(mPagerAdapter);
		appManager.getInstance().addActivity(this);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	/**
	 * On view clicked.
	 *
	 * @param view the view
	 */
	@OnClick({R.id.tvBtnBottomMenuDeviceList, R.id.tvBtnBottomMenuGallery, R.id.tvBtnBottomMenuDraw, R.id.tvBtnBottomMenuText, R.id.tvBtnBottomMenuShowMode})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tvBtnBottomMenuDeviceList:
				mBottomMenuReset();
				btnBottomMenuDeviceList.setSelected(true);
				vpMain.setCurrentItem(FD_MENU.DEVICE_LIST);
				break;
			case R.id.tvBtnBottomMenuGallery:
				mBottomMenuReset();
				btnBottomMenuGallery.setSelected(true);
				vpMain.setCurrentItem(FD_MENU.GALLERY_);
				break;
			case R.id.tvBtnBottomMenuDraw:
				mBottomMenuReset();
				btnBottomMenuDraw.setSelected(true);
				vpMain.setCurrentItem(FD_MENU.DRAW_);
				break;
			case R.id.tvBtnBottomMenuText:
				mBottomMenuReset();
				tvBtnBottomMenuText.setSelected(true);
				vpMain.setCurrentItem(FD_MENU.TEXT_);
				break;
			case R.id.tvBtnBottomMenuShowMode:
				mBottomMenuReset();
				btnBottomMenuShowMode.setSelected(true);
				vpMain.setCurrentItem(FD_MENU.SHOW_MODE);
				break;
		}
	}

	// Bottom Button Reset
	private void mBottomMenuReset() {
		btnBottomMenuDeviceList.setSelected(false);
		btnBottomMenuGallery.setSelected(false);
		btnBottomMenuDraw.setSelected(false);
		tvBtnBottomMenuText.setSelected(false);
		btnBottomMenuShowMode.setSelected(false);
	}

	private class MMPagerAdapter extends MPagerAdapter {
		/**
		 * Instantiates a new Mm pager adapter.
		 *
		 * @param fm       fragment manager that will interact with this adapter
		 * @param behavior determines if only current fragments are in a resumed state
		 * @param _context the context
		 */
		public MMPagerAdapter(@NonNull FragmentManager fm, int behavior, Context _context) {
			super(fm, behavior, _context);
		}

		@Override
		public Fragment getItem(int position) {
			MDEBUG.debug("getitem!!!!!!!!! : " + position);
			if (position == FD_MENU.DEVICE_LIST) {
				if (mFragmentDeviceList == null)
					mFragmentDeviceList = new MFragmentDeviceList(MainActivity.this);
				return mFragmentDeviceList;
			} else if (position == FD_MENU.GALLERY_) {
				if (mFragmentGallery == null)
					mFragmentGallery = new MFragmentGallery(MainActivity.this);
				return mFragmentGallery;
			} else if (position == FD_MENU.DRAW_) {
				if (mFragmentDraw == null)
					mFragmentDraw = new MFragmentDraw(MainActivity.this);
				return mFragmentDraw;
			} else if (position == FD_MENU.TEXT_) {
				if (mFragmentText == null)
					mFragmentText = new MFragmentText(MainActivity.this, mFileDirPath, mStrGalleryList);
				return mFragmentText;
			} else if (position == FD_MENU.SHOW_MODE) {
				if (mFragmentShowMode == null)
					mFragmentShowMode = new MFragmentShowMode(MainActivity.this);
				return mFragmentShowMode;
			}

			return null;
		}
	}

	public void mKeyboardHide(EditText _et) {
		InputMethodManager inputMethodManager =
				(InputMethodManager) getSystemService(
						Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(_et.getWindowToken(), 0);
	}

	/**
	 * M view pager current page.
	 *
	 * @param _idx the idx
	 */
// Current Page
	public void mViewPagerCurrentPage(int _idx) {
		if (vpMain == null)
			return;

		mBottomMenuReset();
		switch (_idx) {
			case FD_MENU.DEVICE_LIST:
				btnBottomMenuDeviceList.setSelected(true);
				break;
			case FD_MENU.GALLERY_:
				btnBottomMenuGallery.setSelected(true);
				break;
			case FD_MENU.DRAW_:
				btnBottomMenuDraw.setSelected(true);
				break;
			case FD_MENU.TEXT_:
				tvBtnBottomMenuText.setSelected(true);
				break;
			case FD_MENU.SHOW_MODE:
				btnBottomMenuShowMode.setSelected(true);
				break;
		}

		vpMain.setCurrentItem(_idx);
	}
}