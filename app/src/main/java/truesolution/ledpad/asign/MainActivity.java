package truesolution.ledpad.asign;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import truesolution.ledpad.asign.utils.AppManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import truesolution.ledpad.asign.db.MD_Category;
import truesolution.ledpad.asign.db.MD_Emoticon;
import truesolution.ledpad.asign.fd.FD_MENU;
import truesolution.ledpad.asign.fragment.MFragmentDeviceList;
import truesolution.ledpad.asign.fragment.MFragmentDraw;
import truesolution.ledpad.asign.fragment.MFragmentText;
import truesolution.ledpad.asign.fragment.MFragmentShowMode;
import truesolution.ledpad.asign.fragment.MFragmentGallery;
import truesolution.ledpad.asign.fragment.adapter.MPagerAdapter;
import truesolution.ledpad.asign.fragment.str.STR_GalleryCell;
import truesolution.ledpad.asign.view.MSwipeViewPager;

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
	 * Directory File Path
	 */
	private String mFileDirPath;

	/**
	 * Transmit Recive Check
	 */
	public byte sendCount;
	public int mW, mH, mIDX;
	int[][] mAllData;
	int reqCnt = 0;
	boolean sendBusyFlag = true;
	boolean dialogStatus = false;
	int reciveSuccessToken = -1;

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

	private List<MD_Category> mCategoryList = new ArrayList<>();
	private List<MD_Emoticon> mEmoticonList = new ArrayList<>();
	private List<STR_GalleryCell> mStrGalleryList = new ArrayList<>();

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
					mFragmentGallery = new MFragmentText();
				return mFragmentGallery;
			} else if (position == FD_MENU.DRAW_) {
				if (mFragmentDraw == null)
					mFragmentDraw = new MFragmentDraw(MainActivity.this);
				return mFragmentDraw;
			} else if (position == FD_MENU.TEXT_) {
				if (mFragmentText == null)
					mFragmentText = new MFragmentGallery(MainActivity.this, mFileDirPath, mStrGalleryList);
				return mFragmentText;
			} else if (position == FD_MENU.SHOW_MODE) {
				if (mFragmentShowMode == null)
					mFragmentShowMode = new MFragmentShowMode(MainActivity.this);
				return mFragmentShowMode;
			}

			return null;
		}
	}

	public void print(String msg) {
		MDEBUG.debug("msg :" + msg);
	}

	public Object getData() {
		return MainActivity.this;
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