package truesolution.ledpad.asign.db;

import truesolution.ledpad.asign.MDEBUG;
import truesolution.ledpad.asign.R;
import truesolution.ledpad.asign.app.MAPP;
import truesolution.ledpad.asign.fd.FD_DRAW;

/**
 * Created by TCH on 2020/07/10
 *
 * @author think.code.help @gmail.com
 * @version 1.0
 * @since 2020 /07/10
 */
public class MDB_FD {
	/**
	 * The constant CATEGORY_IDX_DATE.
	 */
	public static final int CATEGORY_IDX_DATE                   = 0;
	/**
	 * The constant CATEGORY_IDX_DRIVER.
	 */
	public static final int CATEGORY_IDX_DRIVER                 = 1;
	/**
	 * The constant CATEGORY_IDX_HOLIDAY.
	 */
	public static final int CATEGORY_IDX_HOLIDAY                = 2;
	/**
	 * The constant CATEGORY_IDX_WARNING.
	 */
	public static final int CATEGORY_IDX_WARNING                = 3;
	/**
	 * The constant CATEGORY_IDX_TEXT.
	 */
	public static final int CATEGORY_IDX_TEXT                   = 4;

	/**
	 * The constant CATEGORY_IDX_C19.
	 */
	public static final int CATEGORY_IDX_C19                    = 5;
	/**
	 * The constant CATEGORY_IDX_SHOW.
	 */
	public static final int CATEGORY_IDX_SHOW                   = 6;
	/**
	 * The constant CATEGORY_IDX_NATION.
	 */
	public static final int CATEGORY_IDX_NATION                 = 7;
	/**
	 * The constant CATEGORY_IDX_ANIMAL.
	 */
	public static final int CATEGORY_IDX_ANIMAL                 = 8;
	/**
	 * The constant CATEGORY_IDX_SHOP.
	 */
	public static final int CATEGORY_IDX_SHOP                   = 9;

	/**
	 * The constant CATEGORY_IDX_OTHER.
	 */
	public static final int CATEGORY_IDX_OTHER                  = 10;

	/**
	 * The constant CATEGORY_IDX_ANIMATION.
	 */
	public static final int CATEGORY_IDX_ANIMATION				= 11;


	public static final int CATEGORY_SIZE						= 12;

	/**
	 * The constant MCATEGORY_TITLE.
	 */
// Default Category
	public static final String[] MCATEGORY_TITLE = {
			"데이트", "운전자", "홀리데이", "경고", "문자",
			"코로나", "공연", "국가", "동물", "상점(푸드트럭)",
			"기타", "에니메이션"
	};

	/**
	 * The constant MCATEGORY_TYPE.
	 */
	public static final int[] MCATEGORY_TYPE = {
			CATEGORY_IDX_DATE, CATEGORY_IDX_DRIVER, CATEGORY_IDX_HOLIDAY, CATEGORY_IDX_WARNING, CATEGORY_IDX_TEXT,

			CATEGORY_IDX_C19, CATEGORY_IDX_SHOW, CATEGORY_IDX_NATION, CATEGORY_IDX_ANIMAL, CATEGORY_IDX_SHOP,

			CATEGORY_IDX_OTHER, CATEGORY_IDX_ANIMATION
	};

	/**
	 * The constant MCATEGORY_RES_ID.
	 */
	public static final int[] MCATEGORY_RES_ID = {
			R.drawable.cate_date,
			R.drawable.cate_driver,
			R.drawable.cate_holiday,
			R.drawable.cate_warning,
			R.drawable.cate_text,

			R.drawable.cate_covid,
			R.drawable.cate_show,
			R.drawable.cate_country,
			R.drawable.cate_animal,
			R.drawable.cate_shop,

			R.drawable.cate_default,
			R.drawable.cate_3_d
	};

