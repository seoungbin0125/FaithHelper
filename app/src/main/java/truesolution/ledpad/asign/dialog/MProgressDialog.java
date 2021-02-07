package truesolution.ledpad.asign.dialog;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.MainActivity;
import truesolution.ledpad.asign.R;

/**
 * MProgressDialog
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020. 07. 01.
 */
public class MProgressDialog extends Dialog {
	/**
	 * Instantiates a new M progress dialog.
	 *
	 * @param context the context
	 */
	public MProgressDialog(Context context) {
		super(context);
	}
	
	/**
	 * Show m progress dialog.
	 *
	 * @param _context the context
	 * @param _title   the title
	 * @param _message the message
	 *
	 * @return the m progress dialog
	 */
	public static MProgressDialog show(Context _context,
                                       CharSequence _title, CharSequence _message) {
		return show(_context, _title, _message, false);
	}
	
	/**
	 * Show m progress dialog.
	 *
	 * @param _context       the context
	 * @param _title         the title
	 * @param _message       the message
	 * @param _indeterminate the indeterminate
	 *
	 * @return the m progress dialog
	 */
	public static MProgressDialog show(Context _context,
                                       CharSequence _title, CharSequence _message,
                                       boolean _indeterminate) {
		return show(_context, _title, _message, _indeterminate, false, null);
	}
	
	/**
	 * Show m progress dialog.
	 *
	 * @param _context       the context
	 * @param _title         the title
	 * @param _message       the message
	 * @param _indeterminate the indeterminate
	 * @param _cancelable    the cancelable
	 *
	 * @return the m progress dialog
	 */
	public static MProgressDialog show(Context _context,
                                       CharSequence _title, CharSequence _message,
                                       boolean _indeterminate, boolean _cancelable) {
		return show(_context, _title, _message, _indeterminate, _cancelable, null);
	}
	
	/**
	 * Show m progress dialog.
	 *
	 * @param _context        the context
	 * @param _title          the title
	 * @param _message        the message
	 * @param _indeterminate  the indeterminate
	 * @param _cancelable     the cancelable
	 * @param _cancelListener the cancel listener
	 *
	 * @return the m progress dialog
	 */
	public static MProgressDialog show(Context _context, CharSequence _title,
                                       CharSequence _message, boolean _indeterminate,
                                       boolean _cancelable, OnCancelListener _cancelListener) {
		MProgressDialog _dialog = new MProgressDialog(_context);
		_dialog.setTitle(_title);
		_dialog.setCancelable(_cancelable);
		_dialog.setOnCancelListener(_cancelListener);
		_dialog.addContentView(new ProgressBar(_context),
				new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		_dialog.show();
		
		return _dialog;
	}

	public static MProgressDialog show(Context _context, CharSequence _title,
									   CharSequence _message, boolean _indeterminate,
									   boolean _cancelable, OnCancelListener _cancelListener,
									   BluetoothAdapter mBtAdapter, int excuteInfo) {
		MProgressDialog _dialog = new MProgressDialog(_context);
		_dialog.setTitle(_title);
		_dialog.setCancelable(_cancelable);
		_dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog)
			{
//				switch (excuteInfo) {
//					case BLUETOOTH_SCAN_CANCEL:
//						mBtAdapter.cancelDiscovery();
//						break;
//					case PICTURE_TRANSMIT_CANCEL:
//						((MainActivity)MainActivity.mContext).setDialogStatus(false);
//						MDEBUG.debug("cancel Picture");
//						break;
//				}
//				mBtAdapter.cancelDiscovery();
			}
		});
		_dialog.addContentView(new ProgressBar(_context),
				new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		_dialog.show();
		return _dialog;
	}
	
	@Override
	public void onCreate(Bundle _bundle) {
		super.onCreate(_bundle);
		
		WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
		lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		lpWindow.dimAmount = 0.8f;

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setAttributes(lpWindow);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
		
//		mHideSystemUI();
		
		setContentView(R.layout.layout_progress);
	}
	
	@Override
	public void onStart() {
		super.onStart();
//		mHideSystemUI();
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
//		mHideSystemUI();
	}
	
	/**
	 * M hide system ui.
	 */
// This snippet hides the system bars.
	public void mHideSystemUI() {
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
						| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
						| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		MDEBUG.debug("Dialog mHideSystemUI!!!!!");
	}
}
