package com.cobra.repository.mongoDb;

import com.cobra.models.Role;
import com.cobra.repository.RoleDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRoleDao extends RoleDao, MongoRepository<Role, String> {
}