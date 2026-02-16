package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddMemberDTO;
import com.example.demo.dto.ResponceMemberDTO;
import com.example.demo.exception.BookNotFound;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	MemberRepository memberRepo;
	
	//DTO
	private Member mapToEntity(AddMemberDTO member) {
		Member newMember = new Member();
		
		newMember.setEmail(member.getEmail());
		newMember.setMembershipDate(member.getMembershipDate());
		newMember.setName(member.getName());
		
		return newMember;
	}
	
	
	private ResponceMemberDTO mapToDTO(Member member) {
		
		ResponceMemberDTO newResponce = new ResponceMemberDTO();
		
		newResponce.setId(member.getId());
		newResponce.setEmail(member.getEmail());
		newResponce.setName(member.getName());
		newResponce.setMembershipDate(member.getMembershipDate());
		
		return newResponce;
	}
	
	public ResponceMemberDTO saveMember(AddMemberDTO member) {
		Member newMember = mapToEntity(member);
		
		Member SavedMem = memberRepo.save(newMember);
		
		return mapToDTO(SavedMem);
	}
	
	
	
	public List<ResponceMemberDTO> getAllMembers() {
		
		List<Member> members = memberRepo.findAll();
		
		return members.stream().map(this :: mapToDTO).toList();
		
	}
	
	
	public ResponceMemberDTO getMemberById(Long id) {
		Member member = memberRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Member Not Found with id: " + id));
		
		return mapToDTO(member);
	}
	
	
	public ResponceMemberDTO updateMember(Long id, AddMemberDTO newMember) {
		
		Member member = mapToEntity(newMember);
		
		Member old_member = memberRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Member Not Found with id: " + id));
		
		old_member.setEmail(member.getEmail());
		old_member.setName(member.getName());
		old_member.setMembershipDate(member.getMembershipDate());
		
		Member savedMem = memberRepo.save(old_member);
		
		return mapToDTO(savedMem);
		
	}
	
	public ResponceMemberDTO deleteMemberById(Long id) {
		
		Member member = memberRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Member Not Found with id: " + id));
		
		memberRepo.delete(member);
		
		return mapToDTO(member); 
	}
	
	
	public void deleteAll() {
		memberRepo.deleteAll();
	}
}
