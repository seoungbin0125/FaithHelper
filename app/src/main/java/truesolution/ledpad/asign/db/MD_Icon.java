package truesolution.ledpad.asign.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by TCH on 2020. 07. 01.
 *
 * @author think.code.help@gmail.com
 * @version 1.0
 * @since 2020. 07. 01.
 */
@Entity
public class MD_Icon {
	@PrimaryKey(autoGenerate = true)
	public int idx_;
	
	@ColumnInfo(name = "title_")
	public String mTitle;
	
	@ColumnInfo(name = "catergory_")
	public String mCatergory;
	
	@ColumnInfo(name = "path_")
	public String mPath;
	
	@ColumnInfo(name = "name_")
	public String mName;
	
	@ColumnInfo(name = "description_")
	public String mDescription;
	
	@ColumnInfo(name = "date_")
	public String mDate;
}