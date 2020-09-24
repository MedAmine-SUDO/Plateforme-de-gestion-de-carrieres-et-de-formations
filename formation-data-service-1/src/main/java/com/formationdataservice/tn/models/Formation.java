package com.formationdataservice.tn.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "formations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formation {
	@Id
	private String id;

	private String title;

	private String description;

	private String idFormateurs;

	private String niveau;

	private String idCompetence;

	private Date beginDate;

	private Date endDate;

	private int nbrHours;

	private int price;

	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getNbrHours() {
		return nbrHours;
	}

	public void setNbrHours(int nbrHours) {
		this.nbrHours = nbrHours;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdFormateurs() {
		return idFormateurs;
	}

	public void setIdFormateurs(String idFormateurs) {
		this.idFormateurs = idFormateurs;
	}

	public String getIdCompetence() {
		return idCompetence;
	}

	public void setIdCompetence(String idCompetence) {
		this.idCompetence = idCompetence;
	}

	@Override
	public String toString() {
		return "Formation [id=" + id + ", title=" + title + ", description=" + description + ", idFormateurs="
				+ idFormateurs + ", niveau=" + niveau + ", idCompetence=" + idCompetence + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + ", nbrHours=" + nbrHours + ", price=" + price + ", type=" + type + "]";
	}


}