	/**
	 * The constant MEMOTICON_NAME.
	 */
// Default Emoticon
	public static final String[] MEMOTICON_NAME = {
			// Date 1 : 사랑해 , 2 : Kiss
			"사랑해", "Kiss",
			// Driver
			// Holiday 3 : 여행, 4 : 야자수, 5 : 여름휴가
			"여행", "야자수", "여름휴가",
			// Warning 6 : 조심1 , 7 : 조심2 , 8 : 정지2, 9 : 화기조심
			"조심1", "조심2", "정지2", "화기조심",
			// Text 10 : 욜로 , 11 : Welcome
			"욜로", "Welcome",
			// COVID 12 : 2m거리유지 , 13 : 마스크, 14 : 손씻기, 15 : 바이러스
			"2m 거리유지", "마스크", "손씻기", "바이러스",
			// Show  16 : Good
			"Good",
			// Country 17 : 브라질 , 18 : 중국 , 19 : 프랑스, 20 : 독일,
			// 21 : 이태리, 22 : 일번, 23 : 한국, 24 : 미국
			"브라질", "중국", "프랑스", "독일", "이태리", "일본", "한국", "미국",
			// Animal 25 : 고양이, 26 : 닭, 27 : 강아지, 28 : 사자
			"고양이", "닭", "강아지", "사자",
			// Shop 29 : 맥주, 30 : 커피, 31 : 아이스크림, 32 : 쥬스,
			"맥주", "커피", "아이스크림", "쥬스",
			// 33 : 중국반점, 34 : 핫도그, 35 : 채소가게,
			"중국반점", "핫도그", "채소가게",
			// 36 : 꿀사과, 37 : 당근, 38 : 맥주 무제한,
			"꿀사과", "당근", "맥주 무제한",
			// 39 : 물고기1, 40 : 물고기2, 41 : 짜장, 42 : 피자1, 43 : 피자2,
			"물고기1", "물고기2", "짜장", "피자1", "피자2",

			// 44 : Logo(cs25), 45 : Logo(du), 46 : Logo(Omart), 47 : 화이팅1, 48 : 화이팅2,
			"Logo(CS25)", "Logo(DU)", "Logo(Omart)", "화이팅1", "화이팅2",
			// 49 : Hope1, 50 : Hope2, 51 : 라이언1, 52 : 라이언2,
			"Hope1", "Hope12", "라이언1", "라이언2",
			// 53 : 월세, 54 : 전세, 55 : 급매, 	 56 : Hello1 , 57 : Hello2
			"월세", "전세","급매", "Hello1", "Hello2",

			// Animation
			"동전",
			"지도",
			"스마일"
//			"동전1", "동전2", "동전3", "동전4", "동전5",
//			"지도1", "지도2", "지도3", "지도4", "지도5",
//			"스마일1", "스마일2", "스마일3", "스마일4"
	};

	/**
	 * The constant MEMOTICON_RES_ID.
	 */
	public static final int[] MEMOTICON_RES_ID = {
			// Date 1 : 사랑해 , 2 : Kiss
			R.drawable.date_heart, R.drawable.date_kiss_emoji,
			// Driver
			// Holiday 3 : 야자수, 4 : 여름휴가, 5 : 크리스마스
			R.drawable.hd_palm_tree, R.drawable.hd_summer_vacation, R.drawable.hd_christmas,
			// Warning 6 : 조심1 , 7 : 조심2 , 8 : 정지2, 9 : 화기조심
			R.drawable.wr_attention_1, R.drawable.wr_attention_2, R.drawable.wr_attention_3, R.drawable.wr_attention_4,
			// Text 10 : 욜로 , 11 : Welcome
			R.drawable.txt_3, R.drawable.data_welcome,
			// COVID 12 : 2m거리유지 , 13 : 마스크, 14 : 손씻기, 15 : 바이러스
			R.drawable.c19_2m, R.drawable.c19_mask, R.drawable.c19_no_handshake, R.drawable.c19_virus,
			// Show  16 : Good
			R.drawable.date_good_sign,
			// Country 17 : 브라질 , 18 : 중국 , 19 : 프랑스, 20 : 독일,
			//         21 : 이태리, 22 : 일번, 23 : 한국, 24 : 미국
			R.drawable.nt_brazil, R.drawable.nt_china, R.drawable.nt_france, R.drawable.nt_germany,
			R.drawable.nt_italy, R.drawable.nt_japan, R.drawable.nt_korea, R.drawable.nt_usa,
			// Animal 25 : 고양이, 26 : 닭, 27 : 강아지, 28 : 사자
			R.drawable.anm_cat, R.drawable.anm_chick, R.drawable.anm_dog, R.drawable.anm_lion,
			// Shop  29 : 맥주, 30 : 커피, 31 : 아이스크림,
			//       32 : 쥬스,
			// 64x32 33 : 중국반점, 34 : 핫도그, 35 : 채소가게
			R.drawable.store_beer, R.drawable.store_coffee, R.drawable.store_ice_cream,
			R.drawable.store_juice,
			R.drawable.data_chinese_banjum, R.drawable.data_hotdog, R.drawable.data_vegetable_store,
			// 36 : 꿀사과, 37 : 당근, 38 : 맥주 무제한,
			R.drawable.data_honey_apple, R.drawable.data_carrot, R.drawable.data_unlimited_beer,
			// 39 : 물고기1, 40 : 물고기2, 41 : 짜장, 42 : 피자1, 43 : 피자2,
			R.drawable.data_fish1, R.drawable.data_fish2, R.drawable.data_black_noddle, R.drawable.data_pizza1, R.drawable.data_pizza2,

			// Other 44 : Logo(cs25), 45 : Logo(du), 46 : Logo(Omart), 47 : 화이팅1, 48 : 화이팅2,
			R.drawable.data_logo_cs25, R.drawable.data_logo_du, R.drawable.data_logo_omart, R.drawable.data_fight1, R.drawable.data_fight2,
			// 49 : Hope1, 50 : Hope2, 51 : 라이언1, 52 : 라이언2,
			R.drawable.data_hope1, R.drawable.data_hope2, R.drawable.data_ryan1, R.drawable.data_ryan2,
			// 53 : 월세, 54 : 전세, 55 : 급매, 	 56 : Hello1 , 57 : Hello2
			R.drawable.data_monthly_rent, R.drawable.data_lease_rent, R.drawable.data_sudden_sale, R.drawable.data_hello1, R.drawable.data_hello2,

			// 3D
//			"동전1", "동전2", "동전3", "동전4", "동전5",
//			"지도1", "지도2", "지도3", "지도4", "지도5",
//			"스마일1", "스마일2", "스마일3", "스마일4"
			R.drawable.ani_coin_1,// R.drawable.ani_coin_2, R.drawable.ani_coin_3, R.drawable.ani_coin_4, R.drawable.ani_coin_5,
			R.drawable.ani_korea_map_1,// R.drawable.ani_korea_map_2, R.drawable.ani_korea_map_3, R.drawable.ani_korea_map_4, R.drawable.ani_korea_map_5,
			R.drawable.ani_smile_1//, R.drawable.ani_smile_2, R.drawable.ani_smile_3, R.drawable.ani_smile_4
	};

