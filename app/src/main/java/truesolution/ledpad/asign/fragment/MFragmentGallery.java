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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.MainActivity;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.app.FoodViewer;
import truesolution.ledpad.asign.app.ItemFoodShop;
import truesolution.ledpad.asign.app.MAPP;
import truesolution.ledpad.asign.async.EmoticonFavoriteDBDataAsyncTask;
import truesolution.ledpad.asign.db.MAppDatabase;
import truesolution.ledpad.asign.fd.FD_ASSETS;
import truesolution.ledpad.asign.fd.FD_DRAW;
import truesolution.ledpad.asign.fragment.adapter.MGalleryBaseAdapter;
import truesolution.ledpad.asign.fragment.adapter.MRealTimeIssueDataAdapter;
import truesolution.ledpad.asign.fragment.str.STR_GalleryCell;

/**
 * Created by TCH on 2020/07/07
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/07
 */
public class MFragmentGallery extends Fragment {

	private static final int CATEGORY_AMOUNT = 2;

	private static final int CATEGORY_CHICKEN = 0;
	private static final int CATEGORY_PIZZA = 1;
	private static final int CATEGORY_CHINA = 2;


	private static final int TAB_MENU_CATEGORY = 0;
	private static final int TAB_MENU_ALL = 1;
	private static final int TAB_MENU_FAVORITE = 2;
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
	 * The Rl draw top.
	 */
	public RelativeLayout rlDrawTop;
	private View mView;
	private MainActivity mActivity;
	private GridView gvGalleryList;

	private List<STR_GalleryCell> mGalleryList;
	private List<STR_GalleryCell> mDisplayGalleryList = new ArrayList<>();

	private ArrayList<ItemFoodShop> cateList = new ArrayList<>();
//	private ArrayList<ItemFoodShop> foodShopList = new ArrayList<>();

	ArrayList<ItemFoodShop> foodShopList[] = new ArrayList[CATEGORY_AMOUNT];

	private String mFileDirPath;
	private FoodAdapter foodAdapter;
	boolean mIsCategory = true;

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
		if (mView == null) {
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
		setCateCellList();
		setfoodShopCellList();
		foodAdapter = new FoodAdapter(cateList);
		gvGalleryList.setAdapter(foodAdapter);
		gvGalleryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(mActivity, foodAdapter.getItem(position).getName().toString(), Toast.LENGTH_LONG).show();
				FoodViewer viewHolder = (FoodViewer)view.getTag();

				if (foodAdapter.getItem(position).getViewStatus()) {
					foodAdapter.setList(foodShopList[position]);
					foodAdapter.notifyDataSetChanged();
					viewHolder.liCellfun.setVisibility(View.GONE);
					viewHolder.liCellInfo.setVisibility(View.VISIBLE);
				} else {
					if (position == 0) {
						foodAdapter.setList(cateList);
						foodAdapter.notifyDataSetChanged();
					} else {
//						String search_itle = viewHolder.mIssueTitle.getText().toString();
						viewHolder.liCellfun.setVisibility(View.VISIBLE);
						viewHolder.liCellInfo.setVisibility(View.GONE);
						Toast.makeText(mActivity, foodAdapter.getItem(position).getName().toString(), Toast.LENGTH_LONG).show();
					}
				}

			}
		});
	}

	class FoodAdapter extends BaseAdapter {
		ArrayList<ItemFoodShop> mList = new ArrayList<ItemFoodShop>();

		FoodAdapter(ArrayList<ItemFoodShop> list) {
			this.mList = list;
		}

		public void setList(ArrayList<ItemFoodShop> list) {
			this.mList = list;
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		public void addItem(ItemFoodShop singerItem) {
			mList.add(singerItem);
		}

		@Override
		public ItemFoodShop getItem(int i) {
			return mList.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			FoodViewer foodViewer = new FoodViewer(mActivity);
			foodViewer.setItem(mList.get(i));
			foodViewer.setTag(foodViewer);

			return foodViewer;
		}
	}

	public void setCateCellList() {

		ItemFoodShop itemFoodShop[] = new ItemFoodShop[2];
		for (int i = 0; i<itemFoodShop.length; i++) {
			itemFoodShop[i] = new ItemFoodShop();
		}
		itemFoodShop[0].setCateInfo("피자", R.drawable.data_pizza1);
		itemFoodShop[1].setCateInfo("중국집", R.drawable.data_chinese_banjum);

		for (int i = 0; i<itemFoodShop.length; i++) {
			cateList.add(itemFoodShop[i]);
		}
	}

	public void setfoodShopCellList() {

		for(int i = 0; i<CATEGORY_AMOUNT; i++) {
			foodShopList[i] = new ArrayList<>();
		}

		ItemFoodShop itemFoodShop[] = new ItemFoodShop[2 + 1];
		for (int i = 0; i<itemFoodShop.length; i++) {
			itemFoodShop[i] = new ItemFoodShop();
		}
		itemFoodShop[0].setShopInfo("뒤로가기", R.drawable.category_back,"010-xxxx-xxxx",123);
		itemFoodShop[1].setShopInfo("피자1", R.drawable.data_pizza1,"010-xxxx-xxxx",123);
		itemFoodShop[2].setShopInfo("피자2", R.drawable.data_chinese_banjum,"010-xxxx-xxxx",456);
		for (int i = 0; i < itemFoodShop.length; i++) {
			foodShopList[CATEGORY_CHICKEN].add(itemFoodShop[i]);
		}
		ItemFoodShop itemPIZZA[] = new ItemFoodShop[4 + 1];
		for (int i = 0; i<itemPIZZA.length; i++) {
			itemPIZZA[i] = new ItemFoodShop();
		}
		itemPIZZA[0].setShopInfo("뒤로가기", R.drawable.category_back,"010-xxxx-xxxx",123);
		itemPIZZA[1].setShopInfo("치킨1", R.drawable.data_pizza1,"010-xxxx-xxxx",123);
		itemPIZZA[2].setShopInfo("치킨2", R.drawable.data_chinese_banjum,"010-xxxx-xxxx",456);
		itemPIZZA[3].setShopInfo("치킨3", R.drawable.data_chinese_banjum,"010-xxxx-xxxx",456);
		itemPIZZA[4].setShopInfo("치킨4", R.drawable.data_chinese_banjum,"010-xxxx-xxxx",456);
		for (int i = 0; i < itemPIZZA.length; i++) {
			foodShopList[CATEGORY_PIZZA].add(itemPIZZA[i]);
		}
	}
}
