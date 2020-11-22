package entity;

/**
 * @author yintianhao
 * @createTime 2020/11/22 17:06
 * @description 消息类
 */
public class Message {
    /**
     * 消息id
     * */
    private long msgId;
    /**
     * 发送人的消息userid
     */
    private long sourceUserId;
    /**
     * 接收人的userid
     * */
    private long destUserId;

    private Message(MessageBuilder buider){
        this.msgId = buider.msgId;
        this.destUserId = buider.destUserId;
        this.sourceUserId = buider.sourceUserId;
    }

    public static class MessageBuilder{

        private long msgId;

        private long sourceUserId;

        private long destUserId;

        public MessageBuilder msgId(long msgId){
            this.msgId = msgId;
            return this;
        }
        public MessageBuilder sourceUserId(long sourceUserId){
            this.sourceUserId = sourceUserId;
            return this;
        }
        public MessageBuilder destUserId(long destUserId){
            this.destUserId = destUserId;
            return this;
        }
        public Message build(){
            return new Message(this);
        }
    }

}