	/**
	 * The constant MANIMATION_EMOTICON_LIST.
	 */
	public static final String[] MANIMATION_EMOTICON_LIST = {
			"ani_coin_1;ani_coin_2;ani_coin_3;ani_coin_4;ani_coin_5",
			"ani_korea_map_1;ani_korea_map_2;ani_korea_map_3;ani_korea_map_4;ani_korea_map_5",
			"ani_smile_1;ani_smile_2;ani_smile_3;ani_smile_4"
//			((R.drawable.ani_coin_1) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_coin_2) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_coin_3) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_coin_4) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_coin_5)),
//			((R.drawable.ani_korea_map_1) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_korea_map_2) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_korea_map_3) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_korea_map_4) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_korea_map_5)),
//			((R.drawable.ani_smile_1) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_smile_2) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_smile_3) + FD_DRAW.SPLIT_TOKEN +
//					(R.drawable.ani_smile_4))
	};

	/**
	 * The constant MEMOTICON_CATEGORY_TYPE.
	 */
	public static final int[] MEMOTICON_CATEGORY_TYPE = {
			// Date 1 : 사랑해 , 2 : Kiss
			CATEGORY_IDX_DATE, CATEGORY_IDX_DATE,
			// Driver
			// Holiday 3 : 여행, 4 : 야자수, 5 : 여름휴가
			CATEGORY_IDX_HOLIDAY, CATEGORY_IDX_HOLIDAY, CATEGORY_IDX_HOLIDAY,
			// Warning 6 : 조심1 , 7 : 조심2 , 8 : 정지2, 9 : 화기조심
			CATEGORY_IDX_WARNING, CATEGORY_IDX_WARNING, CATEGORY_IDX_WARNING, CATEGORY_IDX_WARNING,
			// Text 10 : 욜로 , 11 : Welcome
			CATEGORY_IDX_TEXT, CATEGORY_IDX_TEXT,
			// COVID 12 : 2m거리유지 , 13 : 마스크, 14 : 손씻기, 15 : 바이러스
			CATEGORY_IDX_C19, CATEGORY_IDX_C19, CATEGORY_IDX_C19, CATEGORY_IDX_C19,
			// Show  16 : Good
			CATEGORY_IDX_SHOW,
			// Country 17 : 브라질 , 18 : 중국 , 19 : 프랑스, 20 : 독일,
			//         21 : 이태리, 22 : 일번, 23 : 한국, 24 : 미국
			CATEGORY_IDX_NATION, CATEGORY_IDX_NATION, CATEGORY_IDX_NATION, CATEGORY_IDX_NATION,
			CATEGORY_IDX_NATION, CATEGORY_IDX_NATION, CATEGORY_IDX_NATION, CATEGORY_IDX_NATION,
			// Animal 25 : 고양이, 26 : 닭, 27 : 강아지, 28 : 사자
			CATEGORY_IDX_ANIMAL, CATEGORY_IDX_ANIMAL, CATEGORY_IDX_ANIMAL, CATEGORY_IDX_ANIMAL,
			// Shop  29 : 맥주, 30 : 커피, 31 : 아이스크림,
			//       32 : 쥬스,
			// 33 : 중국반점, 34 : 핫도그, 35 : 채소가게
			// 36 : 꿀사과, 37 : 당근, 38 : 맥주 무제한,
			// 39 : 물고기1, 40 : 물고기2, 41 : 짜장, 42 : 피자1, 43 : 피자2,
			CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP,
			CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP,
			CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP,
			CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP, CATEGORY_IDX_SHOP,

			// Other 44 : Logo(cs25), 45 : Logo(du), 46 : Logo(Omart), 47 : 화이팅1, 48 : 화이팅2,
			CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER,
			// 49 : Hope1, 50 : Hope2, 51 : 라이언1, 52 : 라이언2,
			CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER,
			// 53 : 월세, 54 : 전세, 55 : 급매, 	 56 : Hello1 , 57 : Hello2
			CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER, CATEGORY_IDX_OTHER,

			CATEGORY_IDX_ANIMATION,// CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D,
			CATEGORY_IDX_ANIMATION,// CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D,
			CATEGORY_IDX_ANIMATION//, CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D
	};

