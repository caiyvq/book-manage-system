package model;

public class Card {
	private int cardNo;
	private String username;
	private int amount;
	
	
	public Card() {
		super();
	}

	public Card(int cardNo, String username) {
		super();
		this.cardNo = cardNo;
		this.username = username;
	}
	
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
