package edu.sjsu.cmpe.customerfeedback.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;

@JsonPropertyOrder({"owner","links"})

public class OwnerDto extends LinksDto {
	
	private Owner owner;

	public OwnerDto(Owner owner) {
		// TODO Auto-generated constructor stub
		this.owner = owner;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	

}
