// Person.java

public class Person {
	private String sei;
	private String mei;
	private String eMail;

	public Person( String sei, String mei, String eMail){
		this.sei = sei;
		this.mei = mei;
		this.eMail = eMail;
	}

	public String getSei() {
		return sei;
	}
	public void setSei(String sei) {
		this.sei = sei;
	}
	public String getMei() {
		return mei;
	}
	public void setMei(String mei) {
		this.mei = mei;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
}
