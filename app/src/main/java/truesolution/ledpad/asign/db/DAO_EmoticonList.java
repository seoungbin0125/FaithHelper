package truesolution.ledpad.asign.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by TCH on 2020. 07. 01.
 *
 * @author think.code.help@gmail.com
 * @version 1.0
 * @since 2020. 07. 01.
 */
@Dao
public interface DAO_EmoticonList {
	// LIMIT 100
	@Query("SELECT * FROM MD_Icon ORDER BY date_ DESC LIMIT 999")
	List<MD_Icon> mGetAll();
	
	@Insert
	void insertIcon(MD_Icon... md_);
	
	@Delete
	void delete(MD_Icon... md_);
}