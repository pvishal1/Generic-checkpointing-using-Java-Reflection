package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {
	double myDoubleT;
	float myFloatT;
	short myShortT;
	char myCharT;
	double myOtherDoubleT;

	public MyAllTypesSecond() {
		super();
	}

	public MyAllTypesSecond(double myDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn, double myOtherDoubleTIn) {
		super();
		this.myDoubleT = myDoubleTIn;
		this.myFloatT = myFloatTIn;
		this.myShortT = myShortTIn;
		this.myCharT = myCharTIn;
		this.myOtherDoubleT = myOtherDoubleTIn;
	}

	public double getMyDoubleT() {
		return myDoubleT;
	}

	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}

	public float getMyFloatT() {
		return myFloatT;
	}

	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}

	public short getMyShortT() {
		return myShortT;
	}

	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
	}

	public char getMyCharT() {
		return myCharT;
	}

	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
	}

	public double getMyOtherDoubleT() {
		return myOtherDoubleT;
	}

	public void setMyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
	}

	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (!(obj instanceof MyAllTypesSecond)) {
			return false;
		} else {
			MyAllTypesSecond mySecond = (MyAllTypesSecond) obj;
			if(mySecond.myDoubleT < 10) {
				mySecond.myDoubleT = 0;
			}
			if(mySecond.myOtherDoubleT < 10) {
				mySecond.myOtherDoubleT = 0;
			}
			if ((mySecond.myCharT == myCharT) && (mySecond.myDoubleT == myDoubleT) && (mySecond.myFloatT == myFloatT)
					&& (mySecond.myShortT == myShortT) && (mySecond.myOtherDoubleT == myOtherDoubleT)) {
				return true;
			}
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + myCharT;
		long temp;
		temp = Double.doubleToLongBits(myDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(myFloatT);
		temp = Double.doubleToLongBits(myOtherDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + myShortT;
		return result;
	}

	@Override
	public String toString() {
		return "MyAllTypesSecond [myDoubleT=" + myDoubleT + ", myFloatT=" + myFloatT + ", myShortT=" + myShortT
				+ ", myCharT=" + myCharT + ", myOtherDoubleT=" + myOtherDoubleT + "]";
	}

}
