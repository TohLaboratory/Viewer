public class nodeState {
    private int state;  // -1:null, 0:leaf, 1:圧縮可能, 2:展開可能 3: 数制限のため展開不可
    private double x, y;

    void setState(int state) {
	this.state = state;
    }

    void setCoordinates(double x, double y) {
        this.x = x;
	this.y = y;
    }

    int getState() {
	return state;
    }

    double getX()  {
	return x;
    }

    double getY()  {
	return y;
    }
}
