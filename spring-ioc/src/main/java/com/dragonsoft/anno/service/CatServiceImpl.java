/**  
 * @Title: CatServiceImpl.java  
 * @Package com.dragonsoft.spring.anno.service  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author ronin  
 * @date 2019年3月20日  
 * @version V1.0  
 */ 
package com.dragonsoft.anno.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dragonsoft.anno.dao.CatDao;

/**  
 * @ClassName: CatServiceImpl  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author ronin  
 * @date 2019年3月20日  
 */

@Component(value="catService")
public class CatServiceImpl implements ICatService{

	//@Autowired
	@Resource(name="catDao")
	private CatDao catDao;

	public void eat() {
		catDao.eat();
	}

}
