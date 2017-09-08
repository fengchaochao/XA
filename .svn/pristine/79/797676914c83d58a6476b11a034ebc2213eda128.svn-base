package com.prj.core.shiro;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import com.prj.core.constant.SysConstants;


/** 
* @Description: TODO
* @date 2016年7月29日 
* @author 1936
*/
public class UFDMSessionDao extends CachingSessionDAO{

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    protected Serializable doCreate(Session session) {
    	
    	System.out.println("\n 创建 session : " + session.toString());
    	
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        
        System.out.println("\n 创建 session : " + session);
        
        String sql = "insert into per_sessions(id, session) values(?,?)";
        jdbcTemplate.update(sql, sessionId, SerializableUtils.serialize(session));
        return session.getId();
    }
    
    @Override
    protected void doUpdate(Session session) {
    	
    	System.out.println("\n 更新  session : " + session);
    	
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
        
        String sql = "update per_sessions set session=? where id=?";
        jdbcTemplate.update(sql, SerializableUtils.serialize(session), session.getId());
    }
    @Override
    protected void doDelete(Session session) {
    	
    	System.out.println("\n 删除 session : " + session);
    	
        String sql = "delete from per_sessions where id=?";
        jdbcTemplate.update(sql, session.getId());
    }
    
    @Override
    public Session doReadSession(Serializable sessionId) {
    	
    	
    	System.out.println("\n 获取 session : " + sessionId);
    	
    	
        String sql = "select session from per_sessions where id=?";
        List<String> sessionStrList = jdbcTemplate.queryForList(sql, String.class, sessionId);
        if(sessionStrList.size() == 0) return null;
        
        Session rtnSession = SerializableUtils.deserialize(sessionStrList.get(0));
        
        //System.out.println("\n 获取 session sessionSysUser : " + rtnSession.getAttribute(SysConstants.SESSION_SYS_USER));
        
        return rtnSession;
    }

}
