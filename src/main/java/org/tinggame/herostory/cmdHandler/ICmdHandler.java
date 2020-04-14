package org.tinggame.herostory.cmdHandler;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;

public interface ICmdHandler<Tcmd extends GeneratedMessageV3> {
    void handle(ChannelHandlerContext context, Tcmd msg);
}
