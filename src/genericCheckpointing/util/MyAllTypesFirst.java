package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject {
	int myInt;
	long myLong;
	String myString;
	boolean myBool;
	int myOtherInt;
	long myOtherLong;
	
	public MyAllTypesFirst() {
		super();
	}

	public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn, int myOtherIntIn, long myOtherLongIn) {
		super();
		this.myInt = myIntIn;
		this.myLong = myLongIn;
		this.myString = myStringIn;
		this.myBool = myBoolIn;
		this.myOtherInt = myOtherIntIn;
		this.myOtherLong = myOtherLongIn;
	}

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	public long getMyLong() {
		return myLong;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public boolean isMyBool() {
		return myBool;
	}

	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}

	public int getMyOtherInt() {
		return myOtherInt;
	}

	public void setMyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}

	public long getMyOtherLong() {
		return myOtherLong;
	}

	public void setMyOtherLong(long myOtherLong) {
		this.myOtherLong = myOtherLong;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MyAllTypesFirst)) {
			return false;
		} else {
			MyAllTypesFirst myFirst = (MyAllTypesFirst) obj;
			if(myFirst.myInt < 10) {
				myFirst.myInt = 0;
			}
			if(myFirst.myOtherInt < 10) {
				myFirst.myOtherInt = 0;
			}
			if(myFirst.myLong < 10) {
				myFirst.myLong = 0;
			}
			if(myFirst.myOtherLong < 10) {
				myFirst.myOtherLong = 0;
			}
			if ((myFirst.myBool == myBool) && (myFirst.myInt == myInt) && (myFirst.myLong == myLong)
					&& (myFirst.myOtherInt == myOtherInt) && (myFirst.myString.equals(myString)) && (myFirst.myOtherLong == myOtherLong)) {
				return true;
			}
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (myBool ? 1231 : 1237);
		result = prime * result + myInt;
		result = prime * result + (int) (myLong ^ (myLong >>> 32));
		result = prime * result + myOtherInt;
		result = prime * result + (int) (myOtherLong ^ (myOtherLong >>> 32));
		result = prime * result + ((myString == null) ? 0 : myString.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "MyAllTypesFirst [myInt=" + myInt + ", myLong=" + myLong + ", myString=" + myString + ", myBool="
				+ myBool + ", myOtherInt=" + myOtherInt + ", myOtherLong=" + myOtherLong + "]";
	}

}
