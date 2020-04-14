package org.tinggame.herostory.model;

import java.util.Collection;
import java.util.HashMap;

/**
 * @ Author :cloudy
 * @ Date   :Created in 11:51 2020/4/14
 * @ Description: 用户管理
 */
public final class UserManage {
    private static final HashMap<Integer, User> usersMap = new HashMap<>();

    private UserManage() {
    }

    public static void addUser(User user){
        if(null != user){
            usersMap.put(user.userId, user);
        }
    }

    /**
     * 所有用户
     * @return
     */
    public static Collection<User> getAllUser(){
        return usersMap.values();
    }

    public static void RemoveUserById(int userId){
        usersMap.remove(userId);
    }
}
