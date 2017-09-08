package com.prj.biz.service._impl.friend;

import java.util.List;

import com.prj.biz.bean.friend.Friend;
import com.prj.biz.dao.maindb.friend.FriendDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.friend.FriendService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 好友列表 Service 实现<br>
 * @author Liang
 * @date 2017-08-26
 */
@Service
public class FriendServiceImpl extends BaseServiceImpl<FriendDao,Friend> implements FriendService
{

	@Override
	public List<Friend> selectListNewFriend(Friend friend) {
		// TODO Auto-generated method stub
		return genDao.selectListNewFriend(friend);
	}
	

}
