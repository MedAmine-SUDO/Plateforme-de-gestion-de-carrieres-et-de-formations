package com.profiledataservice.tn.models;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
	@Id
	private String id;
	 	
	private String userID;
	 
	@Size(max = 20)
	private String firstName;
	 
	@Size(max = 20)
	private String lastName;
	 
	@Size(max = 20)
	private Date birthDate;
	 
	@Size(max = 50)
	private String address;
	 
	@Size(max = 20)
	private int postCode;
	 
	@Size(max = 30)
	private String region;
	 
	@Size(max = 20)
	private int telephone;
	 
	@Size(max = 30)
	private String country;
	private String idCompetence;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getIdCompetence() {
		return idCompetence;
	}
	public void setIdCompetence(String idCompetence) {
		this.idCompetence = idCompetence;
	}
	@Override
	public String toString() {
		return "Profile [id=" + id + ", userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthDate=" + birthDate + ", address=" + address + ", postCode=" + postCode + ", region=" + region
				+ ", telephone=" + telephone + ", country=" + country + ", idCompetence=" + idCompetence + "]";
	}
	
}
