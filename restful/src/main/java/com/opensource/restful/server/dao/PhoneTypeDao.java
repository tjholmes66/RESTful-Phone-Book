package com.opensource.restful.server.dao;

import java.util.List;

import com.opensource.restful.domain.PhoneTypeEntity;

public interface PhoneTypeDao {
	
	public PhoneTypeEntity savePhoneTypeEntity(PhoneTypeEntity phoneType);
	
	public void deletePhoneTypeEntity(Long phoneTypeId);
	public void deletePhoneTypeEntity(PhoneTypeEntity phoneType);
	
	public List<PhoneTypeEntity> getAllPhoneTypeEntitys();
	
	// Retrieve
	public PhoneTypeEntity getPhoneTypeEntity(long id);
	public List<PhoneTypeEntity> getPhoneTypeEntity(PhoneTypeEntity exampleEntity);
	
}
