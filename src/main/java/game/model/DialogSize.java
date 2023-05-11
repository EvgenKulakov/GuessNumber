package game.model;
/**
 * градация по размерам ShowDialog
 * и данные для выравнивания
 */
public enum DialogSize {

    ONE_SIZE(132.8000030517578, 271.5, 320.0),
    TWO_SIZE(112.80000305175781, 271.5, 360.0),
    THREE_SIZE(112.80000305175781, 265.9000015258789, 360),
    FOUR_SIZE(74.39999389648438, 257.5, 360.0);


    private final double plusX;
    private final double plusY;
    private final double minWindow;

    DialogSize(double plusX, double plusY, double minWindow) {
        this.plusX = plusX;
        this.plusY = plusY;
        this.minWindow = minWindow;
    }

    public double getPlusX() {
        return plusX;
    }
    public double getPlusY() {
        return plusY;
    }
    public double getMinWindow() {
        return minWindow;
    }
}
