package org.tinggame.herostory.msg;

import com.google.protobuf.GeneratedMessageV3;
import com.heroStory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.tinggame.herostory.cmdHandler.*;
import org.tinggame.herostory.model.User;
import org.tinggame.herostory.model.UserManage;

import java.util.HashMap;

/**
 * @ Author :cloudy
 * @ Date   :Created in 17:38 2020/4/10
 * @ Description:
 */
public class GameMsgHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        BroadCaster.addChannel(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);

        BroadCaster.removeChannel(ctx.channel());

        //先拿到用户Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (null == userId){
            return;
        }
        UserManage.RemoveUserById(userId);

        GameMsgProtocol.UserQuitResult.Builder resultBuilder = GameMsgProtocol.UserQuitResult.newBuilder();
        resultBuilder.setQuitUserId(userId);
        GameMsgProtocol.UserQuitResult result = resultBuilder.build();
        BroadCaster.broadcast(result);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("收到客户端消息,msgClazz=:"+msg.getClass().getName()+",msg="+msg);
        ICmdHandler<? extends GeneratedMessageV3> cmdHandler = CmdHandlerFactory.create(msg);
        if(null != cmdHandler){
            cmdHandler.handle(channelHandlerContext, cast(msg));
        }
    }

    private static <Tcmd extends GeneratedMessageV3> Tcmd cast(Object msg){
        if(msg ==null){
            return null;
        }else {
            return (Tcmd) msg;
        }

    }
}
