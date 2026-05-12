package bean;

import java.io.Serializable;

public class TestListStudent extends School implements Serializable {
	
	private String subjectName;
	private String subjectCd;
	private int num;
	private int point;
	/**
	 * @return subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName セットする subjectName
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * @return subjectCd
	 */
	public String getSubjectCd() {
		return subjectCd;
	}
	/**
	 * @param subjectCd セットする subjectCd
	 */
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	/**
	 * @return num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num セットする num
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point セットする point
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
