package org.tinggame.herostory.cmdHandler;

import com.google.protobuf.GeneratedMessageV3;
import com.heroStory.msg.GameMsgProtocol;

import java.util.HashMap;

/**
 * @ Author :cloudy
 * @ Date   :Created in 17:05 2020/4/14
 * @ Description: cmdhandler工厂
 */
public final class CmdHandlerFactory {

    private static HashMap<Class<?>,ICmdHandler<? extends GeneratedMessageV3>> handlerMap = new HashMap<>();

    public static void init(){
        handlerMap.put(GameMsgProtocol.UserEntryCmd.class, new UserEntryCmdHandler());
        handlerMap.put(GameMsgProtocol.UserMoveToCmd.class, new UserMoveToCmdHandler());
        handlerMap.put(GameMsgProtocol.WhoElseIsHereCmd.class, new WhoElseIsHereCmdHandler());
    }
    private CmdHandlerFactory() {
    }

    public static  ICmdHandler<? extends GeneratedMessageV3> create(Class<?> msgClazz){
        return handlerMap.get(msgClazz);
    }
}
