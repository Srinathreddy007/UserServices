package com.example.userservice.Repository;

import com.example.userservice.model.User;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends DatastoreRepository<User,Long>
{

    public User findByEmail(String user);
    public User findByEmailAndPassword(String email,String password);
    public List<User> findAll();

}
