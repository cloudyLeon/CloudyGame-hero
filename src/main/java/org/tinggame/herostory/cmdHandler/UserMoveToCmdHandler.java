package org.tinggame.herostory.cmdHandler;

import com.heroStory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.tinggame.herostory.msg.BroadCaster;

/**
 * @ Author :cloudy
 * @ Date   :Created in 16:44 2020/4/14
 * @ Description:
 */
public class UserMoveToCmdHandler implements ICmdHandler<GameMsgProtocol.UserMoveToCmd> {
    @Override
    public  void handle(ChannelHandlerContext channelHandlerContext, GameMsgProtocol.UserMoveToCmd msg) {
        Integer uid = (Integer) channelHandlerContext.channel().attr(AttributeKey.valueOf("userId")).get();
        if(null == uid) {
            return;
        }
        GameMsgProtocol.UserMoveToCmd cmd = msg;
        GameMsgProtocol.UserMoveToResult.Builder resultBuilder = GameMsgProtocol.UserMoveToResult.newBuilder();
        resultBuilder.setMoveUserId(uid);
        resultBuilder.setMoveToPosX(cmd.getMoveToPosX());
        resultBuilder.setMoveToPosY(cmd.getMoveToPosY());
        GameMsgProtocol.UserMoveToResult result = resultBuilder.build();
        BroadCaster.broadcast(result);
    }
}
