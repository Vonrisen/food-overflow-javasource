package entities;

public class Address {

	private String address;
	private String civic_number;
	private String cap;
	private String city;
	private String province;
	
	public Address(String address, String civic_number, String cap, String city, String province) {

		this.address = address;
		this.civic_number = civic_number;
		this.cap = cap;
		this.city = city;
		this.province = province;

	}

	public String getCivic_number() {
		return civic_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCivic_number(String civic_number) {
		this.civic_number = civic_number;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String toString() {
		return address + ", " + civic_number + ", " + cap + ", " + city + ", " + province;
	}
	

}
