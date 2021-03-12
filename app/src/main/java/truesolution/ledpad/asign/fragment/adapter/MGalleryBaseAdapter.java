package truesolution.ledpad.asign.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.app.MAPP;
import truesolution.ledpad.asign.fd.FD_DRAW;
import truesolution.ledpad.asign.fragment.str.STR_GalleryCell;
import truesolution.ledpad.asign.fragment.str.STR_GalleryEmoticon;

/**
 * Created by TCH on 2020/07/10
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/10
 */
public abstract class MGalleryBaseAdapter extends BaseAdapter implements View.OnClickListener, View.OnLongClickListener {
	/**
	 * M click.
	 *
	 * @param _vh the vh
	 */
	public abstract void mClick(ViewHolder _vh);
	
	/**
	 * M preview.
	 *
	 * @param _str the str
	 */
	public abstract void mPreview(STR_GalleryCell _str);
	
	/**
	 * M write.
	 *
	 * @param _str the str
	 */
	public abstract void mWrite(STR_GalleryCell _str);
	
	/**
	 * M delete.
	 *
	 * @param _str the str
	 */
	public abstract void mDelete(STR_GalleryCell _str);
	
	/**
	 * M favorite.
	 *
	 * @param _str the str
	 * @param _st  the st
	 */
	public abstract void mFavorite(STR_GalleryCell _str, boolean _st);
	private final Context mContext;
	private List<STR_GalleryCell> mList = new ArrayList<>();
	private List<ViewHolder> mCellVH = new ArrayList<>();
	private String mFileDirPath;
	private int mSelectIDX;
	
	/**
	 * Instantiates a new M gallery base adapter.
	 *
	 * @param context the context
	 * @param _path   the path
	 * @param _list   the list
	 */
	public MGalleryBaseAdapter(Context context, String _path, List<STR_GalleryCell> _list) {
		this.mContext = context;
		mList = _list;
		mFileDirPath = _path;
		mSelectIDX = MAPP.ERROR_;
	}
	
	/**
	 * M get cell view view holder.
	 *
	 * @param _pos the pos
	 *
	 * @return the view holder
	 */
	public ViewHolder mGetCellView(int _pos) {
		MDEBUG.debug("mConvertViewList size : " + mCellVH.size());
//		if(_pos >= mCellVH.size()) {
//			return null;
//		}
		
		for(int i = 0; i < mCellVH.size(); i++) {
			if(_pos == mCellVH.get(i).mStr.mCellPosition)
				return mCellVH.get(i);
		}
		
		return mCellVH.get(_pos);
	};
	
	/**
	 * M init info layout.
	 */
	public void mInitInfoLayout() {
		for(int i = 0; i < mCellVH.size(); i++) {
			mCellVH.get(i).llInfo.setVisibility(View.VISIBLE);
			mCellVH.get(i).llIcon.setVisibility(View.INVISIBLE);
		}
		
		for(int i = 0; i < mList.size(); i++) {
			STR_GalleryEmoticon _str_ge = mList.get(i).mEmoticon;
			if(_str_ge != null)
				mList.get(i).mEmoticon.mIsInfoLayoutShow = true;
		}
	}
	
	/**
	 * M update data and view.
	 */
	public void mUpdateDataAndView() {
		// View Recycle
		mSelectIDX = MAPP.ERROR_;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}
	
	// 3
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	// 4
	@Override
	public Object getItem(int position) {
		return null;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _g_vh;

		return convertView;
	}

	@Override
	public void onClick(View view) {

	}
	
	@Override
	public boolean onLongClick(View view) {
		MDEBUG.debug("long click tag : " + view.getTag());
		ViewHolder _holder = (ViewHolder) view.getTag();
		_holder.tvBtnDelete.setVisibility(View.VISIBLE);
		
		MDEBUG.debug("onLongClick mSubIDX : " + _holder.mStr.mCategory.mSubIDX);
		return true;
	}
	
	/**
	 * The type View holder.
	 */
	public static class ViewHolder {
		/**
		 * The Ll info.
		 */
		public LinearLayout llInfo;
		/**
		 * The Ll icon.
		 */
		public LinearLayout llIcon;
		/**
		 * The M bg.
		 */
		public ImageView mBG;
		/**
		 * The M tv name.
		 */
		public TextView mTVName;
		/**
		 * The M tv btn.
		 */
		public TextView mTVBtn;
		
		/**
		 * The Tv btn gallery preview.
		 */
		public TextView tvBtnGalleryPreview;
		/**
		 * The Tv btn gallery write.
		 */
		public TextView tvBtnGalleryWrite;
		/**
		 * The Tv btn gallery favorite.
		 */
		public TextView tvBtnGalleryFavorite;
		/**
		 * The Tv btn delete.
		 */
		public TextView tvBtnDelete;

		/**
		 * The TV btn WxH
		 */
		public TextView tvBtnWxH;

		/**
		 * The M str.
		 */
		public STR_GalleryCell mStr;
	}
	
	private class MImageLoadAsyncTask extends AsyncTask<Void, Void, Bitmap> {
		/**
		 * The M bgiv.
		 */
		ImageView mBGIV;
		/**
		 * The M path.
		 */
		String mPath;
		/**
		 * The M w.
		 */
		int mW;
		
		/**
		 * Instantiates a new M image load async task.
		 *
		 * @param _iv        the iv
		 * @param _file_path the file path
		 */
		public MImageLoadAsyncTask(ImageView _iv, String _file_path) {
			mBGIV = _iv;
			mPath = _file_path;
			mW = mBGIV.getMeasuredWidth();
		}
		
		@Override
		protected Bitmap doInBackground(Void... _void) {
			Bitmap _bm = null;
			
			try {
				File _img_file = new File(mFileDirPath + "/" + mPath + FD_DRAW.MSAVE_FILE_FORMAT_STR);
				if(_img_file != null && _img_file.exists()) {
					Bitmap _c_bm = BitmapFactory.decodeFile(_img_file.getAbsolutePath());
					float _scale = (float)(mW / _c_bm.getWidth());
					int _s_w = (int)(_c_bm.getWidth() * _scale);
					int _s_h = (int)(_c_bm.getHeight() * _scale);
					_bm = Bitmap.createScaledBitmap(_c_bm, _s_w, _s_h, false);
				}
			} catch(Exception e) {
				MDEBUG.debug("mImageToIntAR error : " + e.toString());
			}
			return _bm;
		}
		
		@Override
		public void onPostExecute(Bitmap _bm) {
			if(_bm != null) {
				final Drawable drawable = new BitmapDrawable(mContext.getResources(), _bm);
				mBGIV.setBackground(drawable);
			}
		}
	}
	
	/**
	 * M close all info layout.
	 */
	public void mCloseAllInfoLayout() {
		for(int i = 0; i < mCellVH.size(); i++) {
			mCellVH.get(i).llIcon.setVisibility(View.INVISIBLE);
			mCellVH.get(i).mStr.mEmoticon.mIsInfoLayoutShow = true;
			mCellVH.get(i).tvBtnWxH.setVisibility(View.INVISIBLE);
		}
	}
}
