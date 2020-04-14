package org.tinggame.herostory.cmdHandler;

import com.google.protobuf.GeneratedMessageV3;
import com.heroStory.msg.GameMsgProtocol;

/**
 * @ Author :cloudy
 * @ Date   :Created in 17:05 2020/4/14
 * @ Description: cmdhandler工厂
 */
public final class CmdHandlerFactory {

    private CmdHandlerFactory() {
    }

    public static  ICmdHandler<? extends GeneratedMessageV3> create(Object msg){
        if(msg instanceof GameMsgProtocol.UserEntryCmd){
            return new UserEntryCmdHandler();
        }else if (msg instanceof GameMsgProtocol.WhoElseIsHereCmd){
            return new WhoElseIsHereCmdHandler();
        }else if (msg instanceof GameMsgProtocol.UserMoveToCmd){
            return new UserMoveToCmdHandler();
        }else {
            return null;
        }
    }
}
