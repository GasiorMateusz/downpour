package com.cobra.repository.mongoDb;

import com.cobra.models.User;
import com.cobra.repository.UserDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserDao extends UserDao, MongoRepository<User, String> {

    @Override
    default User save(User user) {
        return insert(user);
    }
}
