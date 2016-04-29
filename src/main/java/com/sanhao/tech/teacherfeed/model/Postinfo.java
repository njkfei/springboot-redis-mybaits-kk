package com.sanhao.tech.teacherfeed.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 用户�?
 * @author sanhao
 *	Id       int32 `orm:"pk"`
PackageName string
Version string
Title string
DownloadUrl string
PreviewImageUrl string
 */
@Data
public class Postinfo {
	public int id;
	public String PackageName;
	public String Version;
	public String Title;
	public String DownloadUrl;
	public String PreviewImageUrl;
}
