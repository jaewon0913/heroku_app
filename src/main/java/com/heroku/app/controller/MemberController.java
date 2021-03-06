package com.heroku.app.controller;

import com.heroku.app.domain.Address;
import com.heroku.app.domain.Member;
import com.heroku.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        log.info("Get : Members New Form");
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(@Valid MemberForm form, BindingResult bindingResult){
        log.info("Post : Members New");

        return validation(form, bindingResult);
    }

    @PostMapping("/members/save")
    public String createJsonMember(@RequestBody @Valid MemberForm form, BindingResult bindingResult){
        log.info("Post : Members Save");

        return validation(form, bindingResult);
    }

    @GetMapping("/members")
    public String list(Model model){
        log.info("Get : Members List");

        model.addAttribute("members", memberService.findMembers());

        return "members/memberList";
    }

    private String validation(@Valid @RequestBody MemberForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }
}
