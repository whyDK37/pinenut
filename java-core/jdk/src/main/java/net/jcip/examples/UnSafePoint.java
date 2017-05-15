package net.jcip.examples;


class UnSafePoint {
    private int x;
    private int y;

    public UnSafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public UnSafePoint(UnSafePoint safePoint) {
        this(safePoint.x, safePoint.y);
    }

    /*
     * this is a refactored method, instead of a constructor
     */
    public UnSafePoint cloneSafePoint(UnSafePoint originalSafePoint) {
        int[] xy = originalSafePoint.getXY();
        return new UnSafePoint(xy[0], xy[1]);
    }

    public synchronized int[] getXY() {
        return new int[]{x, y};
    }

    public synchronized void setXY(int x, int y) {
        this.x = x;
        //Simulate some resource intensive work that starts EXACTLY at this point, causing a small delay
        try {
            Thread.sleep(10 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "UnSafePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}