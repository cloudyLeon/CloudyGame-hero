package org.tinggame.herostory.cmdHandler;

import com.heroStory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.tinggame.herostory.model.User;
import org.tinggame.herostory.model.UserManage;
import org.tinggame.herostory.msg.BroadCaster;

/**
 * @ Author :cloudy
 * @ Date   :Created in 16:39 2020/4/14
 * @ Description: 登入处理器
 */
public class UserEntryCmdHandler implements ICmdHandler<GameMsgProtocol.UserEntryCmd> {
    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, GameMsgProtocol.UserEntryCmd msg) {
        //从指令对象中获取用户id和英雄形象
        GameMsgProtocol.UserEntryCmd cmd = msg;
        int userId = cmd.getUserId();
        String avator = cmd.getHeroAvatar();
        /*
         *   将新用户添加到用户字典中
         */
        User user = new User();
        user.userId = userId;
        user.userAvator = avator;
        UserManage.addUser(user);

        channelHandlerContext.channel().attr(AttributeKey.valueOf("userId")).set(userId);

        GameMsgProtocol.UserEntryResult.Builder resultBuilder = GameMsgProtocol.UserEntryResult.newBuilder();
        resultBuilder.setUserId(userId);
        resultBuilder.setHeroAvatar(avator);

        //构建结果并发送
        GameMsgProtocol.UserEntryResult result = resultBuilder.build();
        BroadCaster.broadcast(result);
    }
}
