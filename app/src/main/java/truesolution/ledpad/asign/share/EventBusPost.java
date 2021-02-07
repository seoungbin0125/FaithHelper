package truesolution.ledpad.asign.share;

public class EventBusPost {
    private String message;

    public static class WifiInfo {
        private boolean connectOK;

        public WifiInfo(boolean connectOK) {
            this.connectOK = connectOK;
        }

        public boolean getConnectResult() {
            return connectOK;
        }
    }

    public static class BoardSettingInfo {
        private byte[] boardSettingInfo = new byte[7];

        public BoardSettingInfo(byte[] boardSettingInfo) {
            this.boardSettingInfo = boardSettingInfo;
        }
        public byte[] getSettingInfo() {
            return boardSettingInfo;
        }
    }
    public static class BatteryInfo {
        private int batteryVal;

        public BatteryInfo(int batteryVal) {
            this.batteryVal = batteryVal;
//            MDEBUG.debug("this.batteryVal" + this.batteryVal);
        }

        public int getBatteryVal() {
//            MDEBUG.debug("return batteryVal" + batteryVal);
            return batteryVal;
        }
    }
}
