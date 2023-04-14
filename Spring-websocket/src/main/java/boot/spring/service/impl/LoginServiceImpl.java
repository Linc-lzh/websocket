package boot.spring.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import boot.spring.mapper.LoginMapper;
import boot.spring.po.Staff;
import boot.spring.service.LoginService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service("loginservice")
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginMapper loginmapper;
	
	@Override
	public String getpwdbyname(String name) {
		Staff s=loginmapper.getpwdbyname(name);
		if(s!=null) {
			return s.getPassword();
		}
		return null;
	}

	@Override
	public Long getUidbyname(String name) {
		Staff s = loginmapper.getpwdbyname(name);
		if (s != null){
			return (long) s.getStaff_id();
		}
		return null;
	}

	@Override
	public String getnamebyid(long id) {
		Staff s=loginmapper.getnamebyid(id);
		if(s!=null) {
			return s.getUsername();
		}
		return null;
	}
	
	

}
