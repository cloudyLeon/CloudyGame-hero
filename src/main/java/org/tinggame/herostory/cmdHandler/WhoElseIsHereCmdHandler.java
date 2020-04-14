package org.tinggame.herostory.cmdHandler;

import com.heroStory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import org.tinggame.herostory.model.User;
import org.tinggame.herostory.model.UserManage;

/**
 * @ Author :cloudy
 * @ Date   :Created in 16:42 2020/4/14
 * @ Description: 是否有已登入用户
 */
public class WhoElseIsHereCmdHandler implements ICmdHandler<GameMsgProtocol.WhoElseIsHereCmd> {
    @Override
    public  void handle(ChannelHandlerContext channelHandlerContext, GameMsgProtocol.WhoElseIsHereCmd msg) {
        GameMsgProtocol.WhoElseIsHereCmd cmd = msg;
        GameMsgProtocol.WhoElseIsHereResult.Builder resultBuilder = GameMsgProtocol.WhoElseIsHereResult.newBuilder();
        for(User curUser : UserManage.getAllUser()){
            if(null == curUser){
                continue;
            }
            GameMsgProtocol.WhoElseIsHereResult.UserInfo.Builder userInfoBuilder = GameMsgProtocol.WhoElseIsHereResult.UserInfo.newBuilder();
            userInfoBuilder.setUserId(curUser.userId);
            userInfoBuilder.setHeroAvatar(curUser.userAvator);

            resultBuilder.addUserInfo(userInfoBuilder);
        }

        GameMsgProtocol.WhoElseIsHereResult result = resultBuilder.build();
        channelHandlerContext.writeAndFlush(result);
    }
}
