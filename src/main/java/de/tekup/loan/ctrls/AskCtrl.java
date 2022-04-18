package de.tekup.loan.ctrls;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.tekup.loan.services.AskService;
import de.tekup.loan.soap.api.consume.loaneligibilty.CustomerRequest;
import de.tekup.loan.soap.api.consume.loaneligibilty.ObjectFactory;
import de.tekup.loan.soap.api.consume.loaneligibilty.WsResponse;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AskCtrl {
	
	private AskService service;
	
	@GetMapping("/test/client")
	public String getFormular(Model model) {
		model.addAttribute("request", new ObjectFactory().createCustomerRequest());
		return "ask";
	}
	
	@PostMapping("/test/client")
	public String postFormular(Model model, @ModelAttribute("request") CustomerRequest request) {
		WsResponse response = service.sendRequestToServer(request);
		model.addAttribute("response",response);
		return "reponse";
	}

}
