package truesolution.ledpad.asign.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.R;

public class FoodViewer extends RelativeLayout {

    public TextView tvFoodShopIdx;
    ImageView ivFoodShopImg;

    public LinearLayout liCellInfo;
    public LinearLayout liCellfun;

    public TextView tvBtnCellInfo;
    public TextView tvBtnCellWrite;
    public TextView tvBtnCellFavorite;
    public ItemFoodShop itemFoodShop = new ItemFoodShop();

    public FoodViewer(Context context) {
        super(context);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cell_food_shop,this, true);

        tvFoodShopIdx = (TextView) findViewById(R.id.tvCategoryName);
        ivFoodShopImg = (ImageView) findViewById(R.id.tvBoardOnImage);
        liCellInfo = (LinearLayout) findViewById(R.id.liCellInfo);
        liCellfun = (LinearLayout) findViewById(R.id.liCellfun);
        tvBtnCellInfo = (TextView) findViewById(R.id.tvBtnCellInfo);
        tvBtnCellWrite = (TextView) findViewById(R.id.tvBtnCellWrite);
        tvBtnCellWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MDEBUG.debug(itemFoodShop.getPhoneNum());
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + itemFoodShop.getPhoneNum()));
                context.startActivity(intent);
            }
        });
        tvBtnCellFavorite = (TextView) findViewById(R.id.tvBtnCellFavorite);
    }

        public void setItem(ItemFoodShop itemFoodShop) {
        this.itemFoodShop = itemFoodShop;
        tvFoodShopIdx.setText(itemFoodShop.getName());
        ivFoodShopImg.setImageResource(itemFoodShop.getImage());
    }
}
