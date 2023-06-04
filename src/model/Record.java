package model;

public class Record {
	private int rid;
	private int cardNo;
	private String no;
	
	
	public Record() {
		super();
	}
	public Record( int cardNo, String no) {
		super();
		this.cardNo = cardNo;
		this.no = no;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	
}
