package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class AddMemberDTO {
		
		@NotBlank(message = "Name is required")
		private String name;
		
		@Email(message="Invalid  emial")
		@NotBlank(message="email required")
		private String email;
		
		
		@NotNull(message = "Membership date is required")
		private LocalDate membershipDate;


		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public LocalDate getMembershipDate() {
			return membershipDate;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setMembershipDate(LocalDate membershipDate) {
			this.membershipDate = membershipDate;
		}
		
}
