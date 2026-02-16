package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddMemberDTO;
import com.example.demo.dto.ResponceMemberDTO;
import com.example.demo.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberServ;
	
	@GetMapping("/all")
	public List<ResponceMemberDTO> getAll(){
		return memberServ.getAllMembers();
	}
	
	@GetMapping("/{id}")
	public ResponceMemberDTO getById(@PathVariable Long id) {
		return memberServ.getMemberById(id);
	}
	
	@PostMapping("/add")
	public ResponceMemberDTO addMember(@Valid @RequestBody AddMemberDTO member) {
		return memberServ.saveMember(member);
	}
	
	@PutMapping("/update/{id}")
	public ResponceMemberDTO updateMember(@PathVariable Long id, @Valid @RequestBody AddMemberDTO member) {
		return memberServ.updateMember(id, member);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponceMemberDTO deleteMember(@PathVariable Long id) {
		return memberServ.deleteMemberById(id);
	}
	
	@DeleteMapping("/delete/all")
	public void deleteMember() {
		 memberServ.deleteAll();		
	}
}
