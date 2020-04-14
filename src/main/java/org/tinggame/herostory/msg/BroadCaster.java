package org.tinggame.herostory.msg;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * @ Author :cloudy
 * @ Date   :Created in 11:32 2020/4/14
 * @ Description: 广播员
 */
public final class BroadCaster {

    private static final ChannelGroup _channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private BroadCaster() {
    }

    /**
     * 添加新到
     * @param channel
     */
    static public void addChannel(Channel channel){
        _channelGroup.add(channel);
    }

    /**
     * 移除新到
     * @param channel
     */
    static public void removeChannel(Channel channel){
        _channelGroup.remove(channel);
    }

    static public void broadcast(Object msg) {
        if (msg == null){
            return;
        }
        _channelGroup.writeAndFlush(msg);
    }
}
