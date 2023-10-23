package org.example.Service.ServiceImpl;


import org.example.Dao.DaoImpl.UserDaoImpl;
import org.example.Dao.UserDao;
import org.example.Service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public Boolean existByEmail(String email) {
        return userDao.existByEmail(email);
    }
}
