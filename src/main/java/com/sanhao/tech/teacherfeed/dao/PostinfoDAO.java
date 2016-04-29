package com.sanhao.tech.teacherfeed.dao;

import java.math.BigDecimal;
import java.util.List;

import com.sanhao.tech.teacherfeed.model.Postinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * 用户表相关操�?
 * @author sanhao
 *
 */
public interface PostinfoDAO {
	@Select("SELECT `id`, `pacname` as `packageName` ,`version`,`title`,`zip_source` as `downloadUrl`,`theme_url` as `previewImageUrl`  FROM `postinfo` where `status`=1")
	List<Postinfo> getPostinfos();

	@Select("SELECT `id`, `pacname` as `packageName` ,`version`,`title`,`zip_source` as `downloadUrl`,`theme_url` as `previewImageUrl`  FROM `postinfo` where `status`=1 and `id`=#{id}")
	Postinfo getPostinfo(@Param("id")int id);
}
