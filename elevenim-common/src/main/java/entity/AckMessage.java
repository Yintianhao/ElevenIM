package entity;

import enums.AckMsgType;

/**
 * @author yintianhao
 * @createTime 2020/11/22 17:17
 * @description 消息回执
 */
public class AckMessage {

    /**
     * 此消息id
     * */
    private long msgId;

    /**
     * 被确认的消息的id
     * */
    private long ackedMsgId;

    /**
     * 发送人的消息userid
     */
    private long sourceUserId;
    /**
     * 接收人的userid
     * */
    private long destUserId;
    /**
     * 回执消息类型
     */
    private AckMsgType type;


    private AckMessage(AckMessageBuilder builder){
        this.msgId = builder.msgId;
        this.ackedMsgId = builder.ackedMsgId;
        this.destUserId = builder.destUserId;
        this.sourceUserId = builder.sourceUserId;
    }

    public static class AckMessageBuilder {

        private long msgId;

        private long ackedMsgId;

        private long sourceUserId;

        private long destUserId;

        private AckMsgType type;

        public AckMessageBuilder msgId(long msgId){
            this.msgId = msgId;
            return this;
        }
        public AckMessageBuilder ackedMsgId(long ackedMsgId){
            this.ackedMsgId = ackedMsgId;
            return this;
        }
        public AckMessageBuilder sourceUserId(long sourceUserId){
            this.sourceUserId = sourceUserId;
            return this;
        }
        public AckMessageBuilder destUserId(long destUserId){
            this.destUserId = destUserId;
            return this;
        }
        public AckMessageBuilder type(AckMsgType type){
            this.type = type;
            return this;
        }

        public AckMessage build(){
            return new AckMessage(this);
        }
    }
}
