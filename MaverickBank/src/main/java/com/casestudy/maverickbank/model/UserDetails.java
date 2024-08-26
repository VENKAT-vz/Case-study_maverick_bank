package com.casestudy.maverickbank.model;

public class UserDetails {
	    private String firstname;
	    private String lastname;
	    private String gender;
	    private String contactNumber;
	    private String address;
	    private String city;
	    private String state;
		private String username;
	    private String password;
	    
	    public UserDetails() {
	    	
	    }
	    

		public UserDetails(String firstname, String lastname, String gender, String contactNumber, String address,
				String city, String state, String username, String password) {
			this.firstname = firstname;
			this.lastname = lastname;
			this.gender = gender;
			this.contactNumber = contactNumber;
			this.address = address;
			this.city = city;
			this.state = state;
			this.username = username;
			this.password = password;
		}


		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
					+ ", contactNumber=" + contactNumber + ", address=" + address + ", city=" + city + ", state="
					+ state + ", username=" + username + ", password=" + password + "]";
		}



}
