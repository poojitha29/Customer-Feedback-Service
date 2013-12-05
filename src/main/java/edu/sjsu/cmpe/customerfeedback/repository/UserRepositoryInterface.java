package edu.sjsu.cmpe.customerfeedback.repository;

import edu.sjsu.cmpe.customerfeedback.domain.User;

public interface UserRepositoryInterface {

	boolean saveUser(User user);

	User getUserbyUserName(String userName);

}
