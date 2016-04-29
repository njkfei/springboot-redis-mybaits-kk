package com.sanhao.tech.teacherfeed.web;

import com.alibaba.fastjson.JSON;
import com.sanhao.tech.teacherfeed.dao.PostinfoDAO;
import com.sanhao.tech.teacherfeed.model.Postinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static java.awt.Color.red;
import static java.awt.SystemColor.info;

@RestController
@EnableAutoConfiguration
public class KKContoller
{
	private static final Logger logger = LoggerFactory.getLogger("teacherfeed");

	@Autowired
	private PostinfoDAO postinfoDAO;

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, Postinfo> redisTemplate;

	// inject the template as ListOperations
	// can also inject as Value, Set, ZSet, and HashOperations
	@Resource(name="redisTemplate")
	private ListOperations<String, Postinfo> listOps;

	@Resource(name="redisTemplate")
	private SetOperations<String,String> setOps;

	@Resource(name="redisTemplate")
	private HashOperations<String,String ,String> hashOps;
	//private HashOperations<String,String ,TeacherRoom> hashOps;

	@Resource(name="redisTemplate")
	private ValueOperations<String,String> kvOps1;

	@Resource(name="redisTemplate")
	private ValueOperations<String,String> kvOps2;

	@RequestMapping("/postinfos")
	public String postinfos(){
		logger.info("postinfos start ==========>");

		String infos = kvOps1.get("postinfos");

		if( infos == null || infos.isEmpty()) {
			logger.info("postinfos from mysql ==========>");
			List<Postinfo> lists = postinfoDAO.getPostinfos();

			kvOps1.set("postinfos", JSON.toJSONString(lists));

			return JSON.toJSONString(lists);
		}
		logger.info("postinfos from redis ==========>");
		logger.info("postinfos end ==========>");

		return infos;
	}

	@RequestMapping("/postinfo/{id}")
	public String  postinfo(@PathVariable int id){
		logger.info("postinfo start ==========>");
		String info = kvOps1.get("postinfo"+id);

		if( info == null || info.isEmpty()) {
			logger.info("postinfos from mysql ==========>");
			Postinfo item = postinfoDAO.getPostinfo(id);

			kvOps1.set("postinfo"+id, JSON.toJSONString(item));

			return JSON.toJSONString(item);
		}
		logger.info("postinfos from redis ==========>");
		logger.info("postinfos end ==========>");

		return info;
	}

	@RequestMapping("/clear")
	public String clear(){
		logger.info("clear redis start ==========>");

		redisTemplate.delete("postinfos");
		redisTemplate.delete("postinfo*");



		logger.info("clear redis ==========>");
		return postinfos();
	}


}