	public static final int[] MEMOTICON_IMG_SIZE_TYPE = {
			// Date 1 : 사랑해 , 2 : Kiss
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3,
			// Driver

			// Holiday 3 : 여행, 4 : 야자수, 5 : 여름휴가
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3,
			// Warning 6 : 조심1 , 7 : 조심2 , 8 : 정지2, 9 : 화기조심
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3,
			// Text 10 : 욜로 , 11 : Welcome
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_1,
			// COVID 12 : 2m거리유지 , 13 : 마스크, 14 : 손씻기, 15 : 바이러스
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3,
			// Show  16 : Good
			MD_Emoticon.MSIZE_TYPE_3,
			// Country 17 : 브라질 , 18 : 중국 , 19 : 프랑스, 20 : 독일,
			//         21 : 이태리, 22 : 일번, 23 : 한국, 24 : 미국
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3,
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3,
			// Animal 25 : 고양이, 26 : 닭, 27 : 강아지, 28 : 사자
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3,
			// Shop  29 : 맥주, 30 : 커피, 31 : 아이스크림,
			//       32 : 쥬스,
			// 33 : 중국반점, 34 : 핫도그, 35 : 채소가게
			// 36 : 꿀사과, 37 : 당근, 38 : 맥주 무제한,
			// 39 : 물고기1, 40 : 물고기2, 41 : 짜장, 42 : 피자1, 43 : 피자2,
			MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3, MD_Emoticon.MSIZE_TYPE_3,
			MD_Emoticon.MSIZE_TYPE_3,
			MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1,
			MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1,
			MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1,

			// Other 44 : Logo(cs25), 45 : Logo(du), 46 : Logo(Omart), 47 : 화이팅1, 48 : 화이팅2,
			MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1,
			// 49 : Hope1, 50 : Hope2, 51 : 라이언1, 52 : 라이언2,
			MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1,
			// 53 : 월세, 54 : 전세, 55 : 급매, 	 56 : Hello1 , 57 : Hello2
			MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1, MD_Emoticon.MSIZE_TYPE_1,

			MD_Emoticon.MSIZE_TYPE_3,// CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D,
			MD_Emoticon.MSIZE_TYPE_3,// CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D,
			MD_Emoticon.MSIZE_TYPE_3//, CATEGORY_IDX_3D, CATEGORY_IDX_3D, CATEGORY_IDX_3D
	};

	public static final int mGetSize(int _size_type, int _xay) {
		if(_size_type == MD_Emoticon.MSIZE_TYPE_1) {
			if(_xay == MD_Emoticon.MSIZE_TYPE_IDX_X)
				return MD_Emoticon.MSIZE_64;
			else
				return MD_Emoticon.MSIZE_32;
		} else if(_size_type == MD_Emoticon.MSIZE_TYPE_2) {
			if(_xay == MD_Emoticon.MSIZE_TYPE_IDX_X)
				return MD_Emoticon.MSIZE_32;
			else
				return MD_Emoticon.MSIZE_64;
		} else if(_size_type == MD_Emoticon.MSIZE_TYPE_3) {
			if(_xay == MD_Emoticon.MSIZE_TYPE_IDX_X)
				return MD_Emoticon.MSIZE_32;
			else
				return MD_Emoticon.MSIZE_32;
		} else if(_size_type == MD_Emoticon.MSIZE_TYPE_4) {
			if(_xay == MD_Emoticon.MSIZE_TYPE_IDX_X)
				return MD_Emoticon.MSIZE_128;
			else
				return MD_Emoticon.MSIZE_32;
		}

		return MAPP.ERROR_;
	}
}
