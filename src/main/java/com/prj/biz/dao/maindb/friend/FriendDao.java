package com.prj.biz.dao.maindb.friend;

import java.util.List;

import com.prj.biz.bean.friend.Friend;
import com.prj.biz.dao._base.BaseDao;

/**
 * 
 * 描述: 好友列表 Dao 接口<br>
 * @author Liang
 * @date 2017-08-26
 */
public interface FriendDao extends BaseDao<Friend>
{

	/**
	 * 好友申请列表
	 * @param friend
	 * @return
	 */
	public List<Friend> selectListNewFriend(Friend friend);
}